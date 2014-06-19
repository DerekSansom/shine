package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.game.GameManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.objects.Ruby;
import com.shine.objects.ShineLocation;
import com.sp.spring.SpApplicationContext;

public class PickUpRuby extends BasePhoneServlet {

	private GameManager handler;

	public PickUpRuby() {
		handler = SpApplicationContext.getBean(GameManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getInt(req, "userid");
		int objectId = getInt(req, "id");

		ShineLocation loc = getLocation(req);
		if (objectId == 0 || userid == 0 || loc == null) {
			throw new ShineException(GeneralError.PARAM_MISSING, "id, userid and loc required");
		}
		return pickUp(objectId, userid, loc);
	}

	private String pickUp(int objectId, int userid, ShineLocation loc) throws ShineException {
		int res = handler.pickUpRuby(loc, objectId, userid);
		if (res > 0) {
			Ruby nextRube = handler.getRuby(res, userid);
			if (nextRube != null) {
				return nextRube.toXml();
			}
			//next line is not necessary as handler would throw this anyway.
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		throw new ShineException(res);
	}

}
