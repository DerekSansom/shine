package shine.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import shine.app.BoardManager;
import shine.app.track.TrackingManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.NoticeBoard;
import com.shine.objects.OtherPlayer;
import com.shine.objects.ShineObject;
import com.shine.xml.NoticeBoardXml;
import com.shine.xml.OtherPlayerXml;
import com.sp.spring.SpApplicationContext;

public class BoardServlet extends BasePhoneServlet {

	public static boolean internaliseUsers = true;
	private BoardManager bm;

	public BoardServlet() {
		bm = SpApplicationContext.getBean(BoardManager.class);
	}


	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int boardId = getInt(req, "id");
		int noticeId = getInt(req, "noticeid");
		int replyId = getInt(req, "replyid");
		int userId = getAuthenticatedUserIdIfSet(req, "userid");
		int client = getInt(req, "c");

		if (boardId == 0 && noticeId == 0 && replyId == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING);
		}
			List<ShineObject> list =
					bm.getNoticeBoard(boardId, userId, noticeId, replyId, internaliseUsers);

			if (list == null || list.isEmpty()) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}

			NoticeBoard board = (NoticeBoard) list.get(0);
			NoticeBoardXml xmlWrapper = new NoticeBoardXml(board);

			TrackingManager.trackBoardView(userId, boardId, board.getAds(), client);
			StringBuilder sb = new StringBuilder(XML_DECL);
			sb.append("<xml>");
			sb.append(xmlWrapper.toXml());
			if (!internaliseUsers) {
				for (int i = 1; i < list.size(); i++) {
					OtherPlayer p = (OtherPlayer) list.get(i);
					sb.append(new OtherPlayerXml(p).toXml());
				}
			}
			sb.append("</xml>");

			return sb.toString();

	}

}
