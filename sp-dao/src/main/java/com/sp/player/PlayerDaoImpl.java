package com.sp.player;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.shine.objects.ShineLocation;
import com.sp.dao.util.DaoUtils;
import com.sp.entity.PlayerEntity;

@Repository
public class PlayerDaoImpl implements PlayerDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(PlayerEntity user) {
		entityManager.persist(user);
	}

	@Override
	public PlayerEntity getPlayerById(int id) {
		PlayerEntity p = entityManager.find(PlayerEntity.class, id);
		return p;
	}

	@Override
	public PlayerEntity getPlayerByName(String username) {

		TypedQuery<PlayerEntity> query = entityManager.createQuery("from PlayerEntity where username=:username", PlayerEntity.class);
		query.setParameter("username", username);
		PlayerEntity p = DaoUtils.getSingleResult(query);
		return p;

	}

	@Override
	public PlayerEntity getUserByEmail(String email) {

		TypedQuery<PlayerEntity> query = entityManager.createQuery("from PlayerEntity where email=:email",
				PlayerEntity.class);
		query.setParameter("email", email);
		PlayerEntity p = DaoUtils.getSingleResult(query);
		return p;

	}

	@Override
	public PlayerEntity getUserByCredential(String credential) {

		TypedQuery<PlayerEntity> query = entityManager.createQuery(
				"from PlayerEntity where (email=:credential or username=:credential)",
				PlayerEntity.class);
		query.setParameter("credential", credential);
		PlayerEntity p = DaoUtils.getSingleResult(query);
		return p;

	}

	@Override
	public PlayerEntity doLogin(String credential, String password) {

		TypedQuery<PlayerEntity> query = entityManager.createQuery(
				"from PlayerEntity where (email=:credential or username=:credential) and password=:password",
				PlayerEntity.class);
		query.setParameter("credential", credential);
		query.setParameter("password", password);
		PlayerEntity p = DaoUtils.getSingleResult(query);
		return p;

	}

	@Override
	public void updateUserWithLoc(PlayerEntity player, ShineLocation loc) {

		if (loc != null) {
			player.setLat(loc.getLat());
			player.setLng(loc.getLng());
			player.setUpdated(new Date());
			entityManager.persist(player);

			// TODO: entitise playerloc
			// PlayerLocEntity playerLoc = new PlayerLocEntity(player.getId(),
			// loc.getLat(), loc.getLng());
			// entityManager.persist(playerLoc);

			Query sqlq = entityManager.createNativeQuery("insert into player_loc (userid, lat, lng) values("
					+ player.getId()
					+ "," + loc.getLat() + "," + loc.getLng() + ")");
			sqlq.executeUpdate();
		}

	}

	@Override
	public List<PlayerEntity> getNearPlayers(ShineLocation userloc, int id, double radius) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<PlayerEntity> cq = criteriaBuilder.createQuery(PlayerEntity.class);

		Root<PlayerEntity> locRoot = cq.from(PlayerEntity.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.isNull(locRoot.get("suspended")));

		if (userloc != null && radius > 0) {
			predicates.add(DaoUtils.createLocationCriterion(userloc, radius, criteriaBuilder, locRoot));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		TypedQuery<PlayerEntity> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	@Override
	public void deleteUser(int id) {

		PlayerEntity player = getPlayerById(id);
		if (player != null) {
			entityManager.remove(player);
		}

	}

}
