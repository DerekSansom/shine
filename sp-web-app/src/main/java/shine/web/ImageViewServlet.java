package shine.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.mail.MethodNotSupportedException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

public class ImageViewServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;
	private ServletContext sc;

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		MethodNotSupportedException x = new MethodNotSupportedException();
		throw new ShineException(x);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		String pathInfo = req.getPathInfo();

		// Get the MIME type of the image
		String mimeType = sc.getMimeType(pathInfo);
		if (mimeType == null) {
			sc.log("Could not get MIME type of " + pathInfo);
			res.setStatus(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
			return;
		}

		// Set content type
		res.setContentType(mimeType);
		String fullPath = getFullPath(pathInfo);
		log.debug(fullPath);
		// Set content size
		File file = new File(fullPath);
		if (file.exists()) {

			res.setContentLength((int) file.length());
			// Open the file and output streams
			FileInputStream in = new FileInputStream(file);
			OutputStream out = res.getOutputStream();

			// Copy the contents of the file to the output stream
			byte[] buf = new byte[1024];
			int count = 0;
			while ((count = in.read(buf)) >= 0) {
				out.write(buf, 0, count);
			}
			in.close();
			out.close();
		} else {
			log.debug("ImageViewServlet: " + fullPath + " - does not exist");
		}
	}

	private String getFullPath(String filePath) {

		if (filePath.contains("logo/")) {
			return getOEFilePath(ShineProperties.getLogoimgfolderPath(), filePath);
		} else if (filePath.contains("ad/")) {
			return getOEFilePath(ShineProperties.getAdImageFolderPath(), filePath);
		} else if (filePath.contains("brand/")) {
			return getOEFilePath(ShineProperties.getBoardBGimgfolderPath(), filePath);
		}
		return ShineProperties.getImageFolderPath() + filePath;
	}

	private String getOEFilePath(String folderpath, String filePath) {
		String[] parts = filePath.split("/");
		String fileName = parts[parts.length - 1];
		return folderpath + fileName;
	}

	@Override
	public void init() throws ServletException {
		sc = getServletContext();
		super.init();
	}
}
