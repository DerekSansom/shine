package shine.web;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;

import shine.app.AdManager;
import shine.app.ImageHandler;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.Advert;
import com.sp.img.ImageScaler;
import com.sp.spring.SpApplicationContext;

public class AddUserAdServlet extends AbstractPostServlet {

	private static final long serialVersionUID = 1L;

	private AdManager adManager;
	private ImageScaler imageScaler;
	private ImageHandler imageHandler;

	public AddUserAdServlet() {
		adManager = SpApplicationContext.getBean(AdManager.class);
		imageScaler = SpApplicationContext.getBean(ImageScaler.class);
		imageHandler = SpApplicationContext.getBean(ImageHandler.class);
	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int ret = createUserAd(req);

		return "" + ret;
	}

	private int createUserAd(HttpServletRequest req) throws ShineException {

		String title = req.getParameter("title");
		String text = req.getParameter("txt");
		String phoneno = req.getParameter("phone");
		String email = req.getParameter("email");
		int userid = getAuthenticatedUserId(req, "userid");
		int catId = getInt(req, "catid");
		Double lat = getOptionalDouble(req, "lat");
		Double lng = getOptionalDouble(req, "lng");
		Date expires = getDate(req, "expires");

		if (title == null || userid == 0) {
			return GeneralError.PARAM_MISSING.getCode();
		}

		if (!bothOrNeitherNull(lat, lng)) {
			return GeneralError.PARAM_MISSING.getCode();
		}

		Advert ad = new Advert(title, text, phoneno, email, expires, catId, userid, lat, lng);

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			try {

				BufferedImage imagebytes = extractImageBytes(imageScaler, imageHandler, req);
				return adManager.createAdWithImage(ad, imagebytes);

			} catch (ShineException e) {
				return e.getCode();
			}
		} else {
			return adManager.createAd(ad);

		}
	}

	private boolean bothOrNeitherNull(Double lat, Double lng) {
		if (lat == null) {
			return lng == null;
		}
		return lng != null;
	}

}
