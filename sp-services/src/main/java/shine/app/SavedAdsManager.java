package shine.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.AdMapper;
import shine.app.track.TrackingManager;
import shine.dao.exception.ShineException;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.Advert;
import com.sp.advert.AdvertDao;
import com.sp.advert.SavedAdsDao;
import com.sp.entity.ad.AdvertEntity;
import com.sp.entity.savedads.UserSavedAd;

@Component
public class SavedAdsManager extends BaseHandler {

	private static final int RETRIEVE_SAVED = 2;
	private AdMapper mapper = new AdMapper();

	@Autowired
	private SavedAdsDao savedAdsDao;

	@Autowired
	private AdvertDao advertDao;

	@Transactional
	public int saveAd(int userid, int adid) {

		// try {
		savedAdsDao.saveAd(userid, adid);
		return SharedConstants.SUCCESS;
		// } catch (Exception e) {
		// return handleSqlException(e);
		// }

	}

	@Transactional
	public int unsaveAd(int userid, int deleteid) {
		// SaveDao dao = new SaveDao(hibernateUtil.getSession());
		// try {
		savedAdsDao.unsaveAd(userid, deleteid);
		return SharedConstants.SUCCESS;

		// } catch (Exception e) {
		// return handleSqlException(e);
		// } finally {
		// dao.closeSession();
		// }

	}

	@Transactional
	public int unsaveAll(int userid) {
		// SaveDao dao = new SaveDao(hibernateUtil.getSession());
		// try {
		savedAdsDao.unsaveAll(userid);
		return SharedConstants.SUCCESS;
		// } catch (Exception e) {
		// return handleSqlException(e);
		// } finally {
		// dao.closeSession();
		// }

	}

	private int handleSqlException(Exception e) {
		log.debug("failure in SavedAdsManager", e);
		if (e.getCause() != null && e.getCause() instanceof MySQLIntegrityConstraintViolationException) {
			return SharedConstants.SUCCESS;
		}
		return GeneralError.SYSTEM_ERROR.getCode();
	}

	@Transactional
	public List<Advert> getSavedAds(int userid, int client) throws ShineException {
//		shine.dao.ad.AdvertDao adao = new shine.dao.ad.AdvertDao(hibernateUtil.getSession());
		// SaveDao sdao = new SaveDao(adao.getSession());
		TrackingManager.trackSaveAd(userid, 0, 0, RETRIEVE_SAVED, client);

		try {
			List<UserSavedAd> savedAds = savedAdsDao.findSavedAds(userid);
			if (savedAds.isEmpty()) {
				return Collections.emptyList();
			}
			List<Integer> savedAdIds = savedAdsToIds(savedAds);
			List<AdvertEntity> entities = advertDao.getAdvertsByIds(savedAdIds);
			List<Advert> list = mapper.entityToDto(entities);
			return list;

		} catch (Exception e) {
			log.debug("failure in SavedAdsServlet", e);
			throw new ShineException(e);
		}
//		finally {
//			adao.closeSession();
//		}
	}

	private List<Integer> savedAdsToIds(List<UserSavedAd> savedAds) {
		List<Integer> ids = new ArrayList<Integer>();
		for (UserSavedAd userSavedAd : savedAds) {
			ids.add(userSavedAd.getId().getAdId());
		}
		return ids;
	}
}
