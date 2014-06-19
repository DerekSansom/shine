package shine.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import shine.app.PlayerManager;
import shine.app.mapper.PlayerMapper;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.objects.OtherPlayer;
import com.shine.xml.OtherPlayerXml;
import com.sp.entity.PlayerEntity;
import com.sp.spring.SpApplicationContext;

public class LeaderBoardServlet extends BasePhoneServlet {

	private static final PlayerMapper PLAYER_MAPPER = new PlayerMapper();
	private PlayerManager playerManager;

	public LeaderBoardServlet() {
		playerManager = SpApplicationContext.getBean(PlayerManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getInt(req, "userid");

		if (userid == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING, "userid required");
		}

		try {
			List<PlayerEntity> list = playerManager.getLeaderBoard();

			List<OtherPlayer> players = PLAYER_MAPPER.entityToDto(list);

			StringBuilder sb = new StringBuilder(XML_DECL);
			sb.append("<lb>");
			for (OtherPlayer player : players) {
				OtherPlayerXml xml = new OtherPlayerXml(player);
				sb.append(xml.toXmlLite());
			}
			sb.append("</lb>");

			return sb.toString();
		} catch (Exception e) {
			log.error("LeaderBoardServlet", e);
			throw new ShineException(e);
		}
	}

}
