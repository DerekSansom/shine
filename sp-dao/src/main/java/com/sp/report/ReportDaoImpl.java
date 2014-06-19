package com.sp.report;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import shine.dao.ads.ReportUser;

import com.shine.GeneralError;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.ReplyEntity;
import com.sp.entity.ReportEntity;
import com.sp.exception.SpDaoException;
import com.sp.notice.NoticeDao;
import com.sp.notice.ReplyDao;

@Repository
public class ReportDaoImpl implements ReportDao {

	private static Logger log = LoggerFactory.getLogger(ReportDaoImpl.class);

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private ReplyDao replyDao;

	@PersistenceContext
	private EntityManager entityManager;

	public ReportEntity getReport(int id) {
		ReportEntity r = entityManager.find(ReportEntity.class, id);
		return r;
	}

	public void saveReport(ReportEntity report) throws Exception {

		entityManager.persist(report);

	}

	public void handleReport(int id, Boolean accepted, String judgement, int adminId) {

		ReportEntity report = getReport(id);
		if (report == null) {
			throw new SpDaoException(GeneralError.NOT_FOUND);
		}
		report.setAccepted(accepted);
		report.setJudgement(judgement);
		report.setJudgementDay(new Date());
		report.setAdminId(adminId);

		if (accepted) {
			if (report.getReplyId() != null && report.getReplyId() > 0) {
				ReplyEntity n = replyDao.getReply(report.getReplyId());
				n.setSuspended(true);
				entityManager.persist(n);
			} else {
				NoticeEntity n = noticeDao.getNotice(report.getNoticeId());
				n.setSuspended(true);
				entityManager.persist(n);
			}
		}

		entityManager.persist(report);
		log.debug("saved " + report);

	}

	public List<ReportEntity> getPendingReports() {

		TypedQuery<ReportEntity> query = entityManager.createQuery(
				"from ReportEntity where judgementDay is null", ReportEntity.class);

		List<ReportEntity> list = query.getResultList();
		return list;
	}

	public void save(ReportUser reportUser) {
		entityManager.persist(reportUser);
	}

	public void handleUserReport(ReportUser reportUser, PlayerEntity user) {

		try {
			entityManager.persist(reportUser);
			entityManager.persist(user);
		} catch (Exception e) {
			log.debug("Error trying to handle User report " + reportUser + " user:" + user, e);
			throw new SpDaoException(GeneralError.SYSTEM_ERROR, e);
		}

	}

	@Override
	public ReportUser getUserReport(int reportId) {
		ReportUser ru = entityManager.find(ReportUser.class, reportId);
		return ru;
	}
}
