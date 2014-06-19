package shine.mobweb;

import javax.servlet.http.HttpServletRequest;

import shine.app.NoticeInList;
import shine.app.NoticeManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.Notice;
import com.shine.objects.OtherPlayer;
import com.sp.spring.SpApplicationContext;

public class NoticeServlet extends BaseMobWebServlet {

	public static boolean internaliseUsers = true;

	private NoticeManager noticeManager;

	public NoticeServlet() {
		noticeManager = SpApplicationContext.getBean(NoticeManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {
		boolean dweet = true;// getBoolean(req, "dweeti");

		if (!dweet) {
			return "/mw/holding.html";

		}
		int postId = getInt(req, "id");

		if (postId < 1) {
			throw new ShineException(GeneralError.PARAM_MISSING);
		}
		NoticeInList noticeInList;
		noticeInList = noticeManager.getNoticeWithPreviousAndNext(postId);
		Notice notice = noticeInList.getNotice();
		req.setAttribute("notice", notice);
		if (noticeInList.getPrevious() > 0) {
			req.setAttribute("prev", noticeInList.getPrevious());
		}
		if (noticeInList.getNext() > 0) {
			req.setAttribute("next", noticeInList.getNext());
		}
		String userImgUrl;

		if (notice.getCreatorId() == null || notice.getCreatorId() == 0) {
			notice.setAuthor("StreetPin");
			userImgUrl = "images/streetpin-logo-180x120.jpg";
				} else {

			OtherPlayer p = notice.getPlayer();

			if (p != null && p.isHasIcon()) {
				userImgUrl = "../images/user/" + p.getId() + ".png";
			} else {
				userImgUrl = "images/blank-profile.png";
			}

				}
		// tmpPopRepliers(dao, notice);
		req.setAttribute("userimg", userImgUrl);

		return "/mw/post2.jsp";
	}

}
