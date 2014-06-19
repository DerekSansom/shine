package shine.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import shine.app.track.TrackingManager;

public class TrackServlet extends HttpServlet {

	private Logger log = Logger.getLogger(TrackServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		int adid = getInt(req, "adid");
		int userid = getInt(req, "userid");
		int action = getInt(req, "action");
		int client = getInt(req, "c");
		if (action > 0)
			TrackingManager.trackAdAction(userid, adid, action, client);
		else
			TrackingManager.trackAdView(userid, adid, client);

	}

	protected int getInt(HttpServletRequest req, String parameter) {
		String val = req.getParameter(parameter);
		if (val == null) {
			return 0;
		}
		try {
			int i = Integer.parseInt(val);
			return i;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

}
