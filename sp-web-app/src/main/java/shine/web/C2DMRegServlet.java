package shine.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.sp.spring.SpApplicationContext;

public class C2DMRegServlet extends BasePhoneServlet {

	private PlayerManager playerManager;

	public C2DMRegServlet() {
		playerManager = SpApplicationContext.getBean(PlayerManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		int userId = getAuthenticatedUserId(req, "userid");
		String c2dmKey = req.getParameter("ck");
		if (userId == 0 || StringUtils.isEmpty(c2dmKey)) {
			log.error("userId " + userId + " submitted c2dmkey" + c2dmKey);
			//something not right but return nothing, this is seamless to user
			return "";
		}
		playerManager.updateC2dmKey(userId, c2dmKey);

		return "";
	}

	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
