package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.objects.Player;
import com.shine.xml.PlayerXml;
import com.sp.spring.SpApplicationContext;

public class ProfileServlet extends BasePhoneServlet {

	private PlayerManager playerManager;

	public ProfileServlet() {
		playerManager = SpApplicationContext.getBean(PlayerManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		return getProfile(req);
	}

	private String getProfile(HttpServletRequest req) throws ShineException {

		int userId = getUserIdFromToken(req);

		Player user = playerManager.getUser(userId);
		if (user != null) {
			StringBuilder resp = new StringBuilder(XML_DECL);
			resp.append(new PlayerXml(user).toXml());
			return resp.toString();
		}
		throw new ShineException(GeneralError.PLAYER_NOT_FOUND);
	}

}
