package shine.web;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import shine.dao.exception.ShineException;

import com.shine.Util;
import com.shine.objects.ShineLocation;
import com.sp.auth.AuthService;
import com.sp.auth.SpUnauthorisedException;
import com.sp.spring.SpApplicationContext;

public abstract class BaseServlet extends HttpServlet {

	protected Logger log = Logger.getLogger(BaseServlet.class);

	private AuthService authService;

	protected BaseServlet() {
		authService = SpApplicationContext.getBean(AuthService.class);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
//		Locale loc = req.getLocale();
//		System.out.println(loc);
//		ResourceBundle rb = ResourceBundle.getBundle("mybundle", loc);
//		System.out.println(rb.getString("com.taglib.weblog.Greeting.greeting"));
//		System.out.println(rb.getString("com.onlyindefault"));
		doPost(req, res);
	}

	/**
	 * Returns the xml string to return or throws a shineexception with error
	 * code
	 * 
	 * @param req
	 * @return
	 */
	protected abstract String doResponse(HttpServletRequest req) throws ShineException;

	protected ShineLocation getLocation(HttpServletRequest req) {
		return getLocation(req, "lat", "lng");
	}

	protected ShineLocation getUserLocation(HttpServletRequest req) {
		return getLocation(req, "ulat", "ulng");
	}

	private ShineLocation getLocation(HttpServletRequest req, String latParam, String lngParam) {

		if (req.getParameter(latParam) != null && req.getParameter(lngParam) != null) {
			double lat = getDouble(req, latParam);
			double lng = getDouble(req, lngParam);

			ShineLocation loc = new ShineLocation(lat, lng);
			return loc;
		}

		return null;
	}

	protected String getPhoneNo(HttpServletRequest req) {
		String pstr = req.getParameter("phone");
		return pstr;
	}

	protected int getObjectId(HttpServletRequest req) {
		return getInt(req, "oid");
	}

	protected String getParam(HttpServletRequest req, String param, int maxlength) {
		String value = req.getParameter(param);
		if (value != null && value.length() > maxlength) {
			value = value.substring(0, maxlength);
		}
		return value;
	}

	protected Double getOptionalDouble(HttpServletRequest req, String parameter) {
		String val = req.getParameter(parameter);
		if (val == null) {
			return null;
		}
		Double d = Double.parseDouble(val);
		return d;
	}

	protected double getDouble(HttpServletRequest req, String parameter) {
		return getDouble(req, parameter, 0);
	}

	protected Integer getOptionalInt(HttpServletRequest req, String parameter) {
		String val = req.getParameter(parameter);
		if (val == null) {
			return null;
		}
		try {
			Integer i = Integer.parseInt(val);
			return i;
		} catch (NumberFormatException e) {
			return null;
		}
	}

	protected int getInt(HttpServletRequest req, String parameter) {
		String val = req.getParameter(parameter);
		if (val == null) {
			return 0;
		}
		try {
			int i = Integer.parseInt(val);
			return i;
		} catch (NumberFormatException e) {
			return 0;
		}
	}

	protected double getDouble(HttpServletRequest req, String parameter, double defaultValue) {
		String val = req.getParameter(parameter);
		if (val == null) {
			return defaultValue;
		}
		try {
			double d = Double.parseDouble(val);
			return d;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	protected Integer getIntOrNull(HttpServletRequest req, String parameter) {
		String val = req.getParameter(parameter);
		if (val == null) {
			return null;
		}
		try {
			return new Integer(val);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	protected long getLong(HttpServletRequest req, String parameter) {
		String val = req.getParameter(parameter);
		if (val == null) {
			return 0;
		}
		try {
			long i = Long.parseLong(val);
			return i;
		} catch (NumberFormatException e) {
			return 0;
		}

	}

	protected boolean getBoolean(HttpServletRequest req, String parameter) {
		String val = req.getParameter(parameter);
		if (StringUtils.isEmpty(val)) {
			return false;
		}
		if (val.equals("1") || val.equalsIgnoreCase("t")
				|| val.equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	protected boolean getBoolean(HttpServletRequest req, String parameter, boolean deflt) {
		String val = req.getParameter(parameter);
		if (StringUtils.isEmpty(val)) {
			return deflt;
		}
		if (val.equals("1") || val.equalsIgnoreCase("t")
				|| val.equalsIgnoreCase("true")) {
			return true;
		}
		return false;
	}

	protected Date getDate(HttpServletRequest req, String parameter) {
		String val = req.getParameter(parameter);
		if (val == null) {
			return null;
		}
		return getDate(val);
	}

	protected Date getDate(String string) {

		Date d = null;
		try {
			d = Util.datadf.parse(string);
		} catch (ParseException e) {
			log("failed date parsing", e);
		}
		return d;
	}

	protected void log(String msg, Exception e) {
		log.warn(msg, e);
	}

	protected int getAuthenticatedUserId(HttpServletRequest req, String userIdParamName) {

		if (req.getParameter("authtoken") != null) {
			return getUserIdFromToken(req);

		}
		if (req.getParameter(userIdParamName) == null) {
			throw new SpUnauthorisedException();
		}
		return getInt(req, userIdParamName);
	}

	protected int getUserIdFromToken(HttpServletRequest req) {
		return getUserIdFromToken(req.getParameter("authtoken"));
	}

	protected int getUserIdFromToken(String token) {
		Long lg = authService.getUserIdFromToken(token);
		if (lg == null) {
			throw new SpUnauthorisedException();
		}
		int userId = lg.intValue();
		return userId;
	}

	protected int getAuthenticatedUserIdIfSet(HttpServletRequest req, String userIdParamName) {

		try {
			return getAuthenticatedUserId(req, userIdParamName);
		} catch (SpUnauthorisedException e) {
			// do nothing just return 0
			return 0;
		}
	}
}
