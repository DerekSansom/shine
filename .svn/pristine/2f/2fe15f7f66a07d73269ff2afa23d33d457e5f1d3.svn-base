package com.sp.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.dao.exception.ShineException;

import com.sp.advert.AdvertDao;
import com.sp.entity.ad.AdvertEntity;
import com.sp.entity.loc.Country;
import com.sp.locations.LocationsDao;
import com.sp.security.Role;

@Controller
@RequestMapping(value = "/admin/adverts")
public class AdController {

	private static final int DEFAULT_TO_SHOW = 500;

	@Autowired
	private AdvertDao advertDao;

	@Autowired
	private LocationsDao locationsDao;

	@RequestMapping(value = "")
	@Secured(Role.ADMIN)
	public String showads(Model model) throws ShineException {

		List<AdvertEntity> ads = advertDao.getAllAdverts(0, DEFAULT_TO_SHOW);

		model.addAttribute("adverts", ads);
		return "admin/ads/ads";
	}

	@RequestMapping(value = "{adId}/default")
	@Secured(Role.ADMIN)
	public String selectDefault(@PathVariable("adId") int adId, Model model) throws ShineException {

		AdvertEntity ad = advertDao.getAd(adId);
		model.addAttribute("advert", ad);

		List<Country> countries = locationsDao.getLocations(Country.class);
		model.addAttribute("countries", countries);

		return "admin/ads/countries";
	}

	@RequestMapping(value = "{adId}/{type}/{locationId}/set")
	@Secured(Role.ADMIN)
	public String setDefault(@PathVariable("adId") int adId, @PathVariable("locationId") int locationId, @PathVariable("type") String type,
			Model model) throws ShineException {

		AdvertEntity ad = advertDao.getAd(adId);
		model.addAttribute("advert", ad);

		List<Country> countries = locationsDao.getLocations(Country.class);
		model.addAttribute("countries", countries);

		return "admin/ads/countries";
	}

}
