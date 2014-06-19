package shine.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import shine.app.TrackHandler;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.objects.ShineLocation;
import com.sp.spring.SpApplicationContext;

public class UserNotifyServlet extends BasePhoneServlet {

	private TrackHandler handler;
	private boolean isUpdate;

	public UserNotifyServlet() {
		handler = SpApplicationContext.getBean(TrackHandler.class);
	}

	@Override
	public void init() throws ServletException {
		isUpdate = getInitParameter("isUpdate") != null;
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		ShineLocation loc;
		ShineLocation userloc;
		int userId = 0;
		int client = getInt(req, "c");
		boolean getJsonmsgs = client == 9;
		double radius = getDouble(req, "r", SharedConstants.DISTANCE_TOLERANCE);
		String token = req.getParameter("z");
		// String phoneNo = null;
		try {
			loc = getLocation(req);
			userloc = getUserLocation(req);
			if (loc == null && userloc == null) {
				throw new ShineException(GeneralError.PARAM_MISSING, "location required");
			}
		} catch (ShineException e) {
			log.info("failed to get location", e);
			throw e;

		} catch (Exception e) {
			log.info("failed to get location", e);
			throw new ShineException(GeneralError.PARAM_MISSING, "location required");

		}
		userId = getAuthenticatedUserIdIfSet(req, "userid");

		int mode = getInt(req, "mode");
		//if game included
		StringBuilder xml = new StringBuilder(XML_DECL);
		try {
			if (isUpdate) {
				long secondsSince = getLong(req, "since");
				xml.append(handler.updateReg(userId, loc, userloc, secondsSince, mode, getJsonmsgs, radius));

			} else {
				xml.append(handler.initialReg(userId, loc, userloc, mode, getJsonmsgs, radius));
			}

		} catch (ShineException e) {
			log.warn("error in initialRegistration", e);
			throw e;
		} catch (Exception e) {
			log.warn("error in initialRegistration", e);
			throw new ShineException(e);
		}
		return xml.toString();

	}

}
