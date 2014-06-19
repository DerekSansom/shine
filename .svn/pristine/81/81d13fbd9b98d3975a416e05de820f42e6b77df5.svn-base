package com.sp.notice;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.NoticeCategory;
import com.sp.entity.NoticeEntity;
import com.sp.entity.ReplyEntity;
import com.sp.entity.ReportEntity;
import com.sp.exception.SpDaoException;

@Repository
public class NoticeDaoImpl implements NoticeDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void createNotice(NoticeEntity notice) {

		entityManager.persist(notice);
	}


	@Override
	public boolean createReport(ReportEntity report) {

		entityManager.persist(report);
		if (report.getReplyId() != null && report.getReplyId() > 0) {
			ReplyEntity n = getReply(report.getReplyId());
			if (n == null) {
				throw new SpDaoException(GeneralError.NOT_FOUND);
			}
			n.setReported(true);
			// entityManager.persist(n);
		} else {
			NoticeEntity n = getNotice(report.getNoticeId());
			if (n == null) {
				throw new SpDaoException(GeneralError.NOT_FOUND);
			}
			n.setReported(true);
			// entityManager.merge(n);
		}
		return true;

	}


	@Override
	public List<NoticeEntity> getNotices(int boardid, int noticeIdToInclude, boolean inclSuspended) {

		int start = 0;
		int count = 10;
		boolean gotNotice = false;
		List<NoticeEntity> all = new ArrayList<NoticeEntity>();

		while (true) {

			List<NoticeEntity> notices =
					getNotices(boardid, start, count, NoticeCategory.ALL, inclSuspended);
			all.addAll(notices);
			for (NoticeEntity notice : notices) {
				if (notice.getId() == noticeIdToInclude) {
					gotNotice = true;
				}
				// TODO: not sure players should be populated here, id is
				// enough, to
				// map and avoid duplicating effort where required
//				Player p = pdao.getPlayerById(notice.getCreatorId());
//				if (p != null) {
//					notice.setPlayer(p);
//				}
			}
			if (notices.size() < count) {
				break;
			}
			if (gotNotice && notices.get(notices.size() - 1).getId() != noticeIdToInclude) {
				break;
			}
			start += notices.size();
		}
		return all;
	}

	@Override
	public NoticeEntity getNotice(int noticeId) {

		NoticeEntity n = entityManager.find(NoticeEntity.class, noticeId);
		return n;
	}

	@Override
	public int getNoticesCount(int boardid) {

		String sql = "select count(1) from notices where expires >= NOW() and boardid=" + boardid;
		Object o = entityManager.createNativeQuery(sql).getSingleResult();
		return ((BigInteger) o).intValue();

	}

	@Override
	public List<NoticeEntity> getNotices(int boardid, int start, int maxCount, int catid, boolean inclSuspended) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<NoticeEntity> cq = criteriaBuilder.createQuery(NoticeEntity.class);

		Root<NoticeEntity> locRoot = cq.from(NoticeEntity.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(locRoot.get("boardId"), boardid));
		predicates.add(criteriaBuilder.greaterThan(locRoot.<Date> get("expires"), DateTime.now().toDate()));

		if (!inclSuspended) {
			predicates.add(criteriaBuilder.equal(locRoot.get("suspended"), false));
		}
		if (catid > 0) {
			predicates.add(criteriaBuilder.equal(locRoot.get("categoryid"), catid));
		}

		if (predicates.size() == 2) {
			cq.where(predicates.get(0), predicates.get(1));

		} else if (predicates.size() == 3) {
			cq.where(predicates.get(0), predicates.get(1), predicates.get(2));

		} else if (predicates.size() == 4) {
			cq.where(predicates.get(0), predicates.get(1), predicates.get(2), predicates.get(3));

		}

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));


		TypedQuery<NoticeEntity> typedQuery = entityManager.createQuery(cq);
		// TODO: add groupby
		if (maxCount > 0) {
			typedQuery.setMaxResults(maxCount);
			typedQuery.setFirstResult(start);

		}

		return typedQuery.getResultList();

	}

	@Override
	public List<NoticeEntity> getUnExpiredNotices(int boardid, Date now, Date pastDate) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<NoticeEntity> cq = criteriaBuilder.createQuery(NoticeEntity.class);
		Root<NoticeEntity> locRoot = cq.from(NoticeEntity.class);

		cq.where(criteriaBuilder.equal(locRoot.get("boardId"), boardid),
				criteriaBuilder.greaterThan(locRoot.<Date> get("created"), pastDate),
				criteriaBuilder.greaterThanOrEqualTo(locRoot.<Date> get("expires"), now)
				);

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<NoticeEntity> typedQuery = entityManager.createQuery(cq);

		return typedQuery.getResultList();

	}

	@Override
	public List<NoticeEntity> getExpiredNotices(int boardid, Date now, Date pastDate) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<NoticeEntity> cq = criteriaBuilder.createQuery(NoticeEntity.class);
		Root<NoticeEntity> locRoot = cq.from(NoticeEntity.class);

		cq.where(criteriaBuilder.equal(locRoot.get("boardId"), boardid),
				criteriaBuilder.greaterThan(locRoot.<Date> get("created"), pastDate),
				criteriaBuilder.lessThan(locRoot.<Date> get("expires"), now));

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<NoticeEntity> typedQuery = entityManager.createQuery(cq);

		return typedQuery.getResultList();

	}

	private ReplyEntity getReply(int replyid) {

		ReplyEntity r = entityManager.find(ReplyEntity.class, replyid);
		return r;

	}

	@Override
	public List<NoticeEntity> getNoticesSince(int boardId, int secondsSince) {

		Date now = new Date();
		DateTime since = DateTime.now().minusSeconds(secondsSince);

		TypedQuery<NoticeEntity> query = entityManager.createQuery(
				"from NoticeEntity n where n.boardId = :boardId and (n.suspended is null or n.suspended = 0)"
						+ " and n.expires > :now and n.created > :since", NoticeEntity.class);
		query.setParameter("boardId", boardId);
		query.setParameter("now", now);
		query.setParameter("since", since.toDate());

		List<NoticeEntity> list = query.getResultList();
		return list;
	}

	@Override
	public List<NoticeEntity> getNoticesSearch(int boardid, String str, int mode, int resultsRequired) {

		
		StringBuilder sb = new StringBuilder("from NoticeEntity n where (n.suspended is null or n.suspended = 0) and (n.expires > :now))");
		if (boardid > 0) {
			sb.append(" and n.boardid=:boardid");
		}
		switch (mode) {
		case SharedConstants.NOTICE_ALL_FIELDS:
		case SharedConstants.NOTICE_AND_CREATOR:
			sb.append(" and (n.title like ('%:search%')")
					.append(" or n.notice like ('%:search%')")
					.append(" or n.player.username like ('%:search%'))");

			break;
		case SharedConstants.NOTICE_FIELDS_ONLY:
			sb.append(" and (n.title like ('%:search%')")
					.append(" or n.notice like ('%:search%'))");

			break;
		case SharedConstants.NOTICE_CREATOR_ONLY:
			sb.append(" and n.player.username like ('%:search%')");
			break;
		default:
			break;
		}

		sb.append(" order by n.created desc ");

		Date now = new Date();

		TypedQuery<NoticeEntity> query = entityManager.createQuery(sb.toString(), NoticeEntity.class);
		query.setParameter("boardid", boardid);
		query.setParameter("now", now);
		query.setParameter("search", str);

		if (resultsRequired > 0) {
			query.setMaxResults(resultsRequired);
		}
		List<NoticeEntity> list = query.getResultList();
		return list;

		// sb.append(" group by notices.id order by created desc");
		// if (resultsRequired > 0) {
		// sb.append(" LIMIT 0, ")
		// .append(resultsRequired);
		//
		// }
		//
		// return doQuery(sb.toString(), NoticeEntity.class);
	}

	public List<NoticeEntity> getNoticesByPlayer(int playerid, int start, int count, Date activitySince) {

		StringBuilder sb = new StringBuilder("from NoticeEntity n where n.player.id=:creatorid and n.created > :since");

		sb.append(" order by n.created desc ");

		TypedQuery<NoticeEntity> query = entityManager.createQuery(sb.toString(), NoticeEntity.class);
		query.setParameter("creatorid", playerid);
		query.setParameter("since", activitySince);

		if (count > 0) {
			query.setMaxResults(count);
		}
		if (start > 0) {
			query.setFirstResult(start);
		}

		List<NoticeEntity> list = query.getResultList();
		return list;

//		Criteria crit = sess.createCriteria(NoticeEntity.class);
//		crit.add(Restrictions.eq("player.id", playerid));
//		crit.add(Restrictions.gt("created", activitySince));
//		// crit.add(Restrictions.gt("expires", now));
//		crit.addOrder(Order.desc("created"));
//

//		setPagination(crit, count, start);
//		List<NoticeEntity> list = listAndCast(crit);
//
//		return list;

	}


}
