package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.BoardManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.sp.spring.SpApplicationContext;

public class AdminBoardServlet extends BasePhoneServlet {

	private BoardManager bm;

	public AdminBoardServlet() {
		bm = SpApplicationContext.getBean(BoardManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int ret = handleReport(req);

		return "" + ret;
	}

	private int handleReport(HttpServletRequest req) throws ShineException {

		String judgement = req.getParameter("reason");
		int boardId = getInt(req, "boardid");
		int reportId = getInt(req, "reportid");
		int adminId = getInt(req, "adminid");
		boolean accepted = getBoolean(req, "accepted", true);

		if ((boardId < 1 && reportId < 1) || adminId < 1) {
			throw new ShineException(GeneralError.PARAM_MISSING,
					"admin id and either boardid or report id are required");
		}
		try {
			if (reportId > 0) {
				bm.handleBoardReport(reportId, accepted, judgement, adminId);

			} else {
				bm.suspendBoard(boardId, judgement, accepted, adminId);

			}

			return SharedConstants.SUCCESS;
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log("Failed to create report", e);
			throw new ShineException(e);
		}
	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
