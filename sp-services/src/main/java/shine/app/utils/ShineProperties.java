package shine.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShineProperties {

	public static final String MIN_ADS_TO_SERVE = "min.ads.to.serve";
	private static Logger log = LoggerFactory.getLogger(ShineProperties.class);
	private static Properties props = new Properties();
	private static String imgfolderPath;
	private static String oeimgfolderPath;
	private static String usrimgfolderPath;
	private static String postimgfolderPath;
	private static String adimgfolderPath;
	private static String domainUrl;
	private static String boardBGimgfolderPath;
	private static String brandBGfolderPath;;
	private static String logoimgfolderPath;
	private static String SMTP_USER;
	private static String SMTP_PASSWORD;
	private static String SMTP_HOST;
	private static String PWD_REMINDER_FROM_NAME;
	private static String PWD_REMINDER_FROM_ADDRESS;
	private static String ACCESS_CONTROL_ALLOW_ORIGIN;

	private static int MAX_METRES_FROM_USER_CREATE_BOARD = 500;
	private static int MIN_METRES_FROM_EXISTING_BOARD_CREATE = 500;
	private static int DEFAULT_MAILS_TO_RETRIEVE = 0;
	private static int MAX_IMG_SIZE = 10000000;
	private static int MAX_IMG_DIMENSION = 800;
	private static int BOARD_LOC_UPDATE_INTERVAL = 60 * 60 * 4;
	private static int DEFAULT_NOTICES_COUNT = 20;
	private static int DEFAULT_REPLIES_COUNT = 20;

	public static final int MILLISECS_PLAYER_LOC_VALID = 15 * 60000; // 2 hours
	public static final String TREND_DEFAULT_MAX_DISTANCE = "trend.default_max_distance";
	public static final String TREND_DEFAULT_MAX_AGE = "trend.default_max_age";
	public static final String TREND_DEFAULT_MAX_COUNT = "trend.default_max_count";

	public static final String allowJsonFrom = "allow.json.Access-Control-Allow-Origin";

	static {
	}

	public static void load(String filepath) {

		File f = new File(filepath);
		log.debug("Loading properties from: " + f.getAbsolutePath());
		try {
			InputStream is = new FileInputStream(f);
			props.load(is);
			imgfolderPath = props.getProperty("image.folder.path");
			oeimgfolderPath = props.getProperty("oe.image.folder.path");
			usrimgfolderPath = imgfolderPath + props.getProperty("image.folder.user");
			postimgfolderPath = imgfolderPath + props.getProperty("image.folder.post");
			adimgfolderPath = oeimgfolderPath + props.getProperty("image.folder.ad");
			boardBGimgfolderPath = oeimgfolderPath + props.getProperty("image.folder.board.bg");
			logoimgfolderPath = oeimgfolderPath + props.getProperty("image.folder.logo");
			brandBGfolderPath = oeimgfolderPath + props.getProperty("image.folder.brand.bg");
			domainUrl = props.getProperty("domain.url");

			MAX_METRES_FROM_USER_CREATE_BOARD = getIntProperty("shine.createboard.max_metres_from_user",
					MAX_METRES_FROM_USER_CREATE_BOARD);
			MIN_METRES_FROM_EXISTING_BOARD_CREATE = getIntProperty("shine.createboard.min_metres_to_existing_board",
					MIN_METRES_FROM_EXISTING_BOARD_CREATE);
			DEFAULT_MAILS_TO_RETRIEVE = getIntProperty("shine.createboard.default_mails_to_return",
					DEFAULT_MAILS_TO_RETRIEVE);
			DEFAULT_REPLIES_COUNT = getIntProperty("shine.default_replies_count",
					DEFAULT_REPLIES_COUNT);
			DEFAULT_NOTICES_COUNT = getIntProperty("shine.default_notices_count",
					DEFAULT_NOTICES_COUNT);
			SMTP_USER = props.getProperty("shine.smtp.user");
			SMTP_HOST = props.getProperty("shine.smtp.host");
			SMTP_PASSWORD = props.getProperty("shine.smtp.pwd");
			PWD_REMINDER_FROM_NAME = props.getProperty("shine.pwd.fromname");
			PWD_REMINDER_FROM_ADDRESS = props.getProperty("shine.pwd.fromaddr");
			BOARD_LOC_UPDATE_INTERVAL = getIntProperty("shine.board.loc_update_interval",
					BOARD_LOC_UPDATE_INTERVAL);
			ACCESS_CONTROL_ALLOW_ORIGIN = props.getProperty(allowJsonFrom);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int getIntProperty(String key, int defaultValue) {
		String prop = props.getProperty(key);
		if (prop != null) {
			try {
				int i = Integer.parseInt(prop);
				return i;

			} catch (Exception e) {
				log.debug("failed to set int property: " + key, e);
			}
		}

		return defaultValue;
	}

	public static String getProperty(String key, String defaultValue) {
		return props.getProperty(key, defaultValue);
	}

	public static int getMAX_METRES_FROM_USER_CREATE_BOARD() {
		return MAX_METRES_FROM_USER_CREATE_BOARD;
	}

	public static int getMIN_METRES_FROM_EXISTING_BOARD_CREATE() {
		return MIN_METRES_FROM_EXISTING_BOARD_CREATE;
	}

	// public static String getDbUser() {
	// return props.getProperty("dbuser");
	// }
	//
	// public static String getDbPassword() {
	// return props.getProperty("dbpassword");
	// }
	//
	// public static String getDbName() {
	// return props.getProperty("dbname");
	// }
	//
	// public static String getDbUrl() {
	// return props.getProperty("dburl");
	// }

	public static String getACCESS_CONTROL_ALLOW_ORIGIN() {
		return ACCESS_CONTROL_ALLOW_ORIGIN;
	}

	public static boolean getBoolean(String name) {
		String property = props.getProperty(name);
		return Boolean.parseBoolean(property);
	}

	public static int getDefaultRepliesCount() {
		return DEFAULT_REPLIES_COUNT;
	}

	public static int getDefaultNoticesCount() {
		return DEFAULT_NOTICES_COUNT;
	}

	public static String getImageFolderPath() {
		return imgfolderPath;
	}

	public static String getUserImageFolderPath() {
		return usrimgfolderPath;
	}

	public static int getDEFAULT_MAILS_TO_RETRIEVE() {
		return DEFAULT_MAILS_TO_RETRIEVE;
	}

	public static int getRecentActivityDays() {
		return 90;
	}

	public static String getAdImageFolderPath() {
		return adimgfolderPath;
	}
	
	public static String getBrandBGfolderPath() {
		return brandBGfolderPath;
	}

	public static String getPostImageFolderPath() {
		return postimgfolderPath;
	}

	public static int maxImageSize() {
		return MAX_IMG_SIZE;
	}

	public static int maxImageDimension() {
		return MAX_IMG_DIMENSION;
	}

	public static String getSMTPHost() {
		return SMTP_HOST;
	}

	public static String getSMTPPassword() {
		return SMTP_PASSWORD;
	}

	public static String getSMTPUser() {
		return SMTP_USER;
	}

	public static String getPwdReminderFromAddress() {
		return PWD_REMINDER_FROM_ADDRESS;
	}

	public static String getPwdReminderFromName() {
		return PWD_REMINDER_FROM_NAME;
	}

	public static int getBoardLocationUpdateInterval() {
		return BOARD_LOC_UPDATE_INTERVAL;
	}

	public static String getBoardBGimgfolderPath() {
		return boardBGimgfolderPath;
	}

	public static String getLogoimgfolderPath() {
		return logoimgfolderPath;
	}

	public static String getDomainUrl() {
		return domainUrl;
	}
}
