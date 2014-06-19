package shine.mobweb;

import javax.servlet.http.HttpServletRequest;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;

public class PlayerServlet extends BaseMobWebServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		boolean holding = false;
		int playerid = getInt(req, "playerid");
		if (holding) {
			return "/mw/holding.html";
		}

		if (playerid > 0) {
//			boolean withActivity = getBoolean(req, "activity");

			String p = getPlayer(playerid);
			return p;
		}

		throw new ShineException(GeneralError.PARAM_MISSING);
	}

	private String getPlayer(int playerid
			//, boolean withActivity, int userid
			) throws ShineException {

		PlayerManager pm = new PlayerManager();
//			Player p = pm.getPlayer(playerid, null);
//			sb.append(p.toXml());
//		} else {
//			List<ShineObject> list = pm.getPlayerWithActivity(playerid, userid);
//			sb.append("<objects>");
//			for (ShineObject shineObject : list) {
//				if (shineObject instanceof NoticeBoard) {
//					sb.append(((NoticeBoard) shineObject).toXmlLite());
//				} else {
//					sb.append(shineObject.toXml());
//				}
//			}
//			sb.append("</objects>");
//
//		}
		return null;
	}

}
