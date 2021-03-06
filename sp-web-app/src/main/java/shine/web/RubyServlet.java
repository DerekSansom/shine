package shine.web;

import javax.servlet.http.HttpServletRequest;

import shine.app.game.GameManager;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.objects.Ruby;
import com.sp.spring.SpApplicationContext;

public class RubyServlet extends BasePhoneServlet {

	private static final long serialVersionUID = 1L;

	private GameManager handler;

	public RubyServlet() {
		handler = SpApplicationContext.getBean(GameManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		int userid = getInt(req, "userid");

		int rid = getInt(req, "id");
		if (userid == 0 || rid == 0) {
			throw new ShineException(GeneralError.PARAM_MISSING, "userid and id required");
		}

		try {
			Ruby r = handler.getRuby(rid, userid);
			if (r == null) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			StringBuilder sb = new StringBuilder(XML_DECL);
//			sb.append("<objects>");
			sb.append(r.toXml());
//			sb.append("</objects>");

			return sb.toString();
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.error("RubyServlet", e);
			throw new ShineException(e);
		}
	}

}
