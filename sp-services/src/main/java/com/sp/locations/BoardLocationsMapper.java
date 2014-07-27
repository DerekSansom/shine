package com.sp.locations;
<<<<<<< HEAD
=======

>>>>>>> 482eb53fad7036601388e8d65c7f5a704415ab05
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.dao.exception.ShineException;

import com.shine.objects.ShineLocation;
import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.loc.Area1;
import com.sp.entity.loc.Area2;
import com.sp.entity.loc.Area3;
import com.sp.entity.loc.BoardLoc;
import com.sp.entity.loc.Country;
import com.sp.entity.loc.Location;
import com.sp.locations.GoogleAddressParser.LocDetails;

@Component
public class BoardLocationsMapper {

	private static final String googleRevCodeUrl = "http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&latlng=";
	private static final String googleGeoCodeUrl = "http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&address=";
	private static Logger log = LoggerFactory.getLogger(BoardLocationsMapper.class);

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private LocationsDao locationsDao;

	@Autowired
	private UrlUtils urlUtils;

	@Transactional
	public void updateBoardLocation(Integer id) throws ShineException {
		updateBoardLocation(id, log);
	}

	@Transactional
	public void updateBoardLocation(Integer id, Logger localLogger) throws ShineException {

		NoticeBoardEntity nb = boardDao.getNoticeBoard(id);
		if (nb != null) {
			String strUrl = null;
			try {
				ShineLocation loc = new ShineLocation(nb.getLat(), nb.getLng());
				strUrl = googleRevCodeUrl + loc.getLat() + "," + loc.getLng();
				localLogger.info("Calling google with: " + strUrl);
				InputStream is = urlUtils.getInputStream(strUrl);
				// this is where it happens, extracting the loc details
				if (is != null) {
					LocDetails locd = getLocationDetails(is);
					if (locd != null) {
						setBoardToLoc(id, locd);
						if (StringUtils.isEmpty(locd.getAdminarea1())) {
							localLogger.warn("boardid " + id + " has nothing below country" + locd.getCountry() + ":"
									+ locd.getCountrycode());
						} else {
							localLogger.info("boardid " + id + locd.toString());
						}
					} else {
						localLogger.warn("boardid " + id + " has no location");
					}
				} else {
					localLogger.warn("could not reach google to geocode boardid " + id);
				}
			} catch (NoLocationResultsException e) {
				localLogger.error("boardid " + id + " : " + e.getMessage() + " : " + strUrl);
			} catch (Exception e) {
				localLogger.error("boardid " + id + ":" + e.getMessage());
			}

		} else {
			localLogger.warn("boardid " + id + " not found in database");
		}

	}

	@Transactional
	public void getLocationsLocations() {

		List<Location> unlocatedLocations = locationsDao.getUnlocatedLocations();
		for (Location location : unlocatedLocations) {

			StringBuilder sb = new StringBuilder(location.getName());
			Location parentLoc = location.getParent();
			while (parentLoc != null) {
				sb.append(",").append(parentLoc.getName());
				parentLoc = parentLoc.getParent();
			}

			String address = sb.toString();
			String strUrl = null;
			try {
				strUrl = googleGeoCodeUrl + URLEncoder.encode(address, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				log.error("error in finding address", e);
				continue;
			}

			InputStream is = urlUtils.getInputStream(strUrl);
			if (is == null) {
				log.warn(strUrl + " returned NULL inputstream");
				continue;
			}
			LocDetails locd;
			try {
				locd = getLocationDetails(is);
			} catch (NoLocationResultsException e) {
				log.warn(strUrl + " returned noResultsException");
				continue;
			}

			if (locd == null) {
				log.warn(strUrl + " returned NULL LocDetails");
				continue;
			}
			if (!isLocEqual(location, locd)) {
				log.warn(strUrl + " did not return a location equal to " + location.getClass().getSimpleName()
						+ " id:" + location.getId());
				continue;
			}
			if (isEmpty(locd.getLat()) && isEmpty(locd.getLng())) {
				log.warn(strUrl + " return empty location");
				continue;
			}
			location.setLat(locd.getLat());
			location.setLng(locd.getLng());
			try {
				locationsDao.save(location);
			} catch (Exception e) {
				log.warn(" failed to save location" + location.toString(), e);
			}
		}
	}

	private boolean isEmpty(double lat) {
		return lat == 0D;
	}

	private boolean isLocEqual(Location location, LocDetails locd) {
		String area3 = null;
		String area2 = null;
		String area1 = null;
		String country = null;
		Location parent = location;

		if (parent instanceof Area3) {
			area3 = parent.getName();
			parent = ((Area3) parent).getParent();
		}
		if (parent instanceof Area2) {
			area2 = parent.getName();
			parent = ((Area2) parent).getParent();
		}
		if (parent instanceof Area1) {
			area1 = parent.getName();
			parent = ((Area1) parent).getParent();
		}
		if (parent instanceof Country) {
			country = parent.getName();
		}

		if (equalsOrNull(area3, locd.getAdminarea3())
				&& equalsOrNull(area2, locd.getAdminarea2())
				&& equalsOrNull(area1, locd.getAdminarea1())
				&& equalsOrNull(country, locd.getCountry())) {
			return true;
		}

		return false;
	}

	private boolean equalsOrNull(String str1, String str2) {
		if (StringUtils.isEmpty(str1) && StringUtils.isEmpty(str2)) {
			return true;
		}
		if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
			return false;
		}

		return str1.equalsIgnoreCase(str2);
	}


	private void setBoardToLoc(Integer id, LocDetails locd) throws ShineException {

		BoardLoc locBoard = getLocBoard(locd, id);
		if (locBoard != null) {
			try {
				locationsDao.create(locBoard);
			} catch (Exception e) {
				log.error("Error setting loc to boardid: " + id + " - " + locBoard.toString(), e);
			}
		}

	}

	private BoardLoc getLocBoard(LocDetails locd, int boardid) throws ShineException {

		if (locd.getCountry() != null && locd.getCountrycode() != null) {

			BoardLoc locBoard = locationsDao.getBoardLocation(boardid);
			if (locBoard == null) {
				locBoard = new BoardLoc();
				locBoard.setBoardId(boardid);
			}
			Country c = getCountry(locd.getCountry(), locd.getCountrycode());
			locBoard.setCountryId(c.getId());

			//Some google calls omit admin area 1 e.g. Wrights Lane
			//http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&latlng=51.4994,-0.192

			if (StringUtils.isNotEmpty(locd.getAdminarea1())) {
				Area1 a1 = getArea1(locd.getAdminarea1(), c);
				locBoard.setArea1Id(a1.getId());

				if (StringUtils.isNotEmpty(locd.getAdminarea2())) {

					Area2 a2 = getArea2(locd.getAdminarea2(), a1);
					locBoard.setArea2Id(a2.getId());

					if (StringUtils.isNotEmpty(locd.getAdminarea3())) {
						Area3 a3 = getArea3(locd.getAdminarea3(), a2);
						locBoard.setArea3Id(a3.getId());
					}

				}

			} else if (StringUtils.isNotEmpty(locd.getAdminarea2())) {
				locBoard.setInterventionReqd(true);
				Area2 a2 = getArea2ByCountry(locd.getAdminarea2(), locd.getCountrycode());
				if (a2 != null) {
					locBoard.setArea2Id(a2.getId());
					locBoard.setArea1Id(a2.getParent().getId());

					if (StringUtils.isNotEmpty(locd.getAdminarea3())) {
						Area3 a3 = getArea3(locd.getAdminarea3(), a2);
						locBoard.setArea3Id(a3.getId());
					}
				}
			}

			return locBoard;
		}

		return null;
	}

	private Area2 getArea2ByCountry(String adminarea2, String countrycode) {

		List<Area2> candidates = locationsDao.searchLocs(Area2.class, adminarea2);
		for (Area2 area2 : candidates) {
			Area1 a1 = area2.getParent();
			if (a1 != null) {
				Country c = a1.getParent();
				if (c != null) {
					if (countrycode.equals(c.getCode())) {
						return area2;
					}
				}
			}
		}

		return null;
	}

//	private boolean isLocationSet(Location loc) {
//		return true;
//	}

	private Area1 getArea1(String name, Country country) throws ShineException {
		Area1 a1 = locationsDao.getArea1(name, country.getId());
		if (a1 == null) {
			a1 = new Area1();
			a1.setName(name);
			a1.setParent(country);
			locationsDao.save(a1);
		}
		return a1;
	}

	private Area2 getArea2(String name, Area1 parent) throws ShineException {
		Area2 area = locationsDao.getArea2(name, parent.getId());
		if (area == null) {
			area = new Area2();
			area.setName(name);
			area.setParent(parent);
			locationsDao.save(area);
		}
		return area;
	}

	private Area3 getArea3(String name, Area2 parent) throws ShineException {
		Area3 area = locationsDao.getArea3(name, parent.getId());
		if (area == null) {
			area = new Area3();
			area.setName(name);
			area.setParent(parent);
			locationsDao.save(area);
		}
		return area;
	}

	private Country getCountry(String name, String code) throws ShineException {
		Country c = locationsDao.getCountry(name, code);
		if (c == null) {
			c = new Country();
			c.setName(name);
			c.setCode(code);
			locationsDao.save(c);
		}
		return c;
	}

	private LocDetails getLocationDetails(InputStream is) {
		GoogleAddressParser gap = new GoogleAddressParser(is);
		gap.parse();
		LocDetails ld = gap.locDetails;
		return ld;
	}

	//
	// private InputStream hitGoogle(String address) {
	//
	// String strUrl;
	// try {
	// strUrl = googleGeoCodeUrl + URLEncoder.encode(address, "UTF-8");
	// return getInputStream(strUrl);
	// } catch (UnsupportedEncodingException e) {
	// log.error("error in finding address", e);
	// }
	// return null;
	// }


	@Transactional
	public void populateCountryOnlyBoards() {
		List<Integer> ids = boardDao.getBoardIdsWithCountryOnlyLocations();
		log.info(ids.size() + " boards to process");
		for (Integer integer : ids) {
			try {
				log.info("processing boardid " + integer);
				updateBoardLocation(integer);
			} catch (Exception e) {
				log.warn("processing boardid " + integer, e);
				e.printStackTrace();
			}
		}

	}

	@Transactional
	public void populateBoardLocations() throws ShineException {
		List<Integer> ids = boardDao.getBoardIdsWithNoLocation();
		log.info(ids.size() + " boards to process");
		for (Integer integer : ids) {
			log.info("processing boardid " + integer);
			updateBoardLocation(integer);
		}

	}

}
