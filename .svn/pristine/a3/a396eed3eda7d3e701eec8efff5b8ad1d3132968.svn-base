package com.sp.img;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.stereotype.Component;

@Component
public class ImageScaler {

	public BufferedImage scaleImage(InputStream imageInputStream, int maxDimension) throws IOException {

		BufferedImage imBuff = ImageIO.read(imageInputStream);
		BufferedImage output = Scalr.resize(imBuff, maxDimension);
		return output;
	}

}
