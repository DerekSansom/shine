package shine.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.log4j.Logger;

import shine.app.ImageHandler;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.sp.img.ImageScaler;
import com.sp.spring.SpApplicationContext;

public class ImageUploadServlet extends BaseServlet {

	private ImageHandler handler;

	private ImageScaler imageScaler;


	protected Logger log = Logger.getLogger(BasePhoneServlet.class);

	public ImageUploadServlet() {
		handler = SpApplicationContext.getBean(ImageHandler.class);
		imageScaler = SpApplicationContext.getBean(ImageScaler.class);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		int resp;
		try {
			resp = doImgResponse(req);
			if (resp == SharedConstants.SUCCESS) {
				res.setStatus(200);
			}
			log.debug("ImageUploadServlet sending response OK");

		} catch (ShineException e) {
			log.warn("Failed to upload img", e);
			res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		} catch (Exception e) {
			log.error("Failed to upload img", e);
			res.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
		}

	}

	protected int doImgResponse(HttpServletRequest req) throws ShineException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {
			return process(req);

		}
		throw new ShineException(GeneralError.PARAM_MISSING, "requires a multipart post with image");
	}

	private int process(HttpServletRequest req) throws ShineException {

		ServletFileUpload upload = new ServletFileUpload();
		log.debug("ImageUploadServlet process");

		// Parse the request

		Map<String, String> params = new HashMap<String, String>();

		BufferedImage bufferedImage = null;
		try {
			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();

				InputStream stream = item.openStream();
				if ("userfile".equals(name)) {
					// handler.testMaxSize(stream);

					bufferedImage = imageScaler.scaleImage(stream, ShineProperties.maxImageDimension());

				} else if (item.isFormField()) {
					String value = Streams.asString(stream);
					log.debug("process: putting param: " + name + "=" + value);
					params.put(name, value);
				}


			}
		} catch (IOException ioe) {
			log.debug("error reading upload", ioe);
			throw new ShineException(ioe);
		} catch (FileUploadException e) {
			log.debug("error reading upload", e);
			throw new ShineException(e);
		}
		if (bufferedImage == null) {
			throw new ShineException(GeneralError.PARAM_MISSING, "image not recieved");
		}
		// throw ShineException if too many params set, or id is not int > 0;
		int id = testParamsAndGetId(params);

		// at this point we know only one of the following is set
		if (params.get("userid") != null) {
			log.debug("handling userImg id=" + params.get("userid"));

			handler.handleUserImage(bufferedImage, id);
		} else if (params.get("adid") != null) {
			log.debug("handling adImg id=" + params.get("adid"));
			handler.handleAdImage(bufferedImage, id);
		} else if (params.get("noticeid") != null) {
			log.debug("handling noticeImg id=" + params.get("noticeid"));
			handler.handleNoticeImage(bufferedImage, id);
		} else if (params.get("authtoken") != null) {
			log.debug("handling userImg id=" + params.get("authtoken"));
			id = getUserIdFromToken(params.get("authtoken"));
			handler.handleUserImage(bufferedImage, id);
		}
		return SharedConstants.SUCCESS;
	}

	private int getIdFromToken(String string) {
		// TODO Auto-generated method stub
		return 0;
	}

	private int testParamsAndGetId(Map<String, String> params) throws ShineException {
		String userid = params.get("userid");
		String adid = params.get("adid");
		String noticeid = params.get("noticeid");
		int paramCount = 0;

		String paramid = null;

		if (userid != null) {
			paramCount++;
			paramid = userid;
		}
		if (adid != null) {
			paramCount++;
			paramid = adid;

		}
		if (noticeid != null) {
			paramCount++;
			paramid = noticeid;
		}
		if (paramCount != 1) {
			throw new ShineException(GeneralError.PARAM_MISSING,
					"one and only one of userid, adid or noticeid required");
		}

		try {
			int id = Integer.parseInt(paramid);
			return id;
		} catch (NumberFormatException e) {
			throw new ShineException(GeneralError.PARAM_MISSING,
					"one and only one of userid, adid or noticeid required");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) {
		log.debug("ImageUploadServlet sending response OK");

	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		// TODO Auto-generated method stub
		return null;
	}

}
