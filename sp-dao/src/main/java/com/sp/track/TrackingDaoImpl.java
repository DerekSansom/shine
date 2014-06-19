package com.sp.track;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.sp.entity.track.AdServe;
import com.sp.entity.track.TrackObject;

@Repository
public class TrackingDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	public void save(TrackObject trackObject) {
		entityManager.persist(trackObject);
	}

	public void save(AdServe as) {
		entityManager.persist(as);
	}

}
