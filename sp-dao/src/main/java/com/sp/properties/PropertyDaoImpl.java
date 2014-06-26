package com.sp.properties;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class PropertyDaoImpl implements PropertyDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PropertyEntity getProperty(String key) {
		TypedQuery<PropertyEntity> query = entityManager.createQuery(
				"from PropertyEntity where pkey = :key", PropertyEntity.class);
		query.setParameter("key", key);
		return query.getSingleResult();

	}

	@Override
	public List<PropertyEntity> getAllProperties() {
		TypedQuery<PropertyEntity> query = entityManager.createQuery(
				"from PropertyEntity", PropertyEntity.class);
		return query.getResultList();
	}

}
