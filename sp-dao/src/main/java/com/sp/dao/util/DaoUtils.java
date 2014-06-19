package com.sp.dao.util;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.shine.objects.ShineLocation;

public final class DaoUtils {

	public static Predicate addAllPredicates(List<Predicate> predicates, CriteriaBuilder criteriaBuilder) {

		if (predicates.size() == 1) {
			return predicates.get(0);
		}

		Predicate conjunction = criteriaBuilder.conjunction();
		for (Predicate predicate : predicates) {

			conjunction = criteriaBuilder.and(conjunction, predicate);
		}

		return conjunction;
	}

	public static Predicate createLocationCriterion(ShineLocation loc, double distanceTolerance, CriteriaBuilder criteriaBuilder,
			Root<?> locRoot) {

		return criteriaBuilder.and(
				criteriaBuilder.between(locRoot.<Double> get("lat"), loc.getLat() - distanceTolerance, loc.getLat() + distanceTolerance),
				criteriaBuilder.between(locRoot.<Double> get("lng"), loc.getLng() - distanceTolerance, loc.getLng() + distanceTolerance)

				);

	}

	public static <T> T getSingleResult(TypedQuery<T> query) {

		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
