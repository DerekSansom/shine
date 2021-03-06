package shine.web;

import java.awt.image.BufferedImage;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

import shine.app.ImageHandler;
import shine.app.NoticeManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.Notice;
import com.shine.boards.NoticeCategory;
import com.sp.img.ImageScaler;
import com.sp.spring.SpApplicationContext;

public class AddNoticeServlet extends AbstractPostServlet {

	private NoticeManager noticeManager;
	private ImageScaler imageScaler;
	private ImageHandler imageHandler;

	public AddNoticeServlet() {
		noticeManager = SpApplicationContext.getBean(NoticeManager.class);
		imageScaler = SpApplicationContext.getBean(ImageScaler.class);
		imageHandler = SpApplicationContext.getBean(ImageHandler.class);

	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		int ret = createNotice(req);

		return "" + ret;
	}

	private int createNotice(HttpServletRequest req) throws ShineException {

		String notice = req.getParameter("notice");
		String title = req.getParameter("title");
		log.debug("Incoming notice is: title: " + title + "'; notice: " + notice);

		int catid = getCategoryId(req);
		// if token is set but expired this will throw an exception
		int creatorId = getAuthenticatedUserIdIfSet(req, "creatorid");
		int boardId = getInt(req, "boardid");
		Date expires = getExpiryDate(req);


		if (title == null || creatorId == 0 || boardId == 0) {
			//TODO: try to use post service instead
			boolean isMultipart = ServletFileUpload.isMultipartContent(req);
			if (isMultipart) {
				AddNoticeServletPost postServlet = new AddNoticeServletPost(noticeManager, imageScaler);
				return postServlet.createNotice(req);
			}

			return GeneralError.PARAM_MISSING.getCode();
		}
		if (expires == null) {
			expires = getDefaultExpiry();
		}
		Notice n = new Notice(boardId, creatorId, title, notice, catid,
				expires);

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {

			BufferedImage imagebytes = extractImageBytes(imageScaler, imageHandler, req);
			return noticeManager.createNoticeWithImage(n, imagebytes);

		} else {
			return noticeManager.createNotice(n);
		}
	}

	private Date getExpiryDate(HttpServletRequest req) {

		if (req.getParameter("expires") != null) {
			return getDate(req, "expires");
		}
		String expiry = req.getParameter("expiry");
		if (expiry != null) {

			DateTime now = new DateTime();
			switch (expiry) {
			case "tonight":
				DateMidnight midnight = new DateMidnight().plusDays(1);
				return midnight.toDate();
			case "24hours":
				now = now.plusDays(1);
				break;
			case "1week":
				now = now.plusWeeks(1);
				break;
			case "1month":
				now = now.plusMonths(1);
				break;
			case "3months":
			default:
				now = now.plusMonths(3);
			}
			return now.toDate();

		}

		return null;
	}

	private int getCategoryId(HttpServletRequest req) {
		int catid = getInt(req, "catid");
		if (catid > 0) {
			return catid;
		}
		String cat = req.getParameter("cat");
		NoticeCategory categ = NoticeCategory.getCat(cat);
		return categ.getId();

	}

	private Date getDefaultExpiry() {

		Date now = new Date();
		Date d = new Date(now.getTime() + SharedConstants.DEFAULT_EXPIRY);
		return d;
	}

}
