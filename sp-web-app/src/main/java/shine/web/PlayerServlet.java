package shine.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.NoticeBoard;
import com.shine.objects.OtherPlayer;
import com.shine.objects.ShineObject;
import com.shine.xml.NoticeBoardXml;
import com.shine.xml.OtherPlayerXml;
import com.sp.spring.SpApplicationContext;

public class PlayerServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;

	private PlayerManager playerManager;

	public PlayerServlet() {
		playerManager = SpApplicationContext.getBean(PlayerManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int playerid = getInt(req, "playerid");
		int userid = getAuthenticatedUserId(req, "userid");
		if (playerid > 0) {
			boolean withActivity = getBoolean(req, "activity");

			String p = getPlayer(playerid, withActivity, userid);
			return p;
		}

		throw new ShineException(GeneralError.PARAM_MISSING, "playerid required");
	}

	private String getPlayer(int playerid, boolean withActivity, int userid) throws ShineException {

		StringBuilder sb = new StringBuilder(XML_DECL);
		if (!withActivity) {
			OtherPlayer p = playerManager.getPlayerByPrivilege(playerid, userid);
			sb.append(new OtherPlayerXml(p).toXml());
		} else {
			List<ShineObject> list = playerManager.getPlayerWithActivity(playerid, userid);
			sb.append("<objects>");
			for (ShineObject shineObject : list) {
				if (shineObject instanceof NoticeBoard) {
					NoticeBoardXml xml = new NoticeBoardXml((NoticeBoard) shineObject);

					sb.append(xml.toXmlLite());
				} else {
					sb.append(getXml(shineObject));
				}
			}
			sb.append("</objects>");

		}
		return sb.toString();
	}

}
