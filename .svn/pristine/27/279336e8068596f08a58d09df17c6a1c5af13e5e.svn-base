package shine.app;

import static com.shine.GeneralError.NOT_FOUND;
import static com.shine.SharedConstants.AWAITING_PLAY;
import static com.shine.SharedConstants.CHALLENGER_WINS;
import static com.shine.SharedConstants.CHALLENGER_WINS_ROUND;
import static com.shine.SharedConstants.CHALLENGER_WINS_ROUND_TIMEOUT;
import static com.shine.SharedConstants.DECLINED;
import static com.shine.SharedConstants.NOT_ENOUGH_POINTS;
import static com.shine.SharedConstants.OPPONENT_WINS;
import static com.shine.SharedConstants.OPPONENT_WINS_ROUND;
import static com.shine.SharedConstants.OPPONENT_WINS_ROUND_TIMEOUT;
import static com.shine.SharedConstants.OPP_WINS_GAME;
import static com.shine.SharedConstants.OPP_WINS_ROUND;
import static com.shine.SharedConstants.OPP_WINS_TIMEOUT;
import static com.shine.SharedConstants.PLAYER_NOT_FOUND;
import static com.shine.SharedConstants.YOU_WIN_GAME;
import static com.shine.SharedConstants.YOU_WIN_ROUND;
import static com.shine.SharedConstants.YOU_WIN_TIMEOUT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.Challenge.Round;
import shine.dao.exception.ShineException;

import com.sp.entity.PlayerEntity;
import com.sp.player.PlayerDao;

@Component
public class ChallengeHandler extends BaseHandler {
	private static final int CHALLENGE_SCORE = 1;

	@Autowired
	private PlayerDao playerDao;

	@Transactional
	public Challenge makeChallenge(int playerid, int oppid) throws ShineException {

		checkPlayers(playerid, oppid);

		Challenge c = ChallengeCache.makeChallenge(playerid, oppid);
		int seconds = 0;

		while (seconds < 30 && c.getAccepted() == null && c.isDeclined()) {
			try {
				Thread.sleep(3000);
				seconds += 3;
			} catch (InterruptedException e) {
				log.debug("Waiting for challenge acceptance", e);
			}

		}
		if (c.getAccepted() != null) {
			return c;
		} else if (c.isDeclined()) {
			throw new ShineException(DECLINED);
		}

		throw new ShineException(AWAITING_PLAY);

	}

	@Transactional
	public Challenge pingChallenge(int playerid, int oppid) throws ShineException {

		checkPlayers(playerid, oppid);
		return ChallengeCache.findPendingChallenge(playerid, oppid);

	}

	private static final int waitPeriod = 3000;

	/**
	 * R
	 * 
	 * @param challengeid
	 * @param playerid
	 * @param round
	 * @param play
	 * @return
	 * @throws ShineException
	 */
	public ResultReturn play(int challengeid, int playerid, int round, int play) throws ShineException {

		Challenge c = ChallengeCache.getChallenge(challengeid);
		Round r = c.play(playerid, round, play);
		int millis = 0;
		while (millis < Challenge.MILLISECONDS_ALLOWED && r.result() == AWAITING_PLAY) {
			try {
				Thread.sleep(waitPeriod);
			} catch (InterruptedException e) {
				log.debug("Waiting for other player", e);
			}
			millis += waitPeriod;
		}
		ResultReturn rr = createResultReturn(r, c, playerid);
		return rr;

	}

	/**
	 * Used to convert and convey a Challenger/Opponent result into a YOU, OPP
	 * result for return to player
	 * 
	 * @author derek
	 * 
	 */
	public class ResultReturn {
		int opponentsSelection, result, yourscore, oppscore;

		public int getOpponentsSelection() {
			return opponentsSelection;
		}

		public int getResult() {
			return result;
		}

		public int getYourscore() {
			return yourscore;
		}

		public int getOppscore() {
			return oppscore;
		}
	}

	private ResultReturn createResultReturn(Round thisRound, Challenge c, int playerid) {

		boolean isChallenger = c.getChallenger().intValue() == playerid;

		ResultReturn rr = new ResultReturn();
		if (isChallenger) {
			rr.opponentsSelection = thisRound.opponentSymbol;
			rr.yourscore = c.getChallengerScore();
			rr.oppscore = c.getOpponentScore();
		} else {
			rr.opponentsSelection = thisRound.challengerSymbol;
			rr.yourscore = c.getOpponentScore();
			rr.oppscore = c.getChallengerScore();
		}

		switch (thisRound.result) {
		case CHALLENGER_WINS:
			rr.result = isChallenger ? YOU_WIN_GAME : OPP_WINS_GAME;
			break;
		case OPPONENT_WINS:
			rr.result = isChallenger ? OPP_WINS_GAME : YOU_WIN_GAME;
			break;
		case CHALLENGER_WINS_ROUND:
			rr.result = isChallenger ? YOU_WIN_ROUND : OPP_WINS_ROUND;
			break;
		case CHALLENGER_WINS_ROUND_TIMEOUT:
			rr.result = isChallenger ? YOU_WIN_TIMEOUT : OPP_WINS_TIMEOUT;
			break;
		case OPPONENT_WINS_ROUND:
			rr.result = isChallenger ? OPP_WINS_ROUND : YOU_WIN_ROUND;
			break;
		case OPPONENT_WINS_ROUND_TIMEOUT:
			rr.result = isChallenger ? OPP_WINS_TIMEOUT : YOU_WIN_TIMEOUT;
			break;
		}

		return rr;
	}

	public Challenge acceptChallenge(int challengeid, int challid, int playerid) throws ShineException {

		Challenge c = ChallengeCache.acceptChallenge(challengeid, challid, playerid);
		if (c != null) {
			return c;
		}

		throw new ShineException(NOT_FOUND);

	}

	@Transactional
	public int refuseChallenge(int challengeid, int playerid, int challid) throws ShineException {

		Challenge c = ChallengeCache.refuseChallenge(challengeid, challid, playerid);

		PlayerEntity challenger = playerDao.getPlayerById(challid);
		if (challenger == null) {
			throw new ShineException(PLAYER_NOT_FOUND);
		}
		PlayerEntity opponent = playerDao.getPlayerById(playerid);
		if (opponent == null) {
			throw new ShineException(NOT_FOUND);
		}
		opponent.addScore(-CHALLENGE_SCORE);
		challenger.addScore(CHALLENGE_SCORE);

		playerDao.save(opponent);
		playerDao.save(challenger);

		return CHALLENGE_SCORE;

	}

	private void checkPlayers(int cid, int opid) throws ShineException {

		PlayerEntity challenger = playerDao.getPlayerById(cid);
		if (challenger == null) {
			throw new ShineException(PLAYER_NOT_FOUND);
		} else if (challenger.getScore() < CHALLENGE_SCORE) {
			throw new ShineException(NOT_ENOUGH_POINTS);

		}

		PlayerEntity opponent = playerDao.getPlayerById(opid);
		if (opponent == null) {
			throw new ShineException(NOT_FOUND);
		} else if (opponent.getScore() < CHALLENGE_SCORE) {
			throw new ShineException(NOT_ENOUGH_POINTS);

		}
	}

}
