package shine.mobweb;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import shine.app.BoardManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.NoticeBoard;

public class BoardServlet extends BaseMobWebServlet {

	public static boolean internaliseUsers = true;
	public static final SimpleDateFormat viewdf = new SimpleDateFormat("dd MMM yy");

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int boardId = getInt(req, "id");
		String forward = "/mw/allposts.jsp";

		if (boardId > 0) {
			//if these are set then get board based on it
		} else if (req.getSession().getAttribute("board") != null) {
			return forward;
		} else {
			throw new ShineException(GeneralError.PARAM_MISSING);
		}
		try {
			BoardManager bm = new BoardManager();
			NoticeBoard board = bm.getAnonNoticeBoard(boardId);

			if (board == null) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}

			req.getSession().setAttribute("board", board);
			return forward;

		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.error("Board Servlet failed to create board", e);
			throw new ShineException(e);
		} finally {
		}

	}

}
