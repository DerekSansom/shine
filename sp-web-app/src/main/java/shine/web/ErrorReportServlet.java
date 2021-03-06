package shine.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.log4j.Logger;

import shine.app.ErrorManager;

import com.shine.error.ErrorReport;
import com.sp.spring.SpApplicationContext;

public class ErrorReportServlet extends HttpServlet {

	private Logger log = Logger.getLogger(ErrorReportServlet.class);

	private ErrorManager errorManager;

	public ErrorReportServlet() {
		errorManager = SpApplicationContext.getBean(ErrorManager.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		try {
			process(req);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		res.getWriter().print("OK");
		res.getWriter().close();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		doPost(req, resp);
	}

	private void process(HttpServletRequest req) throws FileUploadException, IOException {

//		ServletFileUpload upload = new ServletFileUpload();

		String stacktrace = req.getParameter("stacktrace");
		String version = getParam(req, "version", 10);
		String os = getParam(req, "os", 10);
		String platform = getParam(req, "platform", 20);

		log.info(stacktrace);

		ErrorReport er = new ErrorReport(stacktrace);
		er.setOs(os);
		er.setPlatform(platform);
		er.setVersion(version);
		errorManager.saveReport(er);

	}

	String getParam(HttpServletRequest req, String param, int i) {
		String value = req.getParameter(param);
		if (value != null && value.length() > i) {
			value = value.substring(0, i);
		}
		return value;
	}

}
