package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.sp.spring.SpApplicationContext;

public class StatusServlet extends BasePhoneServlet {

	private PlayerManager playerManager;

	public StatusServlet() {

		playerManager = SpApplicationContext.getBean(PlayerManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		int userId = getAuthenticatedUserId(req, "userid");
		String status = req.getParameter("status");
		if (userId == 0 || status == null) {
			throw new ShineException(GeneralError.PARAM_MISSING, "status and userid required");
		}
		int result = playerManager.updateStatus(userId, status);
		return "" + result;
	}

	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
