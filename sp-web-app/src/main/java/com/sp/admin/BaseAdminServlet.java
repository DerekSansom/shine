package com.sp.admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import shine.dao.exception.ShineException;
import shine.mobweb.BaseMobWebServlet;
import shine.web.BaseServlet;

public abstract class BaseAdminServlet extends BaseServlet {
	private static final String ERROR_JSP = "/../admin/error.jsp";
	protected Logger log = Logger.getLogger(BaseMobWebServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		log.debug("mw request received: " + req.getRequestURI() + "?" + req.getQueryString());
		req.setCharacterEncoding("UTF-8");
		try {
			String forwardJsp = doResponse(req);
			forward(forwardJsp, req, res);
		} catch (ShineException e) {
			log.error("ShineException caught, forwarding to error page: code " + e.getCode() + " - " + e.getMessage(),
					e);
			forward(ERROR_JSP, req, res);

		}

	}

	private void forward(String forwardJsp, HttpServletRequest req, HttpServletResponse res) throws ServletException,
			IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(forwardJsp);
		dispatcher.forward(req, res);
	}

	/**
	 * Returns the xml string to return or throws a shineexception with error
	 * code
	 * 
	 * @param req
	 * @return
	 */
	protected abstract String doResponse(HttpServletRequest req) throws ShineException;

	protected void log(String msg, Exception e) {
		log.warn(msg, e);
	}

}
