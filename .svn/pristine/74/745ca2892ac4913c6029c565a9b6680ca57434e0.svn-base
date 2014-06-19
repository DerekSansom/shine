package com.sp.trend;

import java.util.List;

import com.shine.objects.ShineLocation;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.ReplyEntity;

public interface TrendDao {

	List<ReplyEntity> getReplies(int userid, int secondssince);

	List<PlayerEntity> getPlayers(ShineLocation loc, int userid, int secondssince, int maxDistance);

	List<NoticeEntity> getNotices(int userid, int secondssince);

	List<NoticeBoardEntity> getBoards(ShineLocation loc, int userid, int secondssince, int maxDistance);

	List<NoticeBoardEntity> getActiveBoards(ShineLocation loc, int secondssince, int maxDistance);

	List<NoticeBoardEntity> getUsersActiveBoards(int userid, int since);

	List<NoticeEntity> getNoticesSince(List<Integer> boardIds, int secondsSince, int userId);

}
