package com.sp.portal;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import shine.dao.exception.ShineException;

import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Component
public class PortalControllerHelper {
	
	private static final Logger logger = LoggerFactory.getLogger(PortalControllerHelper.class);


	public int getUserId(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}

	public boolean isAdmin(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		return user.hasRole(Role.ADMIN);
	}
	
	public int calculateStart(Integer start) {
		if (start == null || start.intValue() < 1) {
			return 1;
		}
		return start;
	}

	public BufferedImage getBufferedImageFromBytes(byte[] bytes) throws IOException {
		BufferedImage retBufferedImage = null;
		InputStream in = new ByteArrayInputStream(bytes);
		retBufferedImage = ImageIO.read(in);
		return(retBufferedImage);
	}
	
	public String saveImage(BufferedImage bufferedImage, String folderpath) throws ShineException {

		UUID sId = UUID.randomUUID();
		File f = new File(folderpath + "/" + sId + ".png");

		try {
			ImageIO.write(bufferedImage, "png", f);

			logger.debug("saved image id: " + sId + ", image written to " + f.getAbsolutePath());
		} catch (IOException e) {
			logger.error("Saving image", e);
			throw new ShineException(e);
		}
		return(String.valueOf(sId));
	}
}

