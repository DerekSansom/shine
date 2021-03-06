package shine.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shine.app.MailReadManager;
import shine.dao.exception.ShineException;

import com.sp.spring.SpApplicationContext;

public class ReadMailServlet extends BasePhoneServlet {

	MailReadManager mrm;

	public ReadMailServlet() {
		mrm = SpApplicationContext.getBean(MailReadManager.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int readMail = getInt(req, "id");
		mrm.markMailAsRead(readMail);

	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		// unimplemented
		return null;
	}

}
