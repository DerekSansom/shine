package shine.web;

import static com.shine.SharedConstants.FRIEND_ACCEPT;
import static com.shine.SharedConstants.FRIEND_BLOCK;
import static com.shine.SharedConstants.FRIEND_REQUEST;
import static com.shine.SharedConstants.FRIEND_UNBLOCK;

import javax.servlet.http.HttpServletRequest;

import shine.app.PrivilegeManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.sp.spring.SpApplicationContext;

public class FriendReqServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;

	private PrivilegeManager pm;

	public FriendReqServlet() {
		pm = SpApplicationContext.getBean(PrivilegeManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getInt(req, "userid");
		int friendid = getInt(req, "fid");
		int action = getInt(req, "action");
		if (userid == 0 || friendid == 0 || action == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING, "userid, friendid and action are required");
		}
		if (action != FRIEND_ACCEPT && action != FRIEND_REQUEST &&
				action != FRIEND_BLOCK && action != FRIEND_UNBLOCK) {
			throw new ShineException(GeneralError.PARAM_MISSING, "ActionId is invalid");
		}
		int ret = 0;
		switch (action) {
		case FRIEND_ACCEPT:
			ret = pm.sendFriendAccept(userid, friendid);
			break;
		case FRIEND_REQUEST:
			ret = pm.sendFriendRequest(userid, friendid);
			break;
		case FRIEND_BLOCK:
			ret = pm.blockNonFriend(userid, friendid);
			break;

		case FRIEND_UNBLOCK:
			ret = pm.unblockFriend(userid, friendid);
			break;

		default:
			break;
		}

		return "" + ret;
	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
