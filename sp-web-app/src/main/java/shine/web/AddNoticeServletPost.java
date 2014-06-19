package shine.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;
import org.joda.time.DateMidnight;
import org.joda.time.DateTime;

import shine.app.NoticeManager;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.Notice;
import com.shine.boards.NoticeCategory;
import com.sp.img.ImageScaler;

public class AddNoticeServletPost extends AbstractPostServlet {

	protected Logger log = Logger.getLogger(AddNoticeServletPost.class);

	private NoticeManager noticeManager;

	private ImageScaler imageScaler;

	AddNoticeServletPost(NoticeManager noticeManager, ImageScaler imageScaler) {
		this.noticeManager = noticeManager;
		this.imageScaler = imageScaler;
	}

	int createNotice(HttpServletRequest req) throws ShineException {

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);

		Notice n = null;
		Noticeholder holder = null;
		if (isMultipart) {
			holder = extractNoticeFromPost(req);
			n = holder.getNotice();
		} else {
			n = createNoticeFromGet(req);
		}

		if (n.getTitle() == null || n.getCreatorId() == null || n.getCreatorId() == 0 || n.getBoardId() == 0) {
			return GeneralError.PARAM_MISSING.getCode();
		}
		if (n.getExpires() == null) {
			n.setExpires(getDefaultExpiry());
		}

		if (isMultipart) {

			return noticeManager.createNoticeWithImage(n, holder.getBufferedImage());

		} else {
			return noticeManager.createNotice(n);
		}
	}

	class Noticeholder {

		private Notice notice;
		private BufferedImage bufferedImage;

		public Noticeholder(Notice notice, BufferedImage bufferedImage) {

			this.notice = notice;
			this.bufferedImage = bufferedImage;
		}

		public Notice getNotice() {
			return notice;
		}

		public BufferedImage getBufferedImage() {
			return bufferedImage;
		}

	}

	private Noticeholder extractNoticeFromPost(HttpServletRequest req) throws ShineException {

		ServletFileUpload upload = new ServletFileUpload();

		String notice = null;
		String title = null;
		log.debug("Incoming multipart notice");

		int catid = 0;
		int creatorId = 0;
		int boardId = 0;

		Date expires = null;

		BufferedImage bufferedImage = null;

		try {
			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();

				InputStream stream = item.openStream();
				if ("userfile".equals(name)) {

					noticeManager.testMaxSize(stream);
					// imagebytes = WebAppUtils.streamToBytes(stream);
					bufferedImage = imageScaler.scaleImage(stream, ShineProperties.maxImageDimension());
				} else if (item.isFormField()) {

					String value = Streams.asString(stream);
					log.debug("process: putting param: " + name + "=" + value);
					if (name.equals("notice")) {
						notice = value;
					} else if (name.equals("title")) {
						title = value;
					} else if (name.equals("creatorid")) {
						creatorId = Integer.parseInt(value);
					} else if (name.equals("authtoken")) {
						creatorId = getUserIdFromToken(req);
					} else if (name.equals("boardid")) {
						boardId = Integer.parseInt(value);
					} else if (name.equals("expires")) {
						expires = getDate(value);
					} else if (name.equals("expiry")) {
						expires = getExpiryDate(value);
					} else if (name.equals("catid")) {
						catid = Integer.parseInt(value);
					} else if (name.equals("cat")) {
						NoticeCategory categ = NoticeCategory.getCat(value);
						catid = categ.getId();
					}
				}
			}
		} catch (IOException ioe) {
			log.debug("error reading upload", ioe);
			throw new ShineException(ioe);
		} catch (FileUploadException e) {
			log.debug("error reading upload", e);
			throw new ShineException(e);
		}

		Notice n = new Notice(boardId, creatorId, title, notice, catid, expires);

		return new Noticeholder(n, bufferedImage);
	}

	private Notice createNoticeFromGet(HttpServletRequest req) {

		String notice = req.getParameter("notice");
		String title = req.getParameter("title");
		log.debug("Incoming notice is: title: " + title + "'; notice: " + notice);

		int catid = getCategoryId(req);
		int creatorId = getInt(req, "creatorid");
		int boardId = getInt(req, "boardid");
		Date expires = getDate(req, "expires");
		Notice n = new Notice(boardId, creatorId, title, notice, catid, expires);

		return n;
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

	private Date getExpiryDate(String expiry) {

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

}
