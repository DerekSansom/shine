package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.ReportManager;
import shine.dao.exception.ShineException;

import com.sp.spring.SpApplicationContext;

public class AdminReportServlet extends BasePhoneServlet {

	private ReportManager reportManager;

	public AdminReportServlet() {
		reportManager = SpApplicationContext.getBean(ReportManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int ret = handleReport(req);

		return "" + ret;
	}

	private int handleReport(HttpServletRequest req) throws ShineException {

		String judgement = req.getParameter("reason");
		boolean accepted = getBoolean(req, "accepted");
		int reportId = getInt(req, "rid");
		int adminId = getInt(req, "adminid");

		return reportManager.handleReport(reportId, accepted, judgement, adminId);

	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
