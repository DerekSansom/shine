package com.sp.user;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sp.dao.util.DaoUtils;
import com.sp.entity.UserEntity;

@Repository
public class UserDaoImpl implements UserDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public UserEntity getUserById(int id) {
		UserEntity user = entityManager.find(UserEntity.class, id);
		return user;
	}

	@Override
	public UserEntity getUser(String credential, String password) {
		TypedQuery<UserEntity> query = entityManager.createQuery(
				"from UserEntity where (email=:credential or username=:credential) and password=:password",
				UserEntity.class);
		query.setParameter("credential", credential);
		query.setParameter("password", password);
		UserEntity user = DaoUtils.getSingleResult(query);
		return user;
	}

	@Override
	public UserEntity getUserByName(String username) {

		TypedQuery<UserEntity> query = entityManager.createQuery("from UserEntity where username=:username", UserEntity.class);
		query.setParameter("username", username);
		UserEntity user = DaoUtils.getSingleResult(query);
		return user;
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		TypedQuery<UserEntity> query = entityManager.createQuery("from UserEntity where email=:email", UserEntity.class);
		query.setParameter("email", email);
		UserEntity user = DaoUtils.getSingleResult(query);
		return user;
	}

}
