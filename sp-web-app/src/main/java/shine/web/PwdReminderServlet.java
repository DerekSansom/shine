package shine.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.app.PasswordManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.sp.spring.SpApplicationContext;

public class PwdReminderServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;

	private PasswordManager passwordManager;

	public PwdReminderServlet() {
		passwordManager = SpApplicationContext.getBean(PasswordManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		return sendReminder(req);
	}

	private String sendReminder(HttpServletRequest req) throws ShineException {

		String cred1 = req.getParameter("cred");
		if (StringUtils.isEmpty(cred1)) {
			throw new ShineException(GeneralError.PARAM_MISSING, "cred or phone required");
		}

		passwordManager.sendPasswordEmail(cred1);

		return "" + SharedConstants.SUCCESS;

	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
