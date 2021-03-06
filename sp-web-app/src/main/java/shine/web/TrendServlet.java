package shine.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import shine.dao.exception.ShineException;

import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.sp.spring.SpApplicationContext;
import com.sp.trending.TrendManager;

public class TrendServlet extends BasePhoneServlet {

	private TrendManager trendManager;

	public TrendServlet() {
		trendManager = SpApplicationContext.getBean(TrendManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		ShineLocation loc = getLocation(req);
		int userId = getAuthenticatedUserIdIfSet(req, "userid");

		int max = getInt(req, "max");
		int since = getInt(req, "since");
		List<ShineObject> latest;
		if (getBoolean(req, "self")) {
			latest = trendManager.getLatestTrendsOnUsersBoards(userId, max, since);

		} else {
			latest = trendManager.getLatestTrends(loc, userId, max, since);
		}
		String s = convertLatest(latest);
		return s;
	}

	private String convertLatest(List<ShineObject> latest) {
		if (latest != null) {
			StringBuilder sb = new StringBuilder("<trending>");
			for (ShineObject shineObject : latest) {
				sb.append(getXml(shineObject));
			}
			sb.append("</trending>");
			return sb.toString();
		}
		return null;
	}

}
