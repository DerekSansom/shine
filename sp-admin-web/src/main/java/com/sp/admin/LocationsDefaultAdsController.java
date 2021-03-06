package com.sp.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.dao.exception.ShineException;

import com.sp.admin.locations.DefaultAdManager;
import com.sp.advert.DefaultAdParamsDao;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.Country;
import com.sp.locations.BoardLocationsMapper;
import com.sp.locations.LocationsDao;
import com.sp.security.Role;

@Controller
@RequestMapping(value = "/admin/locations/")
@Secured(Role.ADMIN)
public class LocationsDefaultAdsController {

	private static Logger log = LoggerFactory.getLogger(BoardLocationsMapper.class);

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private DefaultAdManager defaultAdManager;

	@Autowired
	private DefaultAdParamsDao defaultAdParamsDao;

	@RequestMapping(value = "countries")
	public String getLocations(Model model) throws ShineException {

		List<DefaultAdParams> globalDefaults = defaultAdParamsDao.getNullLocationDefaultAdParams(0);
		model.addAttribute("global", globalDefaults);

		List<Country> countries = locationsDao.getLocations(Country.class);
		model.addAttribute("countries", countries);

		return "admin/boardlocations/countries";
	}


	@RequestMapping(value = "{type}/{locationid}/defaultad/{adid}/remove")
	public String removeDefaultAd(@PathVariable("locationid") int locationId, @PathVariable("adid") int adId,
			@PathVariable("type") String type, Model model) throws ShineException {

		defaultAdManager.removeDefaultAd(adId, locationId, type);
		return getLocations(model);
	}


}
