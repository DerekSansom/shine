package com.shine;

import com.shine.error.RegistrationError;

public class SharedConstants {
	public static final int DEFAULT_UPDATE_PERIOD = 120;
	// 2 weeks
	public static final long DEFAULT_EXPIRY = 60000 * 60 * 24 * 14;
	public static final double DISTANCE_TOLERANCE = 80;

	// response codes are always negative as on some functions id is returned,
	// always positive, so this avoids a clash
	public static final int SUCCESS = -31;
	public static final ShineError ERROR_MAX_IMG_SIZE = GeneralError.ERROR_MAX_IMG_SIZE;

	public static final int SOMEONE_ELSES_SAPPHIRE = -37;
	public static final int WRONG_ANSWER = -38;
	public static final ShineError PLAYER_NOT_FOUND = GeneralError.PLAYER_NOT_FOUND;
	public static final int PARENT_NOT_COLLECTED = -40;
	public static final int ALREADY_ACTIONED = -41;
	public static final int LATER = -42;
	public static final int TOO_FAR = -43;
	public static final int TOO_NEAR = -44;
	public static final int TRAIL_FINISHED = -50;

	//Challenges
	public static final int PAPER = 34;
	public static final int SCISSORS = 35;
	public static final int STONE = 36;

	public static final int DRAW = -22;
	public static final int AWAITING_PLAY = -23;
	public static final int CHALLENGER_WINS_ROUND = -24;
	public static final int OPPONENT_WINS_ROUND = -25;
	public static final int CHALLENGER_WINS = -26;
	public static final int OPPONENT_WINS = -27;
	public static final int CHALLENGER_WINS_ROUND_TIMEOUT = -28;
	public static final int OPPONENT_WINS_ROUND_TIMEOUT = -29;
	public static final int TIMEOUT = -30;
	public static final int DECLINED = -32;
	public static final int NOT_ENOUGH_POINTS = -75;

	public static final int YOU_WIN_ROUND = 1;
	public static final int YOU_WIN_GAME = 2;
	public static final int OPP_WINS_ROUND = 3;
	public static final int OPP_WINS_GAME = 4;
	public static final int YOU_WIN_TIMEOUT = 5;
	public static final int OPP_WINS_TIMEOUT = 6;

	//Game actions
	public static final int CHALLENGE = 3;
	public static final int ACCEPT = 4;
	public static final int REFUSE = 5;
	public static final int PING = 6;
	public static final int PLAY = 7;
	public static final int BUSY = -81;

	public static final int IN_BOX = 1;
	public static final int OUT_BOX = 2;
	public static final int BOTH_BOXES = 3;
	public static final int NEW_MAILS = 4;
	public static final int JSON_MSGS = 5;
	public static final int JSON_NEW = 6;

	// Registration errors
//	public final static int REG_SUCCESS = -9;
	public final static ShineError REG_FAILED_USERNAME_REQUIRED = RegistrationError.USERNAME_REQUIRED;
	public final static ShineError REG_FAILED_USERNAME_TAKEN = RegistrationError.USERNAME_TAKEN;
	public final static ShineError REG_FAILED_USERNAME_4_CHARS_REQD = RegistrationError.USERNAME_4_CHARS_REQD;
	public final static ShineError REG_FAILED_EMAIL_REQUIRED = RegistrationError.EMAIL_REQUIRED;
	public final static ShineError REG_FAILED_EMAIL_INVALID = RegistrationError.EMAIL_INVALID;
	public final static ShineError REG_FAILED_EMAIL_TAKEN = RegistrationError.EMAIL_TAKEN;
	public static final ShineError REG_FAILED_FULLNAME_REQUIRED = RegistrationError.FULLNAME_REQUIRED;
	public final static ShineError REG_FAILED_PHONE_INVALID = RegistrationError.PHONE_INVALID;
	public static final ShineError REG_FAILED_USERNAME_TOO_LONG = RegistrationError.USERNAME_TOO_LONG;
	public static final ShineError REG_FAILED_FULLNAME_TOO_LONG = RegistrationError.FULLNAME_TOO_LONG;
	public static final ShineError REG_FAILED_PASSWORD_REQUIRED = RegistrationError.PASSWORD_REQUIRED;
	public static final ShineError REG_FAILED_PASSWORD_LENGTH = RegistrationError.PASSWORD_LENGTH;

	public static final int LOG_IN_FAILED = -20;

	public static final int STD_FIELD_TEXT_LENGTH = 50;
	public static final int PWD_MAX_LENGTH = 20;
	public static final int PWD_MIN_LENGTH = 4;

	// XML element names
	public final static String TITLE = "title";
	public final static String NOTICE = "notice";
	public final static String REPLY = "reply";
	public final static String NOTICE_ID = "noticeId";
	public final static String LAT = "lat";
	public final static String LNG = "lng";

	public final static String REPLIED = "r";
	public final static String EXPIRED = "e";
	public final static String POSTED = "p";

	// NoticeBoardSearchOptions
	public static final int NOTICE_ALL_FIELDS = 1;
	public static final int NOTICE_FIELDS_ONLY = 2;
	public static final int NOTICE_AND_REPLIES = 3;
	public static final int NOTICE_CREATOR_ONLY = 4;
	public static final int NOTICE_AND_CREATOR = 5;
	public static final int ADVERTS_ONLY = 10;
	// all advert and notice codes = ADVERTS_ONLY plus the corresponding notice code
	public static final int NOTICE_ALL_FIELDS_AND_ADVERTS = 11;
	public static final int NOTICE_FIELDS_ONLY_AND_ADVERTS = 12;
	public static final int NOTICE_AND_CREATOR_AND_ADVERTS = 15;

	public static final int MODE_BOARDS = 0;
	public static final int MODE_GAME = 1;
	public static final int MODE_BOTH = 2;

//friend actions	
	public static final int FRIEND_REQUEST = 1;
	public static final int FRIEND_ACCEPT = 2;
	public static final int FRIEND_BLOCK = 3;
	public static final int FRIEND_REQUEST_RECEIVED = -1;
	public static final int FRIEND_UNBLOCK = 57;
	public static final int RELATIONSHIP_ALREADY_EXISTS = -70;

//c2dm messages	
	public static final String C2DM_CHALLENGE = "c";
	public static final String C2DM_NEW_MSG = "m";
	public static final String C2DM_POST_REPLIED = "r";
	public static final String C2DM_NEW_POST_ON_BOARD = "n";

//mail systemTypes	
	public static final int POST_REPLIED_TO = 56;
	public static final int BOARD_POSTED_TO = 57;
	public static final int MAIL_RECEIVED = 58;

}
