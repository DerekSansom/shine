package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.MailCreateManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.sp.spring.SpApplicationContext;

public class SendMailServlet extends BasePhoneServlet {

	private MailCreateManager mailManager;

	public SendMailServlet() {
		mailManager = SpApplicationContext.getBean(MailCreateManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		return "" + createMail(req);

	}

	private int createMail(HttpServletRequest req) throws ShineException {

		String text = req.getParameter("text");
		String subject = req.getParameter("subject");

		String message = req.getParameter("msg");

		int recipientId = getInt(req, "recid");
		int senderId = getInt(req, "userid");
		int inReplyTo = getInt(req, "inreplyto");

		if (recipientId == 0 || senderId == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING, "recid and userid required");
		}
		int ret = mailManager.createMail(subject, recipientId, senderId, text, inReplyTo, message);

		return ret;

	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
