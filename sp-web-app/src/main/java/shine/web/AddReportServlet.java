package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.ReportManager;
import shine.dao.exception.ShineException;

import com.shine.boards.Report;
import com.sp.spring.SpApplicationContext;

public class AddReportServlet extends BasePhoneServlet {

	private ReportManager reportManager;

	public AddReportServlet() {
		reportManager = SpApplicationContext.getBean(ReportManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int ret = createReport(req);

		return "" + ret;
	}

	private int createReport(HttpServletRequest req) throws ShineException {

		String reason = req.getParameter("reason");
		int reporterId = getAuthenticatedUserId(req, "creatorid");
		int noticeId = getInt(req, "noticeid");
		Integer replyId = getIntOrNull(req, "replyid");

		Report r = new Report(0, reporterId, noticeId, replyId, reason);

		return reportManager.createReport(r);

	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
