package shine.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.ReportMapper;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.Report;
import com.sp.entity.NoticeEntity;
import com.sp.entity.ReplyEntity;
import com.sp.entity.ReportEntity;
import com.sp.notice.NoticeDao;
import com.sp.notice.ReplyDao;
import com.sp.report.ReportDao;

@Component
public class ReportManager extends BaseHandler {

	@Autowired
	private ReportMapper mapper;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private ReplyDao replyDao;

	@Autowired
	private ReportDao reportDao;

	@Transactional
	public int createReport(Report r) throws ShineException {
		try {
			ReportEntity entity = mapper.dtoToEntity(r);
			if (noticeDao.createReport(entity)) {
				return entity.getId();
			}
			throw new ShineException(GeneralError.SYSTEM_ERROR, "failed to create report");

		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.warn("Failed to create report", e);
			throw new ShineException(e);
		}

	}

	public int handleReport(int reportId, boolean accepted, String judgement, int adminId) throws ShineException {
		try {
			ReportEntity r = reportDao.getReport(reportId);
			if (r == null) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			reportDao.handleReport(reportId, accepted, judgement, adminId);
			return SharedConstants.SUCCESS;
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.warn("Failed to create report", e);
			throw new ShineException(e);
		}

	}

	public List<Report> getPendingReports() {


		try{
			List<Report> reports = mapper.entityToDto(reportDao.getPendingReports());
			for (Report report : reports) {
				addOffendingEntry(report);
			}

			return reports;
		}catch(Exception e){
			log.error("Failed to retrieve reports", e);
			throw new RuntimeException("Could not retrieve reports", e);
		}
		
		
	}

	private void addOffendingEntry(Report report) {

		if (report.getReplyId() != null) {
			ReplyEntity reply = replyDao.getReply(report.getReplyId());
			report.setOffendingEntry(reply.getReply());
		} else if (report.getNoticeId() != null && report.getNoticeId() > 0) {

			NoticeEntity notice = noticeDao.getNotice(report.getNoticeId());
			report.setOffendingEntry(notice.getTitle() + " : " + notice.getNotice());
		}

	}

}
