package com.sp.player;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.sp.dao.util.DaoUtils;

@Repository
public class PrivilegeDaoImpl {

	@PersistenceContext
	private EntityManager entityManager;

	Integer ZERO = new Integer(0);


	public Privileges getPrivileges(int userId, int friendId) {

		return loadPrivileges(userId, friendId);
	}

	private Privileges loadPrivileges(int userId, int friendId) {

		TypedQuery<Privileges> query = entityManager.createQuery("from Privileges where userId=:userId and friendId=:friendId",
				Privileges.class);
		query.setParameter("friendId", friendId);
		query.setParameter("userId", userId);
		Privileges p = DaoUtils.getSingleResult(query);
		return p;

	}

	public void createOrUpdatePrivilege(Privileges priv) {
		entityManager.persist(priv);
	}


	public List<Privileges> getAllPrivileges(int userId) {

		TypedQuery<Privileges> query = entityManager.createQuery("from Privileges where userId=:userId", Privileges.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	public List<Privileges> getAllFriendlyPrivileges(int userId) {

		TypedQuery<Privileges> query = entityManager.createQuery("from Privileges where userId=:userId and blocked is null",
				Privileges.class);
		query.setParameter("userId", userId);
		return query.getResultList();
	}

	public void deleteAllPrivileges() {

//TODO: looks like a test thing, should this be here
	}

	public List<Privileges> getPendingRequests(int userId, long secondssince) {

		TypedQuery<Privileges> query = entityManager.createQuery("from Privileges where friendId=:userId and privilege = 1",
				Privileges.class);
		query.setParameter("userId", userId);

//		if (secondssince > 0) {
//			Date since = DateTime.now().minusSeconds((int)secondssince).toDate();
//			crit.add(Restrictions.gt("created", since));
//		}

		return query.getResultList();

	}

}
