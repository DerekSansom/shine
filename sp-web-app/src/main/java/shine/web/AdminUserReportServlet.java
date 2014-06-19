package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.sp.spring.SpApplicationContext;

public class AdminUserReportServlet extends BasePhoneServlet {

	private PlayerManager pm = new PlayerManager();

	public AdminUserReportServlet() {
		pm = SpApplicationContext.getBean(PlayerManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int ret = handleReport(req);

		return "" + ret;
	}

	private int handleReport(HttpServletRequest req) throws ShineException {

		String judgement = req.getParameter("reason");
		int userId = getInt(req, "userid");
		int reportId = getInt(req, "reportid");
		int adminId = getInt(req, "adminid");
		boolean accepted = getBoolean(req, "accepted", true);

		if (userId == 0 || adminId == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING);
		}

		try {
			pm.handleUserReport(reportId, userId, accepted, adminId, judgement);

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
