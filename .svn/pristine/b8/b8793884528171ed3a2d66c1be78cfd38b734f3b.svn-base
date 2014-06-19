package com.sp.advert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sp.dao.util.DaoUtils;
import com.sp.entity.ad.AdDisplayParams;
import com.sp.entity.ad.AdvertEntity;
import com.sp.entity.ad.BoardDisplay;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.BoardLoc;

@Repository
public class AdvertDaoImpl implements AdvertDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<AdvertEntity> getAdvertsByIds(Integer[] adIds, Integer[] excludeCats) {

		if (adIds == null || adIds.length == 0) {
			return new ArrayList<AdvertEntity>();
		}
		String queryStr = "from AdvertEntity where id in (:adids)";
		if (excludeCats != null && excludeCats.length > 0) {
			queryStr += " and categoryId not in (:excludedCatIds)";
		}
		TypedQuery<AdvertEntity> query = entityManager.createQuery(queryStr, AdvertEntity.class);
		query.setParameter("adids", Arrays.asList(adIds));
		if (excludeCats != null && excludeCats.length > 0) {
			query.setParameter("excludedCatIds", Arrays.asList(excludeCats));
		}

		List<AdvertEntity> list = query.getResultList();
		return list;

	}

	@Override
	public List<AdvertEntity> getPaidAdvertsByLocation(BoardLoc boardLocation, Integer[] excludedCats) {

		if (boardLocation == null) {
			return new ArrayList<AdvertEntity>();
		}

		List<BoardDisplay> boardDisplays = getBoardDisplayByLocation(boardLocation);

		if (boardDisplays.isEmpty()) {
			return new ArrayList<AdvertEntity>();
		}

		List<Integer> dispIds = toDisplayIds(boardDisplays);

		List<AdDisplayParams> adDisplayParams = getAdDisplayParamsById(dispIds);

		Integer[] adIds = toAdIds(adDisplayParams);

		List<AdvertEntity> ads = getAdvertsByIds(adIds, excludedCats);
		Collections.shuffle(ads);
		return ads;

	}

	private List<AdDisplayParams> getAdDisplayParamsById(List<Integer> dispIds) {

		Date now = new Date();

		String queryStr = "from AdDisplayParams adp where adp.id in (:displayIds) and adp.end > :now and adp.start < :now";

		TypedQuery<AdDisplayParams> query = entityManager.createQuery(queryStr, AdDisplayParams.class);

		if (queryStr.contains(":displayIds"))
		query.setParameter("displayIds", dispIds);
		if (queryStr.contains(":now"))
		query.setParameter("now", now);

		List<AdDisplayParams> list = query.getResultList();
		return list;

	}

	private List<BoardDisplay> getBoardDisplayByLocation(BoardLoc boardLocation) {

		String queryStr = "from BoardDisplay where"
				+ " (area3Id is not null and area3Id=:area3Id) or"
				+ " boardId=:boardId or countryId=:countryId or area1Id=:area1Id or area2Id=:area2Id";

		TypedQuery<BoardDisplay> query = entityManager.createQuery(queryStr, BoardDisplay.class);
		query.setParameter("boardId", boardLocation.getBoardId());
		query.setParameter("countryId", boardLocation.getCountryId());
		query.setParameter("area1Id", boardLocation.getArea1Id());
		query.setParameter("area2Id", boardLocation.getArea2Id());
		query.setParameter("area3Id", boardLocation.getArea3Id());

		List<BoardDisplay> list = query.getResultList();
		return list;
	}

	List<DefaultAdParams> getDefaultAdParams(BoardLoc boardLocation, Integer[] adIdsToexclude, int maxNumber) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<DefaultAdParams> cq = criteriaBuilder.createQuery(DefaultAdParams.class);
		Root<DefaultAdParams> locRoot = cq.from(DefaultAdParams.class);
		cq.select(locRoot);
		List<Predicate> predictates = new ArrayList<>();
		predictates.add(getLocationRestrictions(criteriaBuilder, locRoot, boardLocation));

		if (adIdsToexclude.length > 0) {
			Expression<Integer> adIds = locRoot.get("adId");
			predictates.add(criteriaBuilder.not(adIds.in((Object[]) adIdsToexclude)));

		}
		Predicate allPredicates = DaoUtils.addAllPredicates(predictates, criteriaBuilder);

		cq.where(allPredicates);
		List<Order> orders = new ArrayList<>();

		orders.add(criteriaBuilder.desc(locRoot.get("boardId")));
		orders.add(criteriaBuilder.desc(locRoot.get("area3Id")));
		orders.add(criteriaBuilder.desc(locRoot.get("area2Id")));
		orders.add(criteriaBuilder.desc(locRoot.get("area1Id")));
		orders.add(criteriaBuilder.desc(locRoot.get("countryId")));

		cq.orderBy(orders);

		TypedQuery<DefaultAdParams> typedQuery = entityManager.createQuery(cq);

		if (maxNumber > 0) {
			typedQuery.setMaxResults(maxNumber);
		}

		return (List<DefaultAdParams>) typedQuery.getResultList();

	}

	private Predicate getLocationRestrictions(CriteriaBuilder criteriaBuilder, Root<DefaultAdParams> locRoot, BoardLoc boardLocation) {

		Predicate predicate = null;
		predicate = addIdRestriction(predicate, criteriaBuilder, locRoot, "boardId", boardLocation.getBoardId());
		predicate = addIdRestriction(predicate, criteriaBuilder, locRoot, "area3Id", boardLocation.getArea3Id());
		predicate = addIdRestriction(predicate, criteriaBuilder, locRoot, "area2Id", boardLocation.getArea2Id());
		predicate = addIdRestriction(predicate, criteriaBuilder, locRoot, "area1Id", boardLocation.getArea1Id());
		predicate = addIdRestriction(predicate, criteriaBuilder, locRoot, "countryId", boardLocation.getCountryId());
		return predicate;
	}

	private Predicate addIdRestriction(Predicate predicate, CriteriaBuilder criteriaBuilder, Root<DefaultAdParams> locRoot,
			String paramName, Integer value) {

		if (value != null && value > 0) {
			Predicate p = criteriaBuilder.equal(locRoot.get(paramName), value);
			if (predicate == null) {
				return p;
			} else {
				return criteriaBuilder.or(predicate, p);
			}
		}
		return predicate;

	}

	private Integer[] toAdIds(List<AdDisplayParams> adDisplayParams) {
		Integer[] adIds = new Integer[adDisplayParams.size()];
		int i = 0;
		for (AdDisplayParams adDisplayParam : adDisplayParams) {
			adIds[i++] = adDisplayParam.getAdvertId();
		}

		return adIds;
	}

	private List<Integer> toDisplayIds(List<BoardDisplay> boardDisplays) {
		List<Integer> dispIds = new ArrayList<Integer>();

		for (BoardDisplay boardDisplay : boardDisplays) {
			dispIds.add(boardDisplay.getDisplayId());
		}

		return dispIds;
	}

	@Override
	public int saveOrUpdateAd(AdvertEntity ad) {

		entityManager.persist(ad);
		return ad.getId();

	}

	@Override
	public AdvertEntity getAd(int adid) {

		return entityManager.find(AdvertEntity.class, adid);
	}

	@Override
	public int createAd(AdvertEntity ad) {

		entityManager.persist(ad);

		return ad.getId();

	}

	@Override
	public List<AdvertEntity> advertSearch(int boardid, String str, int maxResults) {

		String queryStr = "from AdvertEntity ae where title like '%:search%' or text like '%:search%' or displayname like '%:search%'";

		TypedQuery<AdvertEntity> query = entityManager.createQuery(queryStr, AdvertEntity.class);
		query.setParameter("search", str);

		if (maxResults > 0) {
			query.setMaxResults(maxResults);
		}

		List<AdvertEntity> list = query.getResultList();
		return list;

	}

	@Override
	public List<AdvertEntity> getAdvertsByIds(List<Integer> adIds) {
		if (adIds == null || adIds.size() == 0) {
			return new ArrayList<AdvertEntity>();
		}
		String queryStr = "from AdvertEntity where id in (:adids)";

		TypedQuery<AdvertEntity> query = entityManager.createQuery(queryStr, AdvertEntity.class);
		query.setParameter("adids", adIds);

		List<AdvertEntity> list = query.getResultList();
		return list;

	}
	
	@Override
	public List<AdvertEntity> getAllAdverts(int start, int count) {
		String queryStr = "from AdvertEntity";

		TypedQuery<AdvertEntity> query = entityManager.createQuery(queryStr, AdvertEntity.class);
		query.setMaxResults(count);
		query.setFirstResult(start);

		List<AdvertEntity> list = query.getResultList();
		return list;

	}

	@Override
	public List<AdvertEntity> getAdvertsByBrandIds(List<Integer> brandIds) {
		if (brandIds == null || brandIds.size() == 0) {
			return new ArrayList<AdvertEntity>();
		}
		String queryStr = "from AdvertEntity where brandid in (:brandIds)";

		TypedQuery<AdvertEntity> query = entityManager.createQuery(queryStr, AdvertEntity.class);
		query.setParameter("brandIds", brandIds);

		List<AdvertEntity> list = query.getResultList();
		return list;
	}

}
