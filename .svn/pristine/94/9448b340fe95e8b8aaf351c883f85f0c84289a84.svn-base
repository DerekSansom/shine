package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.SavedAdsManager;
import shine.app.track.TrackingManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.sp.spring.SpApplicationContext;

public class SaveServlet extends BasePhoneServlet {

	private static final int UNSAVE_ALL = 1;

	private SavedAdsManager savedAdsManager;

	public SaveServlet() {
		savedAdsManager = SpApplicationContext.getBean(SavedAdsManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getInt(req, "userid");
		int adid = getInt(req, "adid");
		int deleteid = getInt(req, "deleteadid");
		int action = getInt(req, "action");
		int client = getInt(req, "c");

		if (userid == 0 || (adid == 0 && deleteid == 0 && (action != UNSAVE_ALL))) {
			throw new ShineException(GeneralError.PARAM_MISSING,
					"userid and one of adid or deleteadid, or deleteAll required");
		}
		int ret = GeneralError.SYSTEM_ERROR.getCode();
		TrackingManager.trackSaveAd(userid, adid, deleteid, action, client);
		if (adid > 0) {
			ret = savedAdsManager.saveAd(userid, adid);
		} else if (deleteid > 0) {

			ret = savedAdsManager.unsaveAd(userid, deleteid);
		} else if (action == UNSAVE_ALL) {
			ret = savedAdsManager.unsaveAll(userid);
		}

		if (ret != SharedConstants.SUCCESS) {
			throw new ShineException(ret);
		}
		return "" + ret;
	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
