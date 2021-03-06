package shine.web;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import shine.app.NoticeManager;
import shine.app.ReplyManager;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.Notice;
import com.shine.boards.Reply;
import com.shine.objects.OtherPlayer;
import com.shine.xml.NoticeXml;
import com.shine.xml.OtherPlayerXml;
import com.shine.xml.ReplyXml;
import com.sp.spring.SpApplicationContext;

public class NoticeServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;
	private NoticeManager noticeManager;
	private ReplyManager replyManager;

	public NoticeServlet() {
		noticeManager = SpApplicationContext.getBean(NoticeManager.class);
		replyManager = SpApplicationContext.getBean(ReplyManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int boardid = getInt(req, "boardid");
		int start = getInt(req, "start");
		if (boardid > 0) {
			int secondsSince = getInt(req, "since");
			if (start > 0 && secondsSince > 0) {
				throw new ShineException(GeneralError.PARAM_MISSING, "only one of start or since allowed");
			}
			int catid = getInt(req, "catid");
			StringBuilder sb = new StringBuilder(XML_DECL);
			sb.append(getNotices(boardid, start, secondsSince, catid));
			return sb.toString();
		}

		int noticeid = getInt(req, "id");
		if (noticeid > 0) {
			StringBuilder sb = new StringBuilder(XML_DECL);
			sb.append(getReplies(noticeid, start));
			return sb.toString();
		}
		int fullnoticeid = getInt(req, "noticeid");
		if (fullnoticeid > 0) {
			StringBuilder sb = new StringBuilder(XML_DECL);
			sb.append(getNotice(fullnoticeid));
			return sb.toString();
		}

		throw new ShineException(GeneralError.PARAM_MISSING, "one of id, noticeid required");
	}

	private String getNotices(int boardid, int start, int secondsSince, int catid) {
		Set<OtherPlayer> players = new HashSet<OtherPlayer>();

		List<Notice> list = null;
		if (secondsSince > 0) {
			list = noticeManager.getNoticesSince(boardid, secondsSince);

		} else {
			list = noticeManager.getNotices(boardid, start, ShineProperties.getDefaultRepliesCount(), catid, false);
		}
		StringBuilder ret = new StringBuilder("<notices>");
		if (list != null) {

			for (Notice notice : list) {
				if (notice.getPlayer() != null) {
					players.add(notice.getPlayer());
				} else {
					notice.setAuthor("StreetPin");
				}
				ret.append(new NoticeXml(notice).toXml());
			}

			for (OtherPlayer player : players) {
				ret.append(new OtherPlayerXml(player).toXml());
			}

		}
		ret.append("</notices>");
		return ret.toString();
	}

	private String getReplies(int noticeid, int start) throws ShineException {

		try {
			List<Reply> replies = replyManager.getReplies(noticeid, start, ShineProperties.getDefaultRepliesCount(),
					false);

			StringBuilder ret = new StringBuilder("<replies>");
			if (replies != null) {
				for (Reply reply : replies) {
					ret.append(new ReplyXml(reply).toXml());
				}
			}
			ret.append("</replies>");
			return ret.toString();
		} catch (Exception e) {
			log.debug("", e);
			throw new ShineException(e);
		}
	}

	private String getNotice(int noticeid) throws ShineException {

		try {
			Notice notice = noticeManager.getNoticeWithReplies(noticeid);

			if (notice == null) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			NoticeXml xml = new NoticeXml(notice);
			return xml.toXmlWithReplies();
		} catch (Exception e) {
			log.debug("", e);
			throw new ShineException(e);
		}
	}
}
