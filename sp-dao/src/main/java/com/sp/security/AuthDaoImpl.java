package com.sp.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sp.entity.auth.AuthToken;

@Repository
public class AuthDaoImpl implements AuthDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long getIdByToken(String token) {

		TypedQuery<AuthToken> query = entityManager.createQuery("from AuthToken t where t.token = :token", AuthToken.class);
		query.setParameter("token", token);

		AuthToken authToken = getSingleResult(query);
		if (authToken == null) {
			return null;
		}
		return authToken.getUserId();

	}

	@Override
	public AuthToken createToken(long userId, String token) {

		AuthToken entity = new AuthToken();
		entity.setUserId(userId);
		entity.setToken(token);
		entityManager.persist(entity);
		return entity;
	}

	private AuthToken getSingleResult(TypedQuery<AuthToken> query) {

		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}
