package com.sp.advert;

import java.util.ArrayList;
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
import org.springframework.transaction.annotation.Transactional;

import com.sp.dao.util.DaoUtils;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.BoardLoc;

@Repository
public class DefaultAdParamsDaoImpl implements DefaultAdParamsDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<DefaultAdParams> getNullLocationDefaultAdParams(int maxNumber) {

		String queryStr = "from DefaultAdParams dap where dap.boardId is null " +
				"and dap.area3Id is null " +
				"and dap.area2Id is null " +
				"and dap.area1Id is null " +
				"and dap.countryId is null";

		TypedQuery<DefaultAdParams> query = entityManager.createQuery(queryStr, DefaultAdParams.class);

		if (maxNumber > 0) {
			query.setMaxResults(maxNumber);
		}

		List<DefaultAdParams> list = query.getResultList();
		return list;
	}

	@Override
	public List<DefaultAdParams> getDefaultAdParams(BoardLoc boardLocation, Integer[] adIdsToexclude, int maxNumber) {

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

	@Override
	@Transactional
	public void removeCountryDefaultAds(int countryId, int adId) {
		
		String queryStr = "from DefaultAdParams dap where dap.countryId = :countryId and adId = :adId";
		TypedQuery<DefaultAdParams> query = entityManager.createQuery(queryStr, DefaultAdParams.class);
		query.setParameter("countryId", countryId);
		query.setParameter("adId", adId);
		
		DefaultAdParams toDelete = query.getSingleResult();
		entityManager.remove(toDelete);
		
	}
}
