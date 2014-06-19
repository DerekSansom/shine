package shine.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.NoticeBoard;
import com.shine.objects.ShineLocation;
import com.shine.xml.NoticeBoardXml;
import com.sp.board.BoardDao;
import com.sp.entity.loc.Location;
import com.sp.locations.LocationsDao;
import com.sp.spring.SpApplicationContext;

public class AjaxLoc extends BasePhoneServlet {

	private static final int MAX_RESULTS = 8;
	private static long HALF_DAY = 12 * 60 * 60 * 1000;
	private BoardDao ndao;
	private LocationsDao ldao;
	private static Map<String, String> cache = new HashMap<String, String>();
	private long lastCleared;

	@Override
	public void init() throws ServletException {
		super.init();
		ndao = SpApplicationContext.getBean(BoardDao.class);

		ldao = SpApplicationContext.getBean(LocationsDao.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		synchronized (cache) {
			if (lastCleared < System.currentTimeMillis() - HALF_DAY) {
				cache.clear();
				lastCleared = System.currentTimeMillis() - HALF_DAY;
			}
		}
		String s = req.getParameter("s");
//		int t = getInt(req, "t");
		ShineLocation loc = getLocation(req);
		if (StringUtils.isEmpty(s)) {
			throw new ShineException(GeneralError.PARAM_MISSING);
		}
		String key = s;
		if (cache.containsKey(key)) {
			return cache.get(key);
		}
		StringBuilder sb = new StringBuilder(XML_DECL);
		sb.append("<l>");
		List<Location> locs = ldao.getLocations(s);
		sb.append(formatLocs(locs, MAX_RESULTS));

		sb.append("</l>");

		String ret = sb.toString();
		cache.put(key, ret);

		return ret;
	}

	private Object formatLocs(List<Location> locs, int maxResults) {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for (Location location : locs) {
			if (i++ == maxResults) {
				break;
			}
			sb.append("<loc name=\"").append(location.toString())
					.append("\" lat=\"").append(location.getLat())
					.append("\" lng=\"").append(location.getLng())
					.append("\"/>");
		}

		return sb.toString();
	}

	private String formatBoards(List<NoticeBoard> nb, int max) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (NoticeBoard board : nb) {
			NoticeBoardXml xml = new NoticeBoardXml(board);
			sb.append(xml.toXmlLite());
			if (i++ == max) {
				break;
			}
		}

		return sb.toString();
	}

}
