package com.sp.notice;

import java.util.Date;
import java.util.List;

import com.sp.entity.ReplyEntity;

public interface ReplyDao {

	public void createReply(ReplyEntity reply);

	public List<ReplyEntity> getReplies(int noticeid, boolean inclSuspended);

	public List<ReplyEntity> getReplies(int noticeid, int start, int maxCount, boolean inclSuspended);

	public ReplyEntity getReply(int replyid);

	public int getRepliesCount(int noticeid);

	public List<ReplyEntity> getRepliesByPlayer(int id, int i, int j, Date activitySince);
}
