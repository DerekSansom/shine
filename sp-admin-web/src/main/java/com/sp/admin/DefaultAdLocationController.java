package com.sp.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import shine.dao.exception.ShineException;

import com.sp.advert.AdvertDao;
import com.sp.advert.DefaultAdParamsDao;
import com.sp.entity.ad.AdvertEntity;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.Area1;
import com.sp.entity.loc.Area2;
import com.sp.entity.loc.Area3;
import com.sp.entity.loc.Country;
import com.sp.entity.loc.Location;
import com.sp.locations.LocationsDao;
import com.sp.security.Role;

@Controller
@RequestMapping(value = "/admin/adverts")
public class DefaultAdLocationController {

	private static final int DEFAULT_TO_SHOW = 500;

	@Autowired
	private AdvertDao advertDao;

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private DefaultAdParamsDao defaultAdParamsDao;

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
		return adCountriesPage(ad, model);

	}

	private String adCountriesPage(AdvertEntity advert, Model model) {

		model.addAttribute("advert", advert);

		List<DefaultAdParams> globalDefaults = defaultAdParamsDao.getNullLocationDefaultAdParams(0);

		model.addAttribute("global", globalDefaults);

		List<Country> countries = locationsDao.getLocations(Country.class);
		model.addAttribute("countries", countries);

		return "admin/ads/countries";
	}

	@RequestMapping(value = "{adId}/{type}/{locationId}/set")
	@Secured(Role.ADMIN)
	@Transactional
	public String setDefault(@PathVariable("adId") int adId, @PathVariable("locationId") int locationId, @PathVariable("type") String type,
			Model model) throws ShineException {

		AdvertEntity ad = advertDao.getAd(adId);
		setAdAsDefault(ad, locationId, type, model);
		return adCountriesPage(ad, model);

	}

	@RequestMapping(value = "{adId}/{type}/{locationId}/remove")
	@Transactional
	public String removeAd(@PathVariable("adId") int adId, @PathVariable("locationId") int locationId, @PathVariable("type") String type, Model model)
			throws ShineException {

		DefaultAdParams toDelete = null;

		if (type.equals("global")) {
			toDelete = findInList(defaultAdParamsDao.getNullLocationDefaultAdParams(0), adId);
		} else {
			Location location = getLocation(locationId, type);
			toDelete = findInList(location.getDefaultAdParams(), adId);
		}

		if (toDelete != null) {
			defaultAdParamsDao.delete(toDelete);
		}

		AdvertEntity advert = advertDao.getAd(adId);
		return adCountriesPage(advert, model);
	}

	private DefaultAdParams findInList(List<DefaultAdParams> defaultAdParams, int adId) {

		for (DefaultAdParams defaultAdParam : defaultAdParams) {
			if (defaultAdParam.getAdId() == adId) {
				defaultAdParams.remove(defaultAdParam);
				return defaultAdParam;
			}
		}

		return null;
	}

	private void setAdAsDefault(AdvertEntity advert, int locationId, String type, Model model) {

		if (isDefault(advert, locationId, type)) {
			model.addAttribute("error", "Advert already set as default for this location");
			// already set so do nothing
			return;
		}

		DefaultAdParams newParams = new DefaultAdParams();
		newParams.setAdvert(advert);
		defaultAdParamsDao.save(newParams);

		Location location = getLocation(locationId, type);
		if (location != null) {
			location.getDefaultAdParams().add(newParams);
		}
	}

	private Location getLocation(int locationId, String type) {
		switch (type) {

		case "country":
			return locationsDao.getCountry(locationId);
		case "area1":
			return locationsDao.getArea1(locationId);
		case "area2":
			return locationsDao.getArea2(locationId);
		case "area3":
			return locationsDao.getArea3(locationId);
		case "global":
		default:
			break;
		}
		return null;
	}

	private boolean isDefault(AdvertEntity advert, int locationId, String type) {
		//TODO: better way of doing this
		List<DefaultAdParams> existing = null;
		
		switch (type) {
		case "country":
			Country country = locationsDao.getCountry(locationId);
			existing = country.getDefaultAdParams();
			break;
		case "area1":
			Area1 area1 = locationsDao.getArea1(locationId);
			existing =  area1.getDefaultAdParams();
			break;
		case "area2":
			Area2 area2 = locationsDao.getArea2(locationId);
			existing =  area2.getDefaultAdParams();
			break;
		case "area3":
			Area3 area3 = locationsDao.getArea3(locationId);
			existing = area3.getDefaultAdParams();
			break;
		case "global":
			existing = defaultAdParamsDao.getNullLocationDefaultAdParams(0);
			break;
		}	
		
		if (existing != null) {
		for (DefaultAdParams defaultAdParams : existing) {
			if(defaultAdParams.getAdId() == advert.getId()){
				return true;
			}
		}
		}
		return false;
	}

}
