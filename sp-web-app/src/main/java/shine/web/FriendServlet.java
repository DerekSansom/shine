package shine.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import shine.app.PrivilegeManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.objects.OtherPlayer;
import com.shine.xml.OtherPlayerXml;
import com.sp.spring.SpApplicationContext;

public class FriendServlet extends BasePhoneServlet {

	private PrivilegeManager pm;

	public FriendServlet() {
		pm = SpApplicationContext.getBean(PrivilegeManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getInt(req, "userid");
		if (userid == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING, "userid required");
		}

		int priv = getInt(req, "priv");

		try {
			List<OtherPlayer> list = pm.getFriends(userid, priv);
			if (list == null || list.isEmpty()) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			StringBuilder sb = new StringBuilder(XML_DECL);
			sb.append("<objects>");
			for (OtherPlayer player : list) {
				sb.append(new OtherPlayerXml(player).toXml());
			}
			sb.append("</objects>");

			return sb.toString();
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.error("FriendsServlet", e);
			throw new ShineException(e);
		}
	}

}
