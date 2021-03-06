package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.MailHandler;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.sp.spring.SpApplicationContext;

public class MsgJsonServlet extends BasePhoneServlet {

	private MailHandler mm;

	public MsgJsonServlet() {
		mm = SpApplicationContext.getBean(MailHandler.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		boolean newMsgs = getBoolean(req, "new");
		int userId = getInt(req, "userid");
		if (userId == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING, "userid required");
		}

		String resp = mm.getMessagesAsThreads(userId, newMsgs, 20);
		return resp;
	}

	@Override
	protected String getDefaultContentType() {
		return JSON_UTF8_CONTENT_TYPE;
	}

}
