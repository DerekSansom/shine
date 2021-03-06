package shine.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import shine.app.ImageHandler;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.sp.img.ImageScaler;

public abstract class AbstractPostServlet extends BasePhoneServlet {

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		// not required in a post servlet, so implement here to avoid each
		// subclass having to
		return null;
	}

	protected BufferedImage extractImageBytes(ImageScaler imageScaler, ImageHandler imageHandler, HttpServletRequest req)
			throws ShineException {

		ServletFileUpload upload = new ServletFileUpload();

		try {
			FileItemIterator iter = upload.getItemIterator(req);
			while (iter.hasNext()) {
				FileItemStream item = iter.next();
				String name = item.getFieldName();

				if ("userfile".equals(name)) {
					InputStream stream = item.openStream();
					// if (imageHandler != null) {
					// imageHandler.testMaxSize(stream);
					// }
					return imageScaler.scaleImage(stream, ShineProperties.maxImageDimension());
				}

			}
		} catch (IOException ioe) {
			log.debug("error reading upload", ioe);
			throw new ShineException(ioe);
		} catch (FileUploadException e) {
			log.debug("error reading upload", e);
			throw new ShineException(e);
		}
		return null;
	}

}
