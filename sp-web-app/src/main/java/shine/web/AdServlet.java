package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.AdManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.Advert;
import com.shine.xml.AdvertXml;
import com.sp.spring.SpApplicationContext;

public class AdServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;
	private AdManager manager;

	public AdServlet() {
		manager = SpApplicationContext.getBean(AdManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int adid = getInt(req, "adid");
		if (adid > 0) {
			StringBuilder sb = new StringBuilder(XML_DECL);
			sb.append(getAd(adid));
			return sb.toString();
		}

		throw new ShineException(GeneralError.PARAM_MISSING);
	}

	private String getAd(int adid) throws ShineException {

		Advert dto = manager.retrieveAd(adid);

		return new AdvertXml(dto).toXml();
	}

}
