package com.sp.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sp.entity.auth.PasswordResetToken;

@Repository
public class PasswordResetDaoImpl implements PasswordResetDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Long getIdByToken(String token) {

		TypedQuery<PasswordResetToken> query = entityManager
				.createQuery("from PasswordResetToken t where t.token = :token", PasswordResetToken.class);
		query.setParameter("token", token);

		PasswordResetToken passwordResetToken = getSingleResult(query);
		if (passwordResetToken == null) {
			return null;
		}
		return passwordResetToken.getUserId();

	}

	@Override
	public PasswordResetToken createToken(long userId, String token) {

		PasswordResetToken entity = new PasswordResetToken();
		entity.setUserId(userId);
		entity.setToken(token);
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public PasswordResetToken getResetToken(String token) {
		TypedQuery<PasswordResetToken> query = entityManager
				.createQuery("from PasswordResetToken t where t.token = :token", PasswordResetToken.class);
		query.setParameter("token", token);

		return getSingleResult(query);
	}

	private PasswordResetToken getSingleResult(TypedQuery<PasswordResetToken> query) {

		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public void deleteToken(String token) {
		PasswordResetToken entity = getResetToken(token);
		if (entity != null) {
			entityManager.remove(entity);
		}

	}

}
