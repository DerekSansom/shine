package com.sp.admin.locations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.dao.exception.ShineException;

import com.sp.advert.DefaultAdParamsDao;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.Location;
import com.sp.locations.LocationsDao;

@Component
public class DefaultAdManager {

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private DefaultAdParamsDao defaultAdParamsDao;

	@Transactional
	public void removeDefaultAd(int adId, int locationId, String type)
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

	public Location getLocation(int locationId, String type) {

		type = type.toLowerCase();
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
	
}
