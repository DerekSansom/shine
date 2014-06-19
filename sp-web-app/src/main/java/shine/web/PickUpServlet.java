package shine.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import shine.app.game.GameManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.objects.ShineLocation;
import com.sp.spring.SpApplicationContext;

public class PickUpServlet extends BasePhoneServlet {

	private static final int DIAMOND = 1;
	private static final int RUBY = 2;
	private static final int SAPPHIRE = 3;

	private GameManager handler;

	private int type;

	public PickUpServlet() {
		handler = SpApplicationContext.getBean(GameManager.class);
	}

	@Override
	public void init() throws ServletException {
		handler = new GameManager();
		String t = getInitParameter("type");
		if (t.equals("DIAMOND")) {
			type = DIAMOND;
		} else if (t.equals("RUBY")) {
			type = RUBY;
		} else if (t.equals("SAPPHIRE")) {
			type = SAPPHIRE;
		}
//			else if (t.equals("EMERALD")) {
//			type = EMERALD;
//		}
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getAuthenticatedUserId(req, "userid");
		int objectId = getInt(req, "id");
		String token = req.getParameter("z");
		ShineLocation loc = getLocation(req);
		if (objectId == 0 || userid == 0 || loc == null || token == null) {
			throw new ShineException(GeneralError.PARAM_MISSING, "id, userid, loc and z(token) required");
		}

		return "" + pickUp(objectId, userid, loc);
	}

	private int pickUp(int objectId, int userid, ShineLocation loc) throws ShineException {
		switch (type) {
		case DIAMOND:
			if (handler.pickUpDiamond(loc, objectId, userid)) {
				return SharedConstants.SUCCESS;
			}
		case RUBY:
			return handler.pickUpRuby(loc, objectId, userid);
		case SAPPHIRE:
			return handler.pickUpSapphire(objectId, userid, loc);
		}
		return GeneralError.SYSTEM_ERROR.getCode();
	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
