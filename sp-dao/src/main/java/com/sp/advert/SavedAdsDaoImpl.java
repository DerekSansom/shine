package com.sp.advert;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sp.entity.savedads.UserSavedAd;

@Repository
public class SavedAdsDaoImpl implements SavedAdsDao {


	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int unsaveAll(int userid) {


		List<UserSavedAd> savedAds = findSavedAds(userid);
		for (UserSavedAd userSavedAd : savedAds) {
			entityManager.remove(userSavedAd);
		}
		return savedAds.size();

	}

	@Override
	public void saveAd(int userid, int adid) {

		UserSavedAd savedAd = new UserSavedAd(userid, adid);
		entityManager.persist(savedAd);
	}

	@Override
	public int unsaveAd(int userid, int adid) {

		List<UserSavedAd> savedAds = findSavedAd(userid, adid);

		for (UserSavedAd userSavedAd : savedAds) {
			entityManager.remove(userSavedAd);
		}
		return savedAds.size();
	}

	private List<UserSavedAd> findSavedAd(int userid, int adid) {
		String queryStr = "from UserSavedAd usa where usa.id.userId = :userid and usa.id.adId = :adid";

		TypedQuery<UserSavedAd> query = entityManager.createQuery(queryStr, UserSavedAd.class);
		query.setParameter("userid", userid);
		query.setParameter("adid", adid);

		List<UserSavedAd> list = query.getResultList();
		return list;
	}

	@Override
	public List<UserSavedAd> findSavedAds(int userid) {

		String queryStr = "from UserSavedAd usa where usa.id.userId = :userid";

		TypedQuery<UserSavedAd> query = entityManager.createQuery(queryStr, UserSavedAd.class);
		query.setParameter("userid", userid);

		List<UserSavedAd> list = query.getResultList();
		return list;

	}
}
