package shine.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import shine.app.game.GameManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.objects.Emerald;
import com.shine.objects.ShineLocation;
import com.sp.spring.SpApplicationContext;

public class AddEmeraldServlet extends BasePhoneServlet {

	private GameManager om;

	public AddEmeraldServlet() {
		om = SpApplicationContext.getBean(GameManager.class);
	}


	
	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int ret = createEmerald(req);

		return "" + ret;
	}

	private int createEmerald(HttpServletRequest req) throws ShineException {

		int creatorid = getAuthenticatedUserId(req, "userid");
		String question = req.getParameter("q");
		String answers = req.getParameter("a");
		int correctAnswer = getInt(req, "correct");
		ShineLocation uloc = getUserLocation(req);

		if (creatorid == 0 || correctAnswer < 1 || correctAnswer > 4
				|| StringUtils.isEmpty(question) || StringUtils.isEmpty(answers)
				|| uloc == null) {
			return GeneralError.PARAM_MISSING.getCode();
		}

		String failMsg = req.getParameter("fm");
		String successlMsg = req.getParameter("sm");

		Emerald e = new Emerald();
		e.setQuestion(question);
		e.setAnswerStr(answers);
		e.setLocation(uloc);
		e.setCreatorId(creatorid);
		e.setPoints(1);
		e.setCorrectAnswer(correctAnswer);
		e.setFailMsg(failMsg);
		e.setSuccessMsg(successlMsg);
		om.createUserEmerald(e, uloc);

		return e.getId();
	}

	@Override
	protected String getDefaultContentType() {
		return HTML_UTF8_CONTENT_TYPE;
	}

}
