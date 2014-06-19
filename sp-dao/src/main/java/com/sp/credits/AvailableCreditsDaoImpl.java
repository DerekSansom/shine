package com.sp.credits;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sp.entity.AvailableCreditsEntity;

@Repository
public class AvailableCreditsDaoImpl implements AvailableCreditsDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public AvailableCreditsEntity getAvailableCreditsByUserId(int user_id) {
		

		// THIS FAILS DUE TO UNMAPPED ENTITY
		Integer objUserId = new Integer(user_id);
		AvailableCreditsEntity availableCredits = entityManager.find(AvailableCreditsEntity.class, objUserId);
		return availableCredits;

		/*
		 * SO DOES THIS
		TypedQuery<AvailableCreditsEntity> query = null;
			query = entityManager.createQuery(
					"from com.sp.entity.AvailableCreditsEntity", AvailableCreditsEntity.class);
		//query.setParameter("id", user_id);
		List<AvailableCreditsEntity> list = query.getResultList();
		return null;
		*/

		// // TODO: Derek ... Kludge to keep working!!!
		// AvailableCreditsEntity availableCreditsEntity = new
		// AvailableCreditsEntity();
		// availableCreditsEntity.setId(user_id);
		// availableCreditsEntity.setAdvert(57);
		// availableCreditsEntity.setBoard(999);
		// return availableCreditsEntity;
		}
}
