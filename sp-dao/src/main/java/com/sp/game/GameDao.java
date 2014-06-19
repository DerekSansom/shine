package com.sp.game;

import static com.shine.GeneralError.NOT_FOUND;
import static com.shine.SharedConstants.DISTANCE_TOLERANCE;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.shine.objects.Diamond;
import com.shine.objects.Emerald;
import com.shine.objects.Ruby;
import com.shine.objects.Sapphire;
import com.shine.objects.ShineLocation;
import com.shine.objects.Stone;
import com.shine.objects.Player;
import com.sp.dao.util.DaoUtils;
import com.sp.entity.game.PlayerDiamond;
import com.sp.entity.game.PlayerEmerald;
import com.sp.entity.game.PlayerRuby;
import com.sp.entity.game.PlayerSapphire;
import com.sp.entity.game.PlayerStone;
import com.sp.exception.SpDaoException;

@Repository
public class GameDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public int createDiamond(Diamond diamond) {
		save(diamond);
		return diamond.getId();
	}

	@Transactional
	public int createSapphire(Sapphire sapphire) {
		save(sapphire);
		return sapphire.getId();
	}

	@Transactional
	public int createEmerald(Emerald emerald) {
		save(emerald);
		return emerald.getId();
	}

	/**
	 * Creates an Emerald and updates the user in the same atomic transaction
	 * 
	 * @param emerald
	 * @param user
	 * @return emeraldId
	 * @throws ShineException
	 */
	@Transactional
	public int createUserEmerald(Emerald emerald, Player user) {

		entityManager.persist(user);
		entityManager.persist(emerald);
		return emerald.getId();
	}

	@Transactional
	public int createRuby(Ruby ruby) {
		save(ruby);
		return ruby.getId();
	}

	public List<Diamond> getDiamonds(ShineLocation loc, long secondssince, double radius) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Diamond> cq = criteriaBuilder.createQuery(Diamond.class);

		Root<Diamond> locRoot = cq.from(Diamond.class);

		List<Predicate> predicates = new ArrayList<>();

		if (loc != null && radius > 0) {
			predicates.add(DaoUtils.createLocationCriterion(loc, radius, criteriaBuilder, locRoot));
		}
		if (secondssince > 0) {
			DateTime since = DateTime.now().minusSeconds((int) secondssince);
			predicates.add(criteriaBuilder.greaterThan(locRoot.<Date> get("created"), since.toDate()));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		TypedQuery<Diamond> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	public List<Diamond> getDiamonds(ShineLocation userloc, ShineLocation viewloc, Player user, double radius) {
		List<PlayerDiamond> emeralds = getPlayerStones(PlayerDiamond.class, user.getId());
		if (!emeralds.isEmpty()) {
			List<Integer> ids = convertIds(emeralds);
			return getStonesForPlayer(Diamond.class, userloc, viewloc, ids, radius);
		}

		return getStonesForPlayer(Diamond.class, userloc, viewloc, null, radius);

	}

	public Diamond getDiamond(int id) {
		return entityManager.find(Diamond.class, id);
	}

	public Emerald getEmerald(int id) {
		return entityManager.find(Emerald.class, id);
	}

	public Ruby getRuby(int id) {
		return entityManager.find(Ruby.class, id);
	}

	public List<Emerald> getEmeralds(ShineLocation loc) {
		return getEmeralds(loc, 0, DISTANCE_TOLERANCE);

	}

	public List<Emerald> getEmeralds(ShineLocation loc, long secondssince, double radius) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Emerald> cq = criteriaBuilder.createQuery(Emerald.class);

		Root<Emerald> locRoot = cq.from(Emerald.class);

		List<Predicate> predicates = new ArrayList<>();

		if (loc != null) {
			predicates.add(DaoUtils.createLocationCriterion(loc, radius, criteriaBuilder, locRoot));
		}
		if (secondssince > 0) {
			DateTime since = DateTime.now().minusSeconds((int) secondssince);
			predicates.add(criteriaBuilder.greaterThan(locRoot.<Date> get("created"), since.toDate()));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		TypedQuery<Emerald> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	public List<Sapphire> getSapphires(ShineLocation loc, long secondssince, double radius) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Sapphire> cq = criteriaBuilder.createQuery(Sapphire.class);

		Root<Sapphire> locRoot = cq.from(Sapphire.class);

		List<Predicate> predicates = new ArrayList<>();

		predicates.add(criteriaBuilder.or(
				criteriaBuilder.isNull(locRoot.get("ownerId")),
				criteriaBuilder.equal(locRoot.get("ownerId"), Integer.valueOf(0)))
				);

		if (loc != null && radius > 0) {
			predicates.add(DaoUtils.createLocationCriterion(loc, radius, criteriaBuilder, locRoot));
		}
		if (secondssince > 0) {
			DateTime since = DateTime.now().minusSeconds((int) secondssince);
			predicates.add(criteriaBuilder.greaterThan(locRoot.<Date> get("created"), since.toDate()));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		TypedQuery<Sapphire> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();
	}

	public List<Sapphire> getSapphires(ShineLocation userloc, ShineLocation viewloc, Player player, double radius) {
		List<PlayerSapphire> emeralds = getPlayerStones(PlayerSapphire.class, player.getId());
		if (!emeralds.isEmpty()) {
			List<Integer> ids = convertIds(emeralds);
			return getStonesForPlayer(Sapphire.class, userloc, viewloc, ids, radius);
		}

		return getStonesForPlayer(Sapphire.class, userloc, viewloc, null, radius);
	}

	public List<Emerald> getEmeralds(ShineLocation userloc, ShineLocation viewloc, Player player, double radius) {
		List<PlayerEmerald> emeralds = getPlayerStones(PlayerEmerald.class, player.getId());
		if (!emeralds.isEmpty()) {
			List<Integer> ids = convertIds(emeralds);
			return getStonesForPlayer(Emerald.class, userloc, viewloc, ids, radius);
		}

		return getStonesForPlayer(Emerald.class, userloc, viewloc, null, radius);

	}

	public List<Ruby> getRubies(ShineLocation loc, long secondssince, double radius) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Ruby> cq = criteriaBuilder.createQuery(Ruby.class);

		Root<Ruby> locRoot = cq.from(Ruby.class);

		List<Predicate> predicates = new ArrayList<>();

		if (loc != null && radius > 0) {
			predicates.add(DaoUtils.createLocationCriterion(loc, radius, criteriaBuilder, locRoot));
		}
		if (secondssince > 0) {
			DateTime since = DateTime.now().minusSeconds((int) secondssince);
			predicates.add(criteriaBuilder.greaterThan(locRoot.<Date> get("created"), since.toDate()));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		TypedQuery<Ruby> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	public List<Ruby> getRubies(ShineLocation userloc, ShineLocation viewloc, Player player, double radius) {

		List<PlayerRuby> rubies = getPlayerStones(PlayerRuby.class, player.getId());

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<Ruby> cq = criteriaBuilder.createQuery(Ruby.class);

		Root<Ruby> locRoot = cq.from(Ruby.class);

		List<Predicate> predicates = new ArrayList<>();

		if (userloc != null && radius > 0) {
			if (viewloc == null) {
				predicates.add(DaoUtils.createLocationCriterion(userloc, radius, criteriaBuilder, locRoot));
			} else {

				Predicate eitherLoc = criteriaBuilder.or(
						DaoUtils.createLocationCriterion(userloc, radius, criteriaBuilder, locRoot),
						DaoUtils.createLocationCriterion(viewloc, radius, criteriaBuilder, locRoot));
				predicates.add(eitherLoc);
			}
		}

		if (!rubies.isEmpty()) {
			List<Integer> ids = convertIds(rubies);
			Expression<Integer> rubyIds = locRoot.get("id");
			Expression<Integer> parentIds = locRoot.get("parentId");
			predicates.add(criteriaBuilder.not(rubyIds.in(ids)));
			predicates.add(

					criteriaBuilder.or(
							parentIds.in(ids),
							criteriaBuilder.isNull(locRoot.get("parentId"))
							)
					);
		} else {
			predicates.add(criteriaBuilder.isNull(locRoot.get("parentId")));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		TypedQuery<Ruby> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	private <T extends Stone> List<T> getStonesForPlayer(Class<T> c, ShineLocation userloc, ShineLocation viewloc,
			List<Integer> idsExclude, double radius) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = criteriaBuilder.createQuery(c);

		Root<T> locRoot = cq.from(c);

		List<Predicate> predicates = new ArrayList<>();

		if (viewloc == null) {
			predicates.add(DaoUtils.createLocationCriterion(userloc, radius, criteriaBuilder, locRoot));
		} else {

			Predicate eitherLoc = criteriaBuilder.or(
					DaoUtils.createLocationCriterion(userloc, radius, criteriaBuilder, locRoot),
					DaoUtils.createLocationCriterion(viewloc, radius, criteriaBuilder, locRoot));
			predicates.add(eitherLoc);
		}

		if (idsExclude != null && !idsExclude.isEmpty()) {

			Expression<Integer> ids = locRoot.get("id");

			predicates.add(criteriaBuilder.not(ids.in(idsExclude)));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		TypedQuery<T> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	private List<Integer> convertIds(List<? extends PlayerStone> stones) {
		List<Integer> ids = new ArrayList<Integer>();
		for (PlayerStone playerStone : stones) {
			ids.add(playerStone.getStoneId());
		}
		return ids;
	}

	public Sapphire getSapphire(int id) {
		return entityManager.find(Sapphire.class, id);
	}

	public void save(Stone stone) {
		entityManager.persist(stone);
	}

	public void save(Ruby ruby) {

		if (ruby.getParentId() != null) {
			Ruby parent = getRuby(ruby.getParentId());
			if (parent == null) {
				throw new SpDaoException(NOT_FOUND);
			}
			if (parent.getChildId() != null && (parent.getChildId() != ruby.getId())) {
				//parent already has another child
				throw new SpDaoException(NOT_FOUND);
			}

			entityManager.persist(ruby);
			parent.setChildId(ruby.getId());
			entityManager.persist(parent);

		} else {
			entityManager.persist(ruby);
		}
	}

	public void save(PlayerStone playerStone) {
		entityManager.persist(playerStone);
	}

	public void delete(Stone stone) {
		entityManager.remove(stone);
	}

	public <T extends PlayerStone> List<T> getPlayerStones(Class<T> c, int userid) {
		TypedQuery<T> query = entityManager.createQuery("from " + c.getSimpleName()
				+ " where userId = :userid", c);

		query.setParameter("userid", userid);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public <T extends PlayerStone> T getPlayerStone(Class<T> c, int userid, int stoneid) {

		TypedQuery<T> query = entityManager.createQuery("from " + c.getSimpleName()
				+ " where userId = :userid and stoneId = :stoneid", c);

		query.setParameter("stoneid", stoneid);
		query.setParameter("userid", userid);
		T playerStone = query.getSingleResult();

		return playerStone;

	}

	public <T extends PlayerStone> void dropStones(Class<T> c, int id) {

		List<T> pe = getPlayerStones(c, id);
		if (!pe.isEmpty()) {
			for (PlayerStone playerStone : pe) {
				entityManager.remove(playerStone);
			}
		}
	}

	public <T extends Stone> void delete(Class<T> c) {

		TypedQuery<T> query = entityManager.createQuery("from " + c.getSimpleName(), c);

		List<T> list = query.getResultList();

		for (T t : list) {
			entityManager.remove(t);
		}

	}

	public <T extends PlayerStone> void deletePlayerStones(Class<T> c) {
		TypedQuery<T> query = entityManager.createQuery("from " + c.getSimpleName(), c);

		List<T> list = query.getResultList();

		for (T t : list) {
			entityManager.remove(t);
		}
	}

	public void deleteDiamond(int id) {

		Diamond diamond = getDiamond(id);
		if(diamond != null){
			entityManager.remove(diamond);
		}
	}

	public int deleteDiamond(ShineLocation loc) {

		List<Diamond> list = getDiamondsByLocation(loc) ;
		for (Diamond diamond : list) {
			entityManager.remove(diamond);
		}
		return list.size();
	}

	private List<Diamond> getDiamondsByLocation(ShineLocation loc) {

			CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

			CriteriaQuery<Diamond> cq = criteriaBuilder.createQuery(Diamond.class);

			Root<Diamond> locRoot = cq.from(Diamond.class);

			List<Predicate> predicates = new ArrayList<>();

			if (loc != null) {
				predicates.add(DaoUtils.createLocationCriterion(loc, 0, criteriaBuilder, locRoot));
			}

			Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
			cq.where(conjunction);

			TypedQuery<Diamond> typedQuery = entityManager.createQuery(cq);
			return typedQuery.getResultList();

	}


}
