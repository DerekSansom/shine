package shine.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.Util;
import com.shine.objects.Player;
import com.sp.spring.SpApplicationContext;

public class RegisterServlet extends BasePhoneServlet {

	public static final SimpleDateFormat dobdf = new SimpleDateFormat("yyyy-MM-dd");

	private PlayerManager playerManager;

	public RegisterServlet() {
		playerManager = SpApplicationContext.getBean(PlayerManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		Player user = createUser(req);
		int t = getInt(req, "t");
		String client = req.getParameter("c");
		boolean v1 = false;
		if (StringUtils.isNotEmpty(client) && "7".equals(client)) {
			v1 = true;
		}

		String result = playerManager.registerOrUpdatePlayer(user, t == 1, v1);
		return result;
	}

	private Player createUser(HttpServletRequest req) {
		Player user = new Player();
		user.setId(getInt(req, "id"));
		user.setPassword(req.getParameter("password"));
		user.setPhone(req.getParameter("phone"));
		user.setEmail(req.getParameter("email"));
		user.setForename(req.getParameter("fullname"));
		user.setGender(req.getParameter("gender"));
		user.setOffers(req.getParameter("offers"));
		user.setUsername(req.getParameter("username"));
		user.setStatus(req.getParameter("status"));
		user.setBiog(req.getParameter("biog"));
		user.setAvatar(getInt(req, "avatar"));
		Date newDob = getDob(req);
		user.setDob(newDob);
		user.setClient(getInt(req, "c"));
		user.setPlatform(getParam(req, "platform", 20));
		user.setOs(getParam(req, "os", 10));
		user.setVersion(getParam(req, "version", 10));
		return user;
	}

	private Date getDob(HttpServletRequest req) {

		String str = req.getParameter("dob");
		if (str != null) {
			try {
				return Util.datadf.parse(str);
			} catch (ParseException e) {
				log.warn("cannot parse dob with datadf date: " + str, e);
			}
			try {
				return dobdf.parse(str);
			} catch (ParseException e) {
				log.warn("cannot parse dob with dobdf date: " + str, e);
			}

		}

		return null;
	}

	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
