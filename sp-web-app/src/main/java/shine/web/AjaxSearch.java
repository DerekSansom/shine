package shine.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.app.BoardSearchManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.NoticeBoard;
import com.shine.objects.ShineLocation;
import com.shine.xml.NoticeBoardXml;
import com.sp.spring.SpApplicationContext;

public class AjaxSearch extends BasePhoneServlet {

	private static final int MAX_RESULTS = 8;
	private static long HALF_DAY = 12 * 60 * 60 * 1000;
	private BoardSearchManager boardManager;
	private static Map<String, String> cache = new HashMap<String, String>();
	private long lastCleared;
	public static final int SEARCH_TYPE_ALL = 1;
	public static final int SEARCH_BOARD = 1;
	public static final int SEARCH_USER = 1;
	public static final int SEARCH_BRAND = 1;
	public static final int SEARCH_LOCATION = 1;

	@Override
	public void init() throws ServletException {
		super.init();
		boardManager = SpApplicationContext.getBean(BoardSearchManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		synchronized (cache) {
			if (lastCleared < System.currentTimeMillis() - HALF_DAY) {
				cache.clear();
				lastCleared = System.currentTimeMillis();
			}
		}
		ShineLocation loc = getLocation(req);
		String s = req.getParameter("s");
		int t = getInt(req, "t");
		int userid = getInt(req, "u");
		if (t == 0 || StringUtils.isEmpty(s) || (t >= SharedConstants.ADVERTS_ONLY && userid < 1)) {
			throw new ShineException(GeneralError.PARAM_MISSING);
		}
		String key = loc + ":" + t + ":" + s;
		if (cache.containsKey(key)) {
			return cache.get(key);
		}
		StringBuilder sb = new StringBuilder(XML_DECL);
		sb.append("<x>");
		int resultsToFind = MAX_RESULTS;

		if (t > 0 && resultsToFind > 0) {
			List<NoticeBoard> nb = boardManager.getBoardSearch(loc, s, t, resultsToFind);
			sb.append(formatBoards(nb, MAX_RESULTS));
		}

		sb.append("</x>");

		String ret = sb.toString();
		cache.put(key, ret);

		return ret;
	}

	private String formatBoards(List<NoticeBoard> nb, int max) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (NoticeBoard notice : nb) {
			sb.append(new NoticeBoardXml(notice).toXml());
			if (i++ == max) {
				break;
			}
		}

		return sb.toString();
	}

}
