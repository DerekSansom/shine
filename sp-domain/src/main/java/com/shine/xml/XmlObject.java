package com.shine.xml;

import java.util.Date;

import com.shine.Util;
import com.shine.boards.BoardDetails;
import com.shine.objects.ShineObject;

public abstract class XmlObject {

	public abstract String toXml();

	private static final String EQ_S = "=\"";
	private static final String S = "\"";
	private static final String EMPTY_STR = "";
	private static final String AMP = "&";
	private static final String AMP_ESC = "&amp;";
	private static final String DOUBLE_QUOT = "\"";
	private static final String DOUBLE_QUOT_ESC = "&quot;";
	private static final String LT = "<";
	private static final String LT_ESC = "&lt;";
	private static final String GT = ">";
	private static final String GT_ESC = "&gt;";

	private ShineObject shineObject;

	public XmlObject(ShineObject shineObject) {
		this.shineObject = shineObject;
	}

	protected String formatIntAttribute(String attr, int value) {
		StringBuilder sb = new StringBuilder(" ")
				.append(attr)
				.append(EQ_S)
				.append(value)
				.append(S);
		return sb.toString();
	}

	protected String formatIntAttribute(String attr, Integer value) {
		if (value == null) {
			return EMPTY_STR;
		}
		return formatIntAttribute(attr, value.intValue());
	}

	protected String formatDoubleAttribute(String attr, Double value) {
		if (value == null) {
			return EMPTY_STR;
		}
		return formatDoubleAttribute(attr, value.doubleValue());

	}

	protected String formatDoubleAttribute(String attr, double value) {
		StringBuilder sb = new StringBuilder(" ")
				.append(attr)
				.append(EQ_S)
				.append(value)
				.append(S);
		return sb.toString();
	}

	public String formatAttribute(String attr, String value) {
		if (value == null || value.length() == 0) {
			return EMPTY_STR;
		}
		StringBuilder sb = new StringBuilder(" ")
				.append(attr)
				.append(EQ_S)
				.append(escape(value))
				.append(S);
		return sb.toString();
	}

	protected String formatBoardDetails(BoardDetails boardDetails) {
		if (boardDetails == null) {
			return EMPTY_STR;
		}
		StringBuilder sb = new StringBuilder(" ")
				.append(formatAttribute("location", boardDetails.getLocation()))
				.append(formatAttribute("boardname", boardDetails.getName()));

		return sb.toString();
	}

	public String formatPhoneAttribute(String phoneNo) {
		if (phoneNo == null || phoneNo.length() == 0) {
			return EMPTY_STR;
		}
		return formatAttribute("phoneno", formatPhoneNo(phoneNo));

	}

	private String formatPhoneNo(String value) {
		value = value.replaceAll(" ", "");
		value = value.replaceAll("-", "");
		return value;
	}

	/**
	 * Formats a boolean value into an xml format if true, if false returns
	 * empty string to save resources.
	 * 
	 * @param attr
	 * @param value
	 * @return
	 */
	protected String formatAttribute(String attr, boolean value) {
		if (value) {
			StringBuilder sb = new StringBuilder(" ")
					.append(attr)
					.append(EQ_S)
					.append(1)
					.append(S);
			return sb.toString();
		}
		return EMPTY_STR;
	}

	protected Object escape(String value) {
		if (value == null) {
			return EMPTY_STR;
		}

		value = value.replaceAll(AMP, AMP_ESC);
		value = value.replaceAll(DOUBLE_QUOT, DOUBLE_QUOT_ESC);
		value = value.replaceAll(LT, LT_ESC);
		value = value.replaceAll(GT, GT_ESC);
		return value;
	}

	protected String formatAttribute(String attr, Date date) {
		if (date == null) {
			return EMPTY_STR;
		}
		String value = Util.datadf.format(date);
		// String value = Util.datadfWithTz.format(date);
		return formatAttribute(attr, value);
	}

	protected String formatExtras() {
		StringBuffer extrasxml = new StringBuffer();
		extrasxml.append(formatAttribute("datex", shineObject.getDateExtra()));
		if (shineObject.getIntExtra() != 0) {
			extrasxml.append(formatIntAttribute("intx", shineObject.getIntExtra()));
		}
		if (shineObject.getStringExtra() != null && shineObject.getStringExtra().length() > 0) {
			extrasxml.append(formatAttribute("strx", shineObject.getStringExtra()));
		}
		return extrasxml.toString();
	}

}
