package com.sp.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import shine.app.LocationManager;
import shine.dao.exception.ShineException;

import com.sp.entity.loc.Location;
import com.sp.spring.SpApplicationContext;

public class LocAdminServlet extends BaseAdminServlet {

	private static final String ERROR_PAGE = "admin/jsp/error.jsp";

	private LocationManager locationManager;

	public LocAdminServlet() {
		locationManager = SpApplicationContext.getBean(LocationManager.class);
	}

	@Override
	protected String doResponse(HttpServletRequest req) throws ShineException {

		try {
			List<Location> unlocs = locationManager.getUnlocatedLocations();

			req.setAttribute("unlocs", unlocs);
			return "/admin/jsp/locationAdmin.jsp";

		} catch (Exception e) {
			log.error("failed to retrieve locations", e);
			return ERROR_PAGE;

		}
	}

}
