package com.sp.notice;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.sp.entity.ReplyEntity;

@Repository
public class ReplyDaoImpl implements ReplyDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createReply(ReplyEntity reply) {

		entityManager.persist(reply);
	}

	@Override
	public List<ReplyEntity> getReplies(int noticeid, boolean inclSuspended) {

		return getReplies(noticeid, 0, 0, inclSuspended);
	}

	@Override
	public List<ReplyEntity> getReplies(int noticeid, int start, int maxCount, boolean inclSuspended) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<ReplyEntity> cq = criteriaBuilder.createQuery(ReplyEntity.class);

		Root<ReplyEntity> locRoot = cq.from(ReplyEntity.class);

		if (!inclSuspended) {
			cq.where(
					criteriaBuilder.equal(locRoot.get("noticeId"), noticeid),

					criteriaBuilder.or(
							criteriaBuilder.isNull(locRoot.get("suspended")),
							criteriaBuilder.equal(locRoot.get("suspended"), false))
					);

		} else {
			cq.where(criteriaBuilder.equal(locRoot.get("noticeId"), noticeid));

		}


		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<ReplyEntity> typedQuery = entityManager.createQuery(cq);
		// TODO: add groupby
		if (maxCount > 0) {
			typedQuery.setMaxResults(maxCount);
			typedQuery.setFirstResult(start);

		}

		return typedQuery.getResultList();

	}

	@Override
	public ReplyEntity getReply(int replyid) {

		ReplyEntity r = entityManager.find(ReplyEntity.class, replyid);
		return r;

	}

	@Override
	public int getRepliesCount(int noticeid) {

		String sql = "select count(1) from replies where noticeId=" + noticeid;
		return ((BigInteger) entityManager.createNativeQuery(sql).getSingleResult()).intValue();

	}

	@Override
	public List<ReplyEntity> getRepliesByPlayer(int playerid, int start, int count, Date activitySince) {

		StringBuilder sb = new StringBuilder(
				"from ReplyEntity r where r.player.id=:creatorid and r.created > :since and (r.suspended is null or r.suspended=false)");

		sb.append(" order by r.created desc ");

		TypedQuery<ReplyEntity> query = entityManager.createQuery(sb.toString(), ReplyEntity.class);
		query.setParameter("creatorid", playerid);
		query.setParameter("since", activitySince);

		if (count > 0) {
			query.setMaxResults(count);
		}
		if (start > 0) {
			query.setFirstResult(start);
		}

		List<ReplyEntity> list = query.getResultList();
		return list;

		// Criteria crit = sess.createCriteria(ReplyEntity.class);
		// crit.add(Restrictions.eq("player.id", playerid));
		// crit.add(Restrictions.or(Restrictions.isNull("suspended"),
		// Restrictions.eq("suspended", false)));
		// setPagination(crit, count, start);
		// addSinceDateCrit(crit, since);
		// List<ReplyEntity> replies = listAndCast(crit);
		//
		// return replies;
	}

}
