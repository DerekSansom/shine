package shine.app;

import static com.shine.SharedConstants.AWAITING_PLAY;
import static com.shine.SharedConstants.BUSY;
import static com.shine.SharedConstants.DECLINED;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;

public class ChallengeCache {

	private static Map<Integer, Challenge> challengesByChallenger = new HashMap<Integer, Challenge>();
//	private static Map<Integer, Challenge> challengesByOpponent = new HashMap<Integer, Challenge>();
// a list of made but not yet accepted or rejected challenges by challenger id.
	private static Map<Integer, Challenge> pendingChallenges = new HashMap<Integer, Challenge>();
//	private static Map<Integer, Challenge> pendingChallengesByOponent = new HashMap<Integer, Challenge>();
	private static Map<Integer, Challenge> allChallenges = new HashMap<Integer, Challenge>();
	private static long gameTimeOut = 2 * 60000;
	private static int nextId = 1;

	static Challenge makeChallenge(Integer challengerId, Integer opponentId) throws ShineException {
//TODO: more checks here, if recent challenge refused new one not allowed for a while
		//Also timeouts: and proximity
		//also save to db

		Challenge c = checkForChallenge(challengerId, opponentId);
		if (c == null) {
			c = new Challenge(challengerId, opponentId);
			c.setId(nextId++);
			pendingChallenges.put(challengerId, c);
//		pendingChallengesByOponent.put(opponentId, c);
			allChallenges.put(c.getId(), c);
		}
		return c;

	}

	private static Challenge checkForChallenge(Integer challengerId, Integer opponentId) throws ShineException {
		Challenge c = null;
		if (pendingChallenges.containsKey(opponentId)) {
			c = pendingChallenges.get(opponentId);
		}
		if (pendingChallenges.containsKey(challengerId)) {
			c = pendingChallenges.get(challengerId);
		}
		if (c == null && challengesByChallenger.containsKey(challengerId)) {
			c = challengesByChallenger.get(challengerId);
		}
		if (c != null && !isTimedOut(c)) {
			if ((c.getOpponent().equals(opponentId) && c.getChallenger().equals(challengerId)) ||
					(c.getOpponent().equals(challengerId) && c.getChallenger().equals(opponentId))) {
				return c;
			}
			throw new ShineException(BUSY);
		}
		return null;
	}

	private static boolean isTimedOut(Challenge c) {
		if (c.getAccepted() == null && c.getCreatedMillis() < System.currentTimeMillis() - gameTimeOut) {
			allChallenges.remove(c.getId());
			pendingChallenges.remove(c.getChallenger());
			challengesByChallenger.remove(c.getChallenger());
			return true;
		}
		if (c.getAccepted() != null && c.getLastActivity() < System.currentTimeMillis() - gameTimeOut) {
			allChallenges.remove(c.getId());
			pendingChallenges.remove(c.getChallenger());
			challengesByChallenger.remove(c.getChallenger());
			return true;
		}

		return false;
	}

	static Challenge acceptChallenge(int challengeid, Integer challengerId, Integer opponentId) throws ShineException {

		Challenge c = pendingChallenges.get(challengerId);
		if (c == null || !c.getOpponent().equals(opponentId)) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		if (c.getAccepted() != null) {
			throw new ShineException(SharedConstants.ALREADY_ACTIONED);
		}
		c.setAccepted(new Date());
		pendingChallenges.remove(challengerId);
//		pendingChallengesByOponent.remove(opponentId);
		challengesByChallenger.put(challengerId, c);
		allChallenges.put(c.getId(), c);
//		challengesByOpponent.put(opponentId, c);
		return c;

	}

	static Challenge refuseChallenge(int id, Integer challengerId, Integer opponentId) throws ShineException {

		Challenge c = pendingChallenges.get(challengerId);
		if (c == null || c.getOpponent() != opponentId) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		c.decline();
		pendingChallenges.remove(challengerId);
//		pendingChallengesByOponent.remove(opponentId);
		allChallenges.remove(id);
		challengesByChallenger.put(challengerId, c);
		return c;

	}

	static Challenge getChallenge(Integer id) throws ShineException {

		Challenge c = allChallenges.get(id);
		if (c == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		return c;

	}

	public static Challenge findPendingChallenge(int challengerid, int opponentid) throws ShineException {
		Challenge c = challengesByChallenger.get(challengerid);
		if (c != null) {
			if (c.getOpponent().intValue() == opponentid) {
				if (c.isDeclined()) {
					//now done, remove and record
					challengesByChallenger.remove(challengerid);
					allChallenges.remove(c.getId());
					recordChallenge(c);
					throw new ShineException(DECLINED);
				}
				if (c.getAccepted() != null) {
					return c;
				}
			} else {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
		}
		c = pendingChallenges.get(challengerid);
		if (c == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		if (c.getOpponent().intValue() == opponentid) {
			throw new ShineException(AWAITING_PLAY);
		}
		return c;

	}

	private static void recordChallenge(Challenge c) {

	}

}
