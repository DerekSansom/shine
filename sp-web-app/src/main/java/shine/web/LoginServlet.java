package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.LoginManager;
import shine.app.track.TrackingManager;
import shine.dao.exception.ShineException;

import com.shine.SharedConstants;
import com.shine.objects.Player;
import com.shine.xml.PlayerXml;
import com.sp.spring.SpApplicationContext;

public class LoginServlet extends BasePhoneServlet {

	private LoginManager loginManager;

	public LoginServlet() {
		loginManager = SpApplicationContext.getBean(LoginManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		return doLogin(req);
	}

	private String doLogin(HttpServletRequest req) throws ShineException {

		String cred1 = req.getParameter("cred");
		String password = req.getParameter("pwd");
		String phoneNo = req.getParameter("phone");
		int t = getInt(req, "t");
		int client = getInt(req, "c");
		String platform = getParam(req, "platform", 20);
		String os = getParam(req, "os", 10);
		String version = getParam(req, "version", 10);
		try {

			Player user = loginManager.doLogin(cred1, password, platform, os, version);
			if (user != null) {
				StringBuilder resp = new StringBuilder(XML_DECL);
				resp.append(new PlayerXml(user).toXml());
				return resp.toString();
			}
		} finally {
			TrackingManager.tracklogin(cred1, phoneNo, client);
		}

		throw new ShineException(SharedConstants.LOG_IN_FAILED);

	}

}
