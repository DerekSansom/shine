package shine.xml;

import java.util.List;

import com.shine.boards.CorpBrand;
import com.shine.boards.Notice;
import com.shine.boards.NoticeBoard;
import com.shine.boards.Reply;
import com.shine.mail.Mail;
import com.shine.objects.Emerald;
import com.shine.objects.OtherPlayer;
import com.shine.objects.Player;
import com.shine.objects.ShineObject;
import com.shine.xml.CorpBrandXml;
import com.shine.xml.MailXml;
import com.shine.xml.NoticeBoardXml;
import com.shine.xml.NoticeXml;
import com.shine.xml.OtherPlayerXml;
import com.shine.xml.PlayerXml;
import com.shine.xml.ReplyXml;
import com.shine.xml.XmlObject;

public class XmlUtil {

	private static final String EQ_S = "=\"";
	private static final String S = "\"";
	private static final String AMP = "&";
	private static final String AMP_ESC = "&amp;";
	private static final String DOUBLE_QUOT = "\"";
	private static final String DOUBLE_QUOT_ESC = "&quot;";
	private static final String LT = "<";
	private static final String LT_ESC = "&lt;";
	private static final String GT = ">";
	private static final String GT_ESC = "&gt;";
	private static final String EMPTY_STR = "";

	public static String createObjectListXml(List<ShineObject> objects) {
		StringBuffer xml = new StringBuffer("<objects>");

		for (ShineObject shineObject : objects) {
			if (shineObject instanceof NoticeBoard) {
				NoticeBoardXml nbx = new NoticeBoardXml((NoticeBoard) shineObject);

				xml.append(nbx.toMapXml());
			} else {
				xml.append(getXml(shineObject));
			}
		}
		xml.append("</objects>");

		return xml.toString();
	}

	private static String stoneToXml(ShineObject so) {
		/*
		 * StringBuffer xml=new StringBuffer(); xml.append("<object lat=\"");
		 * xml.append(so.getLat()); xml.append("\" lng=\"");
		 * xml.append(so.getLng()); xml.append("\" type=\"");
		 * xml.append(so.getClass().getSimpleName()); xml.append("\" id=\"");
		 * xml.append(so.getId()); xml.append("\"/>");
		 */
		return getXml(so);
	}

	@Deprecated
	protected String formatIntAttribute(String attr, int value) {
		StringBuilder sb = new StringBuilder(" ")
				.append(attr)
				.append(EQ_S)
				.append(value)
				.append(S);
		return sb.toString();
	}

	public static String createNextEmeraldXml(Emerald child) {

		StringBuffer xml = new StringBuffer();
		xml.append(createObjectXml(child));

		return xml.toString();

	}

	public static String createObjectXml(ShineObject object) {
		StringBuffer xml = new StringBuffer("<objects>");

		xml.append(stoneToXml(object));
		xml.append("</objects>");

		return xml.toString();
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
			return "";
		}
		StringBuilder sb = new StringBuilder(" ")
				.append(attr)
				.append(EQ_S)
				.append(escape(value))
				.append(S);
		return sb.toString();
	}

	public static String getXml(ShineObject shineObject) {

		XmlObject xml = null;
		if (shineObject instanceof Notice) {
			xml = new NoticeXml((Notice) shineObject);

		} else if (shineObject instanceof Reply) {
			xml = new ReplyXml((Reply) shineObject);

		} else if (shineObject instanceof NoticeBoard) {
			xml = new NoticeBoardXml((NoticeBoard) shineObject);

		} else if (shineObject instanceof CorpBrand) {
			xml = new CorpBrandXml((CorpBrand) shineObject);

		} else if (shineObject instanceof Mail) {
			xml = new MailXml((Mail) shineObject);

		} else if (shineObject instanceof Player) {
			xml = new PlayerXml((Player) shineObject);

		} else if (shineObject instanceof OtherPlayer) {
			xml = new OtherPlayerXml((OtherPlayer) shineObject);

		}

		if (xml == null) {
			return "";
		}
		return xml.toXml();
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

}
