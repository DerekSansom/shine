package com.sp.locations;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UrlUtils {

	private static Logger log = LoggerFactory.getLogger(UrlUtils.class);

	InputStream getInputStream(String urlStr) {
		return getInputStream(urlStr, false);
	}

	InputStream getInputStream(String urlStr, boolean printString) {

		try {
			URL uRL = new URL(urlStr);

			URLConnection ucon = uRL.openConnection();
			InputStream is = ucon.getInputStream();

			if (printString) {
				// TODO: just return is should be enough
				String response = convertStreamToString(is);
				System.out.println(response);
				ByteArrayInputStream bais = new ByteArrayInputStream(response.getBytes());
<<<<<<< HEAD
				return bais;
=======
			return bais;
>>>>>>> 482eb53fad7036601388e8d65c7f5a704415ab05
			} else {
				return is;
			}

		} catch (Throwable e) {
			log.warn("Could not reach google apis" + e.getMessage());
		}
		return null;
	}

	private static String convertStreamToString(InputStream is) {
		/*
<<<<<<< HEAD
		 * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
=======
		 * To convert the InputStream to String we use the BufferedReader.readLine() method. We iterate until the BufferedReader return null
		 * which means there's no more data to read. Each line will appended to a StringBuilder and returned as String.
>>>>>>> 482eb53fad7036601388e8d65c7f5a704415ab05
		 */
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
		return sb.toString();
	}

<<<<<<< HEAD
}
=======
}
>>>>>>> 482eb53fad7036601388e8d65c7f5a704415ab05
