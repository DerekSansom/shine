package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.game.GameManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.objects.ShineLocation;
import com.sp.spring.SpApplicationContext;

public class PickUpEmeraldServlet extends BasePhoneServlet {

	private GameManager handler;

	public PickUpEmeraldServlet() {
		handler = SpApplicationContext.getBean(GameManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getInt(req, "userid");
		int objectId = getInt(req, "id");
		int answer = getInt(req, "ans");
		ShineLocation loc = getLocation(req);
		if (objectId == 0 || userid == 0 || answer == 0 || loc == null) {
			throw new ShineException(GeneralError.PARAM_MISSING, "id, userid, ans and loc required");
		}
		return "" + pickUp(objectId, userid, answer, loc);
	}

	private int pickUp(int objectId, int userid, int answerid, ShineLocation loc) throws ShineException {
		if (handler.pickUpEmerald(loc, objectId, userid, answerid)) {
			return SharedConstants.SUCCESS;
		}
		throw new ShineException(GeneralError.SYSTEM_ERROR);
	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
