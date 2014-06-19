package shine.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.app.AdManager;
import shine.app.NoticeManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.Advert;
import com.shine.boards.Notice;
import com.shine.xml.AdvertXml;
import com.shine.xml.NoticeXml;
import com.sp.spring.SpApplicationContext;

public class AjaxBoardSearch extends BasePhoneServlet {

	private static final int MAX_RESULTS = 8;
	private static long HALF_DAY = 12 * 60 * 60 * 1000;
	private NoticeManager ndao;
	private AdManager adManager;
	private static Map<String, String> cache = new HashMap<String, String>();
	private long lastCleared;

	@Override
	public void init() throws ServletException {
		super.init();
		ndao = SpApplicationContext.getBean(NoticeManager.class);
		adManager = SpApplicationContext.getBean(AdManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		synchronized (cache) {
			if (lastCleared < System.currentTimeMillis() - HALF_DAY) {
				cache.clear();
				lastCleared = System.currentTimeMillis();
			}
		}
		String s = req.getParameter("s");
		int t = getInt(req, "t");
		int boardid = getInt(req, "bid");
		int userid = getInt(req, "u");
		if (boardid == 0 || t == 0 || StringUtils.isEmpty(s) || (t >= SharedConstants.ADVERTS_ONLY && userid < 1)) {
			throw new ShineException(GeneralError.PARAM_MISSING);
		}
		String key = boardid + ":" + t + ":" + s;
		if (cache.containsKey(key)) {
			return cache.get(key);
		}
		StringBuilder sb = new StringBuilder(XML_DECL);
		sb.append("<x>");
		int resultsToFind = MAX_RESULTS;

		if (t >= SharedConstants.ADVERTS_ONLY) {
			// add advert search results
			List<Advert> ads = adManager.advertSearch(boardid, s, MAX_RESULTS);

			sb.append(formatAds(ads, MAX_RESULTS));
			resultsToFind -= ads.size();
			t = t - SharedConstants.ADVERTS_ONLY;
		}
		if (t > 0 && resultsToFind > 0) {
			List<Notice> nb = ndao.getNoticesSearch(boardid, s, t, resultsToFind);
			sb.append(formatNotices(nb, MAX_RESULTS));
		}

		sb.append("</x>");

		String ret = sb.toString();
		cache.put(key, ret);

		return ret;
	}

	private Object noticeSearch(int boardid, String s) {
		// TODO Auto-generated method stub
		return null;
	}

	private Object formatAds(List<Advert> ads, int maxResults) {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		for (Advert ad : ads) {
			if (i++ == maxResults) {
				break;
			}
			sb.append(new AdvertXml(ad).toXml());
		}

		return sb.toString();
	}

	private String formatNotices(List<Notice> nb, int max) {
		StringBuilder sb = new StringBuilder();
		int i = 0;
		for (Notice notice : nb) {
			sb.append(new NoticeXml(notice).toXml());
			if (i++ == max) {
				break;
			}
		}

		return sb.toString();
	}

}
