package shine.web;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.CorpBrand;
import com.shine.boards.Notice;
import com.shine.boards.NoticeBoard;
import com.shine.boards.Reply;
import com.shine.mail.Mail;
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
import com.sp.auth.SpUnauthorisedException;

public abstract class BasePhoneServlet extends BaseServlet {

	protected Logger log = Logger.getLogger(BasePhoneServlet.class);
	private static final String XML_UTF8_CONTENT_TYPE = "text/xml; charset=UTF-8";
	protected static final String JSON_UTF8_CONTENT_TYPE = "application/json; charset=UTF-8";
	protected static final String HTML_UTF8_CONTENT_TYPE = "text/html; charset=UTF-8";
	static final String XML_DECL = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.debug(this.getClass().getSimpleName() + " request received: " + req.getRequestURI() + "?"
				+ req.getQueryString());
		boolean test = getBoolean(req, "test");
		String resp = null;
		try {
			resp = doResponse(req);
			res.setContentType(getDefaultContentType());

		} catch (ShineException e) {
			log.error("doPost:shineException: " + e.getCode(), e);
			resp = "" + e.getCode();
			if (test) {
				resp += ":" + e.getMessage();
			}
			res.setContentType(HTML_UTF8_CONTENT_TYPE);

		} catch (SpUnauthorisedException e) {
			log.debug("doPost:unauthorised");
			res.setStatus(HttpURLConnection.HTTP_UNAUTHORIZED);
			return;
		} catch (Exception e) {
			resp = "" + GeneralError.SYSTEM_ERROR.getCode();
			if (test) {
				resp += ":" + e.getMessage();
			}
			res.setContentType(HTML_UTF8_CONTENT_TYPE);
		}

		log.debug(this.getClass().getSimpleName() + " sending response: " + resp);
		res.getWriter().print(resp);
		res.getWriter().close();

	}

	protected String getDefaultContentType() {
		return XML_UTF8_CONTENT_TYPE;
	}

	protected String getXml(ShineObject shineObject) {

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

}
