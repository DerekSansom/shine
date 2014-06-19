package shine.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

import com.shine.boards.AdCategory;

public class WebAppUtils {
	private static String characters = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private static final Random rng = new Random();

	public static Integer[] getUserCategories(String userOffers) {

		Integer[] ret = new Integer[0];
		if (StringUtils.isNotEmpty(userOffers)) {

			ArrayList<Integer> ints = new ArrayList<Integer>();
			char[] chars = userOffers.toCharArray();
			for (char c : chars) {
				AdCategory ac = AdCategory.getCat(c);
				ints.add(ac.getId());

			}

			ret = ints.toArray(ret);
		}
		return ret;
	}

	public static String generatetoken(int length) {
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng.nextInt(characters.length()));
		}
		return new String(text);
	}

	// public static Date getPastDate(int daysInPast) {
	//
	// GregorianCalendar cal = new GregorianCalendar();
	// cal.add(Calendar.DATE, -daysInPast);
	// return cal.getTime();
	// }

	public static Date getDateInPast(int days) {
		long daysInPast = (long) days * 1000l * 60l * 60l * 24l;
		Date d = new Date(System.currentTimeMillis() - daysInPast);
		return d;

	}

	public static ByteArrayOutputStream streamToBytes(InputStream is) throws IOException {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];

		while ((nRead = is.read(data, 0, data.length)) != -1) {
			buffer.write(data, 0, nRead);
		}

		buffer.flush();

		return buffer;

	}

	public static Date getTimeInPast(long secondssince) {
		long millis = secondssince * 1000l;
		Date d = new Date(System.currentTimeMillis() - millis);
		return d;
	}

}
