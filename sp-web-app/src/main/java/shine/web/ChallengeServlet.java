package shine.web;

import static com.shine.GeneralError.PARAM_MISSING;
import static com.shine.SharedConstants.ACCEPT;
import static com.shine.SharedConstants.CHALLENGE;
import static com.shine.SharedConstants.PING;
import static com.shine.SharedConstants.PLAY;
import static com.shine.SharedConstants.REFUSE;

import javax.servlet.http.HttpServletRequest;

import shine.app.Challenge;
import shine.app.ChallengeHandler;
import shine.app.ChallengeHandler.ResultReturn;
import shine.dao.exception.ShineException;

import com.sp.spring.SpApplicationContext;

public class ChallengeServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;
	private ChallengeHandler handler;

	public ChallengeServlet() {
		handler = SpApplicationContext.getBean(ChallengeHandler.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getAuthenticatedUserId(req, "userid");
		int action = getInt(req, "action");
		int oppid = getInt(req, "opid");
		int challengeid = getInt(req, "cid");
		if (userid == 0 || action == 0) {
			throw new ShineException(PARAM_MISSING);
		}
		if (action == PLAY) {
			int round = getInt(req, "r");
			int play = getInt(req, "p");
			if (challengeid == 0 || round == 0 || play == 0) {
				throw new ShineException(PARAM_MISSING);
			}

			return makePlay(challengeid, userid, oppid, round, play);
		}
		return "" + doAction(challengeid, userid, action, oppid);

	}

	private String makePlay(int challengeid, int userid, int oppid, int round, int play) throws ShineException {

		return toXml(handler.play(challengeid, userid, round, play));
	}

	private String toXml(ResultReturn rr) {
		StringBuilder sb = new StringBuilder("<rr");
		formatIntAttribute(sb, "oppselect", rr.getOpponentsSelection());
		formatIntAttribute(sb, "result", rr.getResult());
		formatIntAttribute(sb, "yourscore", rr.getYourscore());
		formatIntAttribute(sb, "oppscore", rr.getOppscore());
		sb.append("/>");
		return sb.toString();
	}

	private static final String EQ_S = "=\"";
	private static final String S = "\"";

	protected String formatIntAttribute(StringBuilder sb, String attr, int value) {
		sb.append(" ")
				.append(attr)
				.append(EQ_S)
				.append(value)
				.append(S);
		return sb.toString();
	}

	private String doAction(int challengeid, int userid, int action, int oppid) throws ShineException {
		Challenge c = null;
		switch (action) {
		case CHALLENGE:
			c = handler.makeChallenge(userid, oppid);
			return c.toXml();
		case ACCEPT:
			c = handler.acceptChallenge(challengeid, oppid, userid);
			return c.toXml();
		case REFUSE:
			throw new ShineException(handler.refuseChallenge(challengeid, userid, oppid));
		case PING:
			c = handler.pingChallenge(userid, oppid);
			return c.toXml();
		default:
			break;
		}

		throw new ShineException(PARAM_MISSING);
	}
}
