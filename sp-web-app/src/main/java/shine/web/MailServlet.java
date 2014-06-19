package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.MailHandler;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.sp.spring.SpApplicationContext;

public class MailServlet extends BasePhoneServlet {

	private MailHandler mm;

	public MailServlet() {
		mm = SpApplicationContext.getBean(MailHandler.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int box = getInt(req, "box");
		int userId = getInt(req, "userid");
		int start = getInt(req, "start");
		if (userId == 0 || box == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING, "userid and box required");
		}

		StringBuilder resp = new StringBuilder(XML_DECL);
		resp.append(mm.getMail(box, userId, start, ShineProperties.getDEFAULT_MAILS_TO_RETRIEVE()));
		return resp.toString();
	}

}
