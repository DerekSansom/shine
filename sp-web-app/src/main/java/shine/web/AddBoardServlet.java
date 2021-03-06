package shine.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import shine.app.BoardCreator;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.BoardCategory;
import com.shine.objects.ShineLocation;
import com.sp.spring.SpApplicationContext;

public class AddBoardServlet extends BasePhoneServlet {

	@Autowired
	private BoardCreator boardCreator;

	public AddBoardServlet() {
		boardCreator = SpApplicationContext.getBean(BoardCreator.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int ret = createBoard(req);
		return "" + ret;
	}

	private int createBoard(HttpServletRequest req) throws ShineException {

		String name = req.getParameter("name");
		String locname = req.getParameter("locname");
		BoardCategory boardCat = getCategory(req);
		int creatorId = getAuthenticatedUserId(req, "creatorid");
		ShineLocation loc = getLocation(req);
		if (StringUtils.isEmpty(name) || StringUtils.isEmpty(locname) ||
				creatorId == 0 || loc == null) {
			return GeneralError.PARAM_MISSING.getCode();
		}

		if (locname.length() > SharedConstants.STD_FIELD_TEXT_LENGTH) {

			return SharedConstants.REG_FAILED_USERNAME_TOO_LONG.getCode();

		}
		if (name.length() > 30) {

			return SharedConstants.REG_FAILED_FULLNAME_TOO_LONG.getCode();

		}

		try {
			return boardCreator.createBoard(name, locname, creatorId, loc, boardCat);
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.error("Failed to create board", e);
			throw new ShineException(e);
		}
	}

	private BoardCategory getCategory(HttpServletRequest req) {
		String category = req.getParameter("category");
		if (StringUtils.isEmpty(category)) {
			return BoardCategory.DFLT;
		}
		BoardCategory cat = BoardCategory.valueOf(category);
		return cat;
	}

	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
