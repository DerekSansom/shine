package shine.app;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.dao.exception.ShineException;
import shine.xml.XmlUtil;

import com.shine.SharedConstants;
import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.shine.objects.Player;
import com.sp.trending.TrendManager;

@Component
public class TrackHandler extends BaseHandler {

	@Autowired
	private TrendManager tm;

	@Autowired
	private PlayerManager playerManager;

	@Autowired
	private ObjectManager objectManager;

	@Autowired
	private MailHandler mh;

	@Transactional
	public String initialReg(int userId, ShineLocation loc, ShineLocation userloc, int mode, boolean jsonMsgs,
			double radius)
			throws ShineException {

		// if viewLoc is not set then send objects for userloc
		if (loc == null) {
			loc = userloc;
		}
		Player u = registerUserLoc(userloc, userId);
		List<ShineObject> objects = objectManager.getNewUserLocObjects(mode, userloc, loc, u, 0, radius);
		String mail = mh.getMail(jsonMsgs ? SharedConstants.JSON_MSGS
				: SharedConstants.BOTH_BOXES, userId, 0, 0);

		StringBuilder xml = new StringBuilder("<xml>");
		xml.append(XmlUtil.createObjectListXml(objects));

		if (mail != null) {
			xml.append(mail);
		}
		String trending = getTrending(userId, loc);
		if (trending != null) {
			xml.append(trending);
		}

		// xml.append(getPendingFriends(pdao, userId, 0));
		xml.append("</xml>");
		return xml.toString();

	}

	private String getTrending(int userId, ShineLocation userloc) {

		List<ShineObject> latest = tm.getLatestTrends(userloc, userId);

		String trends = tm.toTrendString(latest);

		return trends;
	}

	// private String getPendingFriends(PlayerDaoHib pdao, int userId, long
	// secondsSince) {
	// PrivilegeManager pm = new PrivilegeManager();
	//
	// List<Player> pendingFriends = pm.getPendingRequests(pdao, userId,
	// secondsSince);
	// if (pendingFriends.isEmpty()) {
	// return "";
	// }
	// StringBuilder sb = new StringBuilder("<freq>");
	// for (Player player : pendingFriends) {
	// sb.append(player.toXml());
	// }
	//
	// sb.append("</freq>");
	//
	// return sb.toString();
	// }

	/**
	 * Same as initialReg but will send (eventually) new objects
	 * 
	 * @param userId
	 * @param viewloc
	 * @param mode
	 * @return
	 * @throws ShineException
	 */
	@Transactional
	public String updateReg(int userId, ShineLocation viewloc, ShineLocation userloc, long secondsSince, int mode,
			boolean jsonMsgs, double radius)
			throws ShineException {

		if (viewloc == null) {
			viewloc = userloc;
		}
// if (userloc == null) {
// userloc = loc;
// }

		// PlayerDaoHib pdao = new PlayerDaoHib(session);
		// if userloc is null this won't write it anywhere just return the user
		// try {
		Player u = null;
		if (userId > 0) {
			registerUserLoc(userloc, userId);
		}
		List<ShineObject> objects = objectManager.getNewUserLocObjects(mode, userloc, viewloc, u,
				secondsSince, radius);

		StringBuilder xml = new StringBuilder("<xml>");
		xml.append(XmlUtil.createObjectListXml(objects));

		xml.append(getNewMail(userId, jsonMsgs));

		// xml.append(getPendingFriends(pdao, userId, secondsSince));

		xml.append("</xml>");
		return xml.toString();
		// }
		// finally {
		// pdao.closeSession();
		// }

	}

	private CharSequence getNewMail(int userId, boolean jsonMsgs) throws ShineException {

		String mail = null;

		if (jsonMsgs) {
			try {
				mail = mh.getNewMsgs(userId);
			} catch (IOException e) {
				log.error("Failed to get json msgs", e);
			}

		} else {
			mail = mh.getNewMail(userId);
		}
		if (mail != null) {
			return mail;
		}
		return "";
	}

	private Player registerUserLoc(ShineLocation userloc, int userId) throws ShineException {
		return playerManager.getUser(userId, userloc);
	}

}
