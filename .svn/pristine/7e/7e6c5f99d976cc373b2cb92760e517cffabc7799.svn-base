package shine.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.app.PasswordManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.sp.spring.SpApplicationContext;

public class PwdResetServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;

	private PasswordManager passwordManager;

	public PwdResetServlet() {
		passwordManager = SpApplicationContext.getBean(PasswordManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		return resetPassword(req);
	}

	private String resetPassword(HttpServletRequest req) throws ShineException {
		String cred1 = req.getParameter("cred");
		String oldPassword = req.getParameter("token");
		String newPassword = req.getParameter("newpwd");
		if (StringUtils.isEmpty(cred1) || StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)) {
			throw new ShineException(GeneralError.PARAM_MISSING, "cred, token, and newpwd required");
		}

		passwordManager.resetPassword(cred1, oldPassword, newPassword);

		return "" + SharedConstants.SUCCESS;

	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
