package shine.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import shine.app.SavedAdsManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.Advert;
import com.shine.xml.AdvertXml;
import com.sp.spring.SpApplicationContext;

public class SavedAdsServlet extends BasePhoneServlet {

	private SavedAdsManager savedAdsManager;

	public SavedAdsServlet(){
		savedAdsManager = SpApplicationContext.getBean(SavedAdsManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getAuthenticatedUserId(req, "userid");
		int client = getInt(req, "c");

		if (userid == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING, "userid required");
		}

		List<Advert> list = savedAdsManager.getSavedAds(userid, client);
		StringBuilder ret = new StringBuilder(XML_DECL);
		ret.append("<ads>");
		for (Advert advert : list) {
			ret.append(new AdvertXml(advert).toXml());
		}
		ret.append("</ads>");
		return ret.toString();
	}

}
