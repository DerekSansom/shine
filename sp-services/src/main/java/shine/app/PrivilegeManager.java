package shine.app;

import static com.shine.SharedConstants.FRIEND_ACCEPT;
import static com.shine.SharedConstants.FRIEND_BLOCK;
import static com.shine.SharedConstants.FRIEND_REQUEST;
import static com.shine.SharedConstants.FRIEND_REQUEST_RECEIVED;
import static com.shine.SharedConstants.FRIEND_UNBLOCK;
import static com.shine.SharedConstants.RELATIONSHIP_ALREADY_EXISTS;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.PlayerMapper;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.objects.OtherPlayer;
import com.sp.entity.PlayerEntity;
import com.sp.player.PlayerDao;
import com.sp.player.PrivilegeDaoImpl;
import com.sp.player.Privileges;

@Component
public class PrivilegeManager extends BaseHandler {
	private static final int IS_BLOCKED_BY_FRIEND = 4;
	private static Logger log = LoggerFactory.getLogger(PrivilegeManager.class);

	private PlayerMapper playerMapper = new PlayerMapper();

	@Autowired
	private PrivilegeDaoImpl privilegeDao;

	@Autowired
	private PlayerDao playerDao;

	@Transactional
	public boolean isActionAllowed(int userid, int friendid, int action) {

		Privileges priv = privilegeDao.getPrivileges(userid, friendid);
		if (priv == null) {
			return false;
		}
		long l = priv.getPrivilege();

		return isPrivilegeSet(l, action);
	}

	@Transactional
	public boolean isStatusSet(Privileges priv, int action) {

		if (priv == null) {
			return false;
		}
		long l = priv.getPrivilege();
		return isPrivilegeSet(l, action);
	}

	@Transactional
	public long addPrivilege(long current, int toAdd) {

		long addingPriv = 1 << toAdd - 1;

		System.out.println("current " + Long.toBinaryString(current));
		System.out.println("toAdd   " + Long.toBinaryString(addingPriv));

		long newPrivi = current | addingPriv;

		System.out.println("result  " + Long.toBinaryString(newPrivi));

		return newPrivi;

	}

	@Transactional
	public long removePrivilege(long current, int toRemove) {

		long removingPriv = 1 << toRemove - 1;

		String binaryCurrent = Long.toBinaryString(current);

		System.out.println("current " + binaryCurrent);
		System.out.println("toremove   " + Long.toBinaryString(removingPriv));

// if (binaryCurrent.length() > toRemove) {
// int charPositionToCheck = binaryCurrent.length() - toRemove;
//
// if (binaryCurrent.charAt(charPositionToCheck) == '1') {
// char[] chars = binaryCurrent.toCharArray();
// chars[charPositionToCheck] = '0';
//
// String newString = new String(chars);
// long retl = Long.parseLong(newString, 2);
// System.out.println(retl);
// }
//
// }

		long newPrivi = current ^ removingPriv;

		System.out.println("result  " + Long.toBinaryString(newPrivi));
		if (newPrivi > current) {
			// bit wasn't set in the first place.
			return current;
		}

		return newPrivi;

	}

	private void addPrivilege(Privileges priv, int toAdd) {

		long cpriv = priv.getPrivilege();

		priv.setPrivilege(addPrivilege(cpriv, toAdd));

	}

	private void removePrivilege(Privileges priv, int toAdd) {

		long cpriv = priv.getPrivilege();

		priv.setPrivilege(removePrivilege(cpriv, toAdd));

	}

	/**
	 * checks if the appropriate bit for the action is set in the privileges.
	 * 
	 * @param privileges
	 * @param action
	 * @return
	 */
	@Transactional
	public static boolean isPrivilegeSet(long privileges, int action) {

//		System.out.println("received " + Long.toBinaryString(privileges));
		long shifted = privileges >>> (action - 1);
//		System.out.println("shifted " + Long.toBinaryString(shifted));

		long res = Long.lowestOneBit(shifted);

//		System.out.println("after " + Long.toBinaryString(res));

		return res == 1;

	}

	/**
	 * @param userid
	 * @param friendid
	 * @param privileges
	 * @return
	 * @throws ShineException
	 */
	@Transactional
	public boolean assignPrivileges(int userid, int friendid, long privileges) throws ShineException {

		Privileges priv = new Privileges();
		priv.setFriendId(friendid);
		priv.setUserId(userid);
		priv.setPrivilege(privileges);

		try {
			privilegeDao.createOrUpdatePrivilege(priv);
			return true;

		} catch (Exception e) {
			throw new ShineException("Could not create privileges", e);
		}
	}

	@Transactional
	public List<Privileges> getPrivileges(int userId) throws ShineException {

		List<Privileges> priv = privilegeDao.getAllPrivileges(userId);
		return priv;

	}

	@Transactional
	public List<OtherPlayer> getFriends(int userId, int privilegeLevelRequired) throws ShineException {

		List<OtherPlayer> players = new ArrayList<OtherPlayer>();
		List<Privileges> priv = privilegeDao.getAllPrivileges(userId);
		for (Privileges privileges : priv) {
			int privInt = getPrivLevel(privileges);

			if (privilegeLevelRequired > 0 && privilegeLevelRequired != privInt) {
				continue;
			}
			PlayerEntity entity = playerDao.getPlayerById(privileges.getFriendId());

			if (entity != null) {
				OtherPlayer p = playerMapper.entityToDto(entity);
				p.setIntExtra(privInt);
				players.add(p);
			} else {
				// log as error because should not have privileges for a player that doesn't exist
				log.error("null player retrieved " + privileges.getFriendId() + " for userid privileges "
						+ privileges.getUserId());
			}
		}

		players.addAll(getPendingRequests(userId, 0));

		return players;
	}

	@Transactional
	public static int getPrivLevel(Privileges privileges) {
		int privInt = 0;
		if (isPrivilegeSet(privileges.getPrivilege(), FRIEND_BLOCK)) {
			privInt = FRIEND_BLOCK;
		} else if (isPrivilegeSet(privileges.getPrivilege(), FRIEND_ACCEPT)) {
			privInt = FRIEND_ACCEPT;
		} else if (isPrivilegeSet(privileges.getPrivilege(), FRIEND_REQUEST)) {
			privInt = FRIEND_REQUEST;
		}
		return privInt;
	}

	@Transactional
	public List<OtherPlayer> getPendingRequests(int userId, long secondsSince) {

		List<OtherPlayer> players = new ArrayList<OtherPlayer>();
		List<Privileges> priv = privilegeDao.getPendingRequests(userId, secondsSince);
		for (Privileges privileges : priv) {
			PlayerEntity entity = playerDao.getPlayerById(privileges.getFriendId());

			if (entity != null) {
				OtherPlayer p = playerMapper.entityToDto(entity);
				p.setIntExtra(FRIEND_REQUEST_RECEIVED);
				players.add(p);
			} else {
				// log as error because should not have privileges for a player that doesn't exist
				log.error("null player retrieved " + privileges.getFriendId() + " for userid privileges "
						+ privileges.getUserId());
			}
		}

		return players;
	}

	@Transactional
	public int blockNonFriend(int userId, int friendId) throws ShineException {

		Privileges priv = getPrivileges(userId, friendId);
		if (priv == null) {
			priv = createPrivilege(userId, friendId, FRIEND_BLOCK);
		} else {
			addPrivilege(priv, FRIEND_BLOCK);
			priv.setBlocked(new Date());
		}
		privilegeDao.createOrUpdatePrivilege(priv);

		Privileges reversepriv = getPrivileges(friendId, userId);
		if (reversepriv != null) {
			addPrivilege(reversepriv, IS_BLOCKED_BY_FRIEND);
			reversepriv.setBlockedByFriend(true);
			privilegeDao.createOrUpdatePrivilege(reversepriv);
		}
		return FRIEND_BLOCK;

	}

	@Transactional
	public int unblockFriend(int userId, int friendId) throws ShineException {

		Privileges priv = getPrivileges(userId, friendId);
		Privileges reversepriv = getPrivileges(friendId, userId);
		if (priv == null) {
			// not much to do
			if (reversepriv != null && reversepriv.isBlockedByFriend()) {
				reversepriv.setBlockedByFriend(false);
				privilegeDao.createOrUpdatePrivilege(reversepriv);
			}
			return GeneralError.NOT_FOUND.getCode();
		} else {

			removePrivilege(priv, FRIEND_BLOCK);
			priv.setBlocked(null);
			privilegeDao.createOrUpdatePrivilege(priv);

			if (reversepriv != null && reversepriv.isBlockedByFriend()) {
				reversepriv.setBlockedByFriend(false);
				privilegeDao.createOrUpdatePrivilege(reversepriv);
			}

		}
		return FRIEND_UNBLOCK;

	}

	@Transactional
	public int sendFriendAccept(int userId, int friendId) throws ShineException {

		Date now = new Date();

		// find the priveleges that this is in response to, created by
		// friendid
		Privileges priv = getPrivileges(friendId, userId);
		if (priv == null) {
			return GeneralError.NOT_FOUND.getCode();
		}
		addPrivilege(priv, FRIEND_ACCEPT);
		priv.setAccepted(now);
		privilegeDao.createOrUpdatePrivilege(priv);
		// get this users privileges and update

		Privileges thesePriv = getOrCreatePrivilege(userId, friendId, FRIEND_ACCEPT);
		thesePriv.setAccepted(now);
		privilegeDao.createOrUpdatePrivilege(thesePriv);
		return FRIEND_ACCEPT;

	}

	@Transactional
	public int sendFriendRequest(int userId, int friendId) throws ShineException {

		Privileges priv = getPrivileges(userId, friendId);

		// see if there is already a relationship instigated by other user
		Privileges reversePriv = getPrivileges(friendId, userId);
		if (reversePriv != null) {
			// receiver of this privilege already has a relationship
			if (isPrivilegeSet(reversePriv.getPrivilege(), FRIEND_BLOCK)) {
				if (priv != null) {
					return RELATIONSHIP_ALREADY_EXISTS;
				}
				// else create a privilege ut do nothing else request will
				// not actuall get sent
				priv = getOrCreatePrivilege(userId, friendId, FRIEND_REQUEST);
				addPrivilege(priv, IS_BLOCKED_BY_FRIEND);
				priv.setBlockedByFriend(true);
				// priv.setPrivilege(l);
				privilegeDao.createOrUpdatePrivilege(priv);
				return FRIEND_REQUEST;
			} else {
				// anything other than blocked set up an accepted
				// relationship from both sides
				if (reversePriv.getPrivilege() == 1) {
					// request sent not yet accepted, so treat it as an
					// acceptance
					addPrivilege(reversePriv, FRIEND_ACCEPT);
					reversePriv.setAccepted(new Date());
					privilegeDao.createOrUpdatePrivilege(reversePriv);
				}
				priv = getOrCreatePrivilege(userId, friendId, FRIEND_REQUEST);
				addPrivilege(priv, FRIEND_ACCEPT);
				priv.setAccepted(new Date());
				privilegeDao.createOrUpdatePrivilege(priv);

				alertUser(friendId, FRIEND_ACCEPT);
				// no need to alert requester as this is returned
				// alertUser(userId, FRIEND_ACCEPT);

				return FRIEND_ACCEPT;
			}
		} else {
			// reverse priv does not exist
			if (priv == null) {
				priv = createPrivilege(userId, friendId, FRIEND_REQUEST);
				// priv.setPrivilege(l);
				privilegeDao.createOrUpdatePrivilege(priv);
				return FRIEND_REQUEST;
			} else {

				return RELATIONSHIP_ALREADY_EXISTS;

			}

		}

	}

	private Privileges createPrivilege(int userId, int friendId, long initialPrivilege) {
		Privileges priv = new Privileges();
		priv.setUserId(userId);
		priv.setFriendId(friendId);
		priv.setPrivilege(initialPrivilege);
		return priv;
	}

	/**
	 * retrieves priv from db and ensures these privileges are added or returns
	 * a new Privileges with these privileges set.
	 * 
	 * @param pdao
	 * @param userId
	 * @param friendId
	 * @param privileges
	 * @return
	 */
	private Privileges getOrCreatePrivilege(int userId, int friendId, long privileges) {
		Privileges priv = privilegeDao.getPrivileges(userId, friendId);
		if (priv == null) {
			priv = createPrivilege(userId, friendId, privileges);
		} else {
			priv.setPrivilege(priv.getPrivilege() | privileges);
		}
		return priv;
	}

	private void alertUser(int friendId, int friendAccept) {
		// TODO Auto-generated method stub

	}

	@Transactional
	public Privileges getPrivileges(int userid, int friendid) throws ShineException {

		Privileges priv = privilegeDao.getPrivileges(userid, friendid);
		return priv;
	}

}
