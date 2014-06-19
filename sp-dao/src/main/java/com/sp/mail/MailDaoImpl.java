package com.sp.mail;

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

import com.sp.dao.util.DaoUtils;
import com.sp.entity.msg.MailEntity;

@Repository
public class MailDaoImpl implements MailDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public int createMail(MailEntity mail) {
		setRepliedMail(mail.getRepliedMailId());
		entityManager.persist(mail);
		return mail.getId();
	}

	private void setRepliedMail(Integer repliedMailId) {
		if (repliedMailId != null && repliedMailId > 0) {
			MailEntity replied = getMail(repliedMailId);
			if (replied != null) {
				replied.setReplied(true);
				entityManager.persist(replied);

			}
		}

	}

	private MailEntity getMail(int id) {
		return entityManager.find(MailEntity.class, id);
	}

	@Override
	public List<MailEntity> retrieveMailBySender(int userId, Date since) {
		List<MailEntity> sent = listMail("senderId", userId, 0, 0, since, false);
		return sent;
	}

	@Override
	public List<MailEntity> retrieveMailBySender(int userId, int numberToRetrieve, int start) {
		return listMail("senderId", userId, numberToRetrieve, start, null, false);
	}

	@Override
	public List<MailEntity> retrieveMailByRecipient(int userId, Date since) {
		return listMail("recipientId", userId, 0, 0, since, false);
	}

	@Override
	public List<MailEntity> retrieveMailByRecipient(int userId, int numberToRetrieve, int start) {
		return listMail("recipientId", userId, numberToRetrieve, start, null, false);
	}

	@Override
	public List<MailEntity> retrieveNewMailByRecipient(int userId) {
		return listMail("recipientId", userId, 0, 0, null, true);
	}

	private List<MailEntity> listMail(String field, int userId, int numberToRetrieve, int start, Date since,
			boolean undelivered) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<MailEntity> cq = criteriaBuilder.createQuery(MailEntity.class);

		Root<MailEntity> locRoot = cq.from(MailEntity.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.equal(locRoot.get(field), userId));

		if (since != null) {
			predicates.add(criteriaBuilder.greaterThan(locRoot.<Date> get("created"), since));
		}
		if (undelivered) {
			predicates.add(criteriaBuilder.isNull(locRoot.get("delivered")));

		}

		Predicate all = DaoUtils.addAllPredicates(predicates, criteriaBuilder);

		cq.where(all);

		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));

		TypedQuery<MailEntity> typedQuery = entityManager.createQuery(cq);

		if (numberToRetrieve > 0) {
			typedQuery.setMaxResults(numberToRetrieve);
			typedQuery.setFirstResult(start);
		}

		return typedQuery.getResultList();

	}

	@Override
	public void setDelivered(List<MailEntity> mail) {

		if (mail != null) {
			Date now = new Date();
			for (MailEntity m : mail) {
				if (m.getDelivered() == null) {
					m.setDelivered(now);
					entityManager.persist(m);
				}
			}
		}

	}

	@Override
	public int unreadMails(int userId) {

		String sql = "select count(1) from mail_msg where recipientid = " + userId
				+ " and (is_read is null or is_read=0)";
		Query q = entityManager.createNativeQuery(sql);

		Object o = q.getSingleResult();
		if (o != null) {
			if (o instanceof Number) {
				return ((Number) o).intValue();
			}

		}
		return 0;
	}

	@Override
	public void readMail(int mailId) {

		MailEntity mail = getMail(mailId);
		if (mail != null && !mail.isRead()) {
			mail.setRead(true);
			entityManager.persist(mail);
		}

	}

}
