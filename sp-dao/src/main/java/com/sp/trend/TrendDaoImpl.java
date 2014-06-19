package com.sp.trend;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.shine.objects.ShineLocation;
import com.sp.dao.util.DaoUtils;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.ReplyEntity;

@Repository
public class TrendDaoImpl implements TrendDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<PlayerEntity> getPlayers(ShineLocation loc, int userid, int secondssince, int maxDistance) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<PlayerEntity> cq = criteriaBuilder.createQuery(PlayerEntity.class);

		Root<PlayerEntity> locRoot = cq.from(PlayerEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		addSecondsSince(predicates, secondssince, locRoot, criteriaBuilder, "created");

		addExcludeUserId(predicates, userid, locRoot, criteriaBuilder);

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<PlayerEntity> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();
	}

	@Override
	public List<ReplyEntity> getReplies(int userid, int secondssince) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<ReplyEntity> cq = criteriaBuilder.createQuery(ReplyEntity.class);

		Root<ReplyEntity> locRoot = cq.from(ReplyEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		addSecondsSince(predicates, secondssince, locRoot, criteriaBuilder, "created");

		addExcludeUserId(predicates, userid, locRoot.<PlayerEntity> get("player"), criteriaBuilder);

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<ReplyEntity> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();
	}

	@Override
	public List<NoticeEntity> getNotices(int userid, int secondssince) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<NoticeEntity> cq = criteriaBuilder.createQuery(NoticeEntity.class);

		Root<NoticeEntity> locRoot = cq.from(NoticeEntity.class);

		List<Predicate> predicates = new ArrayList<>();

		addSecondsSince(predicates, secondssince, locRoot, criteriaBuilder, "created");

		addExcludeUserId(predicates, userid, locRoot.<PlayerEntity> get("player"), criteriaBuilder);
		addExcludeUserId(predicates, 1, locRoot.<PlayerEntity> get("player"), criteriaBuilder);

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<NoticeEntity> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();
	}

	@Override
	public List<NoticeBoardEntity> getBoards(ShineLocation loc, int userid, int secondssince, int maxDistance) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<NoticeBoardEntity> cq = criteriaBuilder.createQuery(NoticeBoardEntity.class);

		Root<NoticeBoardEntity> locRoot = cq.from(NoticeBoardEntity.class);

		List<Predicate> predicates = getActiveNonSuspendedBoardsPredicates(criteriaBuilder, locRoot);

		addSecondsSince(predicates, secondssince, locRoot, criteriaBuilder, "created");

		if (loc != null && maxDistance > 0) {
			predicates.add(DaoUtils.createLocationCriterion(loc, maxDistance, criteriaBuilder, locRoot));
		}
		addExcludeUserId(predicates, userid, locRoot.<PlayerEntity> get("player"), criteriaBuilder);

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<NoticeBoardEntity> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	@Override
	public List<NoticeBoardEntity> getActiveBoards(ShineLocation loc, int secondssince, int maxDistance) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<NoticeBoardEntity> cq = criteriaBuilder.createQuery(NoticeBoardEntity.class);

		Root<NoticeBoardEntity> locRoot = cq.from(NoticeBoardEntity.class);

		List<Predicate> predicates = getActiveNonSuspendedBoardsPredicates(criteriaBuilder, locRoot);

		addSecondsSince(predicates, secondssince, locRoot, criteriaBuilder, "lastUpdate");

		if (loc != null && maxDistance > 0) {
			predicates.add(DaoUtils.createLocationCriterion(loc, maxDistance, criteriaBuilder, locRoot));
		}
//		addExcludeUserId(predicates, userid, locRoot.<PlayerEntity> get("player"), criteriaBuilder);

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<NoticeBoardEntity> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	private void addSecondsSince(List<Predicate> predicates, int secondssince, Root<?> locRoot,
			CriteriaBuilder criteriaBuilder, String field) {
		if (secondssince > 0) {
			DateTime since = DateTime.now().minusSeconds((int) secondssince);
			predicates.add(criteriaBuilder.greaterThan(locRoot.<Date> get(field), since.toDate()));
		}

	}

	private void addExcludeUserId(List<Predicate> predicates, int userid, Path<PlayerEntity> path,
			CriteriaBuilder criteriaBuilder) {
		if (userid > 0) {
			predicates.add(criteriaBuilder.notEqual(path.get("id"), userid));
		}

	}

	@Override
	public List<NoticeBoardEntity> getUsersActiveBoards(int userid, int secondssince) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<NoticeBoardEntity> cq = criteriaBuilder.createQuery(NoticeBoardEntity.class);

		Root<NoticeBoardEntity> locRoot = cq.from(NoticeBoardEntity.class);

		List<Predicate> predicates = getActiveNonSuspendedBoardsPredicates(criteriaBuilder, locRoot);

		addSecondsSince(predicates, secondssince, locRoot, criteriaBuilder, "lastUpdate");

		if (userid > 0) {
			predicates.add(criteriaBuilder.equal(locRoot.<PlayerEntity> get("player").get("id"), userid));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<NoticeBoardEntity> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();
	}

	@Override
	public List<NoticeEntity> getNoticesSince(List<Integer> boardIds, int secondsSince, int userId) {

		Date now = new Date();
		DateTime since = DateTime.now().minusSeconds(secondsSince);

		TypedQuery<NoticeEntity> query = entityManager.createQuery(
				"from NoticeEntity n where n.boardId in (:boardIds) and (n.suspended is null or n.suspended = 0)"
								+ " and n.expires > :now and n.created > :since and n.player is not null and (n.player.id != 1 and n.player.id != :userId)",
						NoticeEntity.class);
		query.setParameter("boardIds", boardIds);
		query.setParameter("now", now);
		query.setParameter("userId", userId);
		query.setParameter("since", since.toDate());

		List<NoticeEntity> list = query.getResultList();
		return list;
	}

	private List<Predicate> getActiveNonSuspendedBoardsPredicates(CriteriaBuilder criteriaBuilder, Root<NoticeBoardEntity> locRoot) {
		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.isTrue(locRoot.<Boolean> get("active")));
		predicates.add(criteriaBuilder.isNull(locRoot.get("suspended")));
		return predicates;

	}

}
