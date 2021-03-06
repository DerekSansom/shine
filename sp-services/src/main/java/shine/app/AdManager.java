package shine.app;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.AdMapper;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.boards.AdCategory;
import com.shine.boards.Advert;
import com.sp.advert.AdvertDao;
import com.sp.entity.ad.AdvertEntity;

@Component
public class AdManager extends BaseHandler {

	private static Logger log = LoggerFactory.getLogger(AdManager.class);

	private AdMapper mapper = new AdMapper();

	@Autowired
	private ImageHandler ih;

	@Autowired
	private AdvertDao advertDao;

	@Transactional
	public int createAdWithImage(Advert ad, BufferedImage image) throws ShineException {
		return ih.createAdWithImage(image, ad);
	}

	@Transactional
	public int createAd(Advert ad) throws ShineException {

		try {
			return advertDao.createAd(mapper.dtoToEntity(ad));
		} catch (Exception e) {
			log.warn("Failed to create notice", e);
			throw new ShineException(GeneralError.SYSTEM_ERROR, e.getMessage());
		}
	}

	@Transactional
	public Advert retrieveAd(int adid) throws ShineException {

		AdvertEntity ad = advertDao.getAd(adid);
		if (ad == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		Advert dto = mapper.entityToDto(ad);
		return dto;

	}

	@Transactional
	public List<Advert> advertSearch(int boardid, String searchString, int maxResults) {

		List<AdvertEntity> entities = advertDao.advertSearch(boardid, searchString, maxResults);
		return mapper.entityToDto(entities);
	}
	
	public List<Advert> getAllAdvertsPaginated(int start, int count) throws ShineException {
		List<AdvertEntity> advertEntities = advertDao.getAllAdverts(start, count);
		List<Advert> adverts = mapper.entityToDto(advertEntities);
		return adverts;
	}

	public Advert getAdvert(int id) {
		AdvertEntity entity = advertDao.getAd(id);
		Advert advert = mapper.entityToDto(entity);
		return advert;
	}

	public List<Advert> getAdvertsByBrandIds(List<Integer> brandIds) throws ShineException {
		List<AdvertEntity> advertEntities = advertDao.getAdvertsByBrandIds(brandIds);
		List<Advert> adverts = mapper.entityToDto(advertEntities);
		return adverts;
	}

	public List<AdCategory> getAllAdvertCategories() {
		List<AdCategory> adCategories = new ArrayList<AdCategory>();
		adCategories.add(AdCategory.EVENTS);
		adCategories.add(AdCategory.FOOD_DRINK);
		adCategories.add(AdCategory.HOME_GARDEN);
		adCategories.add(AdCategory.JOBS);
		adCategories.add(AdCategory.LEISURE);
		adCategories.add(AdCategory.SERVICES);
		adCategories.add(AdCategory.SHOPPING);
		adCategories.add(AdCategory.TRAVEL);
		return (adCategories);
	}
}
