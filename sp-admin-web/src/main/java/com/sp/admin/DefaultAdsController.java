package com.sp.admin;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.dao.exception.ShineException;

import com.sp.advert.DefaultAdParamsDao;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.Area1;
import com.sp.entity.loc.Country;
import com.sp.locations.BoardLocationsMapper;
import com.sp.locations.LocationsDao;

@Controller
@RequestMapping(value = "/admin/locations/")
public class DefaultAdsController {

	private static Logger log = LoggerFactory.getLogger(BoardLocationsMapper.class);

	@Autowired
	private LocationsDao locationsDao;

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

	@RequestMapping(value = "country/{countryid}/defaultads")
	public String showCountry(@PathVariable("countryid") int countryId, Model model) throws ShineException {

		Country country = locationsDao.getCountry(countryId);
		List<Area1> area1s = locationsDao.getArea1ByCountry(countryId);

		model.addAttribute("country", country);

		return "admin/boardlocations/countries";
	}

	@RequestMapping(value = "country/{countryid}/defaultad/{adid}/remove")
	public String removeAd(@PathVariable("countryid") int countryId, @PathVariable("adid") int adId, Model model) throws ShineException {

		defaultAdParamsDao.removeCountryDefaultAds(countryId, adId);
		return getLocations(model);
	}

}
