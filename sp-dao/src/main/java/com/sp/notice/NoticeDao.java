package com.sp.notice;

import java.util.Date;
import java.util.List;

import com.sp.entity.NoticeEntity;
import com.sp.entity.ReportEntity;

public interface NoticeDao {

	public void createNotice(NoticeEntity notice);

	public boolean createReport(ReportEntity report);

	public List<NoticeEntity> getNotices(int boardid, int noticeIdToInclude, boolean inclSuspended);

	public NoticeEntity getNotice(int noticeId);

	public int getNoticesCount(int boardid);

	public List<NoticeEntity> getNotices(int boardid, int start, int maxCount, int catid, boolean inclSuspended);

	List<NoticeEntity> getUnExpiredNotices(int boardid, Date now, Date pastDate);

	public List<NoticeEntity> getExpiredNotices(int boardId, Date now, Date earliestToRetrieve);

	public List<NoticeEntity> getNoticesSince(int boardid, int secondsSince);

	public List<NoticeEntity> getNoticesSearch(int boardid, String searchString, int mode, int maxResults);

	public List<NoticeEntity> getNoticesByPlayer(int id, int i, int j, Date activitySince);

}
