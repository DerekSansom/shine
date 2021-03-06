package com.sp.trending;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import shine.app.BaseHandler;
import shine.app.mapper.BoardMapper;
import shine.app.mapper.NoticeMapper;
import shine.app.mapper.PlayerMapper;
import shine.app.utils.ShineProperties;
import shine.xml.XmlUtil;

import com.shine.boards.BoardDetails;
import com.shine.boards.Notice;
import com.shine.boards.NoticeBoard;
import com.shine.boards.Reply;
import com.shine.objects.OtherPlayer;
import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.shine.xml.NoticeBoardXml;
import com.shine.xml.OtherPlayerXml;
import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.ReplyEntity;
import com.sp.notice.NoticeDao;
import com.sp.trend.TrendDao;

@Component
public class TrendManager extends BaseHandler {

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private TrendDao trendDao;

	private int MAX_DISTANCE;
	private int SECONDS_IN_PAST;
	private int MAX_COUNT;
	private BoardMapper mapper = new BoardMapper();
	private NoticeMapper noticeMapper = new NoticeMapper();
	private PlayerMapper playerMapper = new PlayerMapper();

	public List<ShineObject> getLatestTrendsOnUsersBoards(int userid, int max, int since) {
		if (MAX_DISTANCE == 0) {
			loadProperties();
		}

		if (max == 0) {
			max = MAX_COUNT;
		}

		if (since <= 0) {
			since = SECONDS_IN_PAST;
		}
		List<NoticeBoardEntity> activeBoards = trendDao.getUsersActiveBoards(userid, since);
		List<Integer> ints = getBoardIds(activeBoards);

		List<ShineObject> latest = new ArrayList<ShineObject>();
		List<NoticeEntity> noticeEntities = trendDao.getNoticesSince(ints, since, userid);
		List<Notice> notices = noticeMapper.entityToDto(noticeEntities);
		notices = decorateWithBoardDetails(notices);
		latest.addAll(notices);
//set userid t 0 to include users own replies
		List<ReplyEntity> replyEntities = trendDao.getReplies(0, since);

		List<Reply> replies = filterByBoardCreator(replyEntities, userid);

		latest.addAll(replies);
		return latest;

	}

	public List<ShineObject> getLatestTrends(ShineLocation loc, int userid, int max, int since) {
		return getLatest(loc, userid, max, since);
	}

	public List<ShineObject> getLatestTrends(ShineLocation loc, int userid) {
		return getLatest(loc, userid, 0, 0);
	}

	private List<ShineObject> getLatest(ShineLocation loc, int userid, int max, int since) {

		if (MAX_DISTANCE == 0) {
			loadProperties();
		}

		if (max == 0) {
			max = MAX_COUNT;
		}

		if (since <= 0) {
			since = SECONDS_IN_PAST;
		}
		List<ShineObject> latest = null;
		if (loc == null) {
			latest = getAllLatest(userid, max, since);
		} else {
			latest = getLocationLatest(loc, userid, max, since);
		}

		sortLatest(latest);

		return Collections.unmodifiableList(latest.subList(0, Math.min(max, latest.size())));
	}

	private List<ShineObject> getLocationLatest(ShineLocation loc, int userid, int max, int since) {

		List<ShineObject> latest = new ArrayList<ShineObject>();
		latest.addAll(mapper.entityToDto(trendDao.getBoards(loc, userid, since, MAX_DISTANCE)));
		latest.addAll(playerMapper.entityToDto(trendDao.getPlayers(loc, userid, since, MAX_DISTANCE)));

		List<NoticeBoardEntity> activeBoards = trendDao.getActiveBoards(loc, since, MAX_DISTANCE);
		List<Integer> activeBoardIds = getBoardIds(activeBoards);

		if (CollectionUtils.isEmpty(activeBoardIds)) {
			return Collections.emptyList();
		}
		List<Reply> replies = getReplies(userid, since, activeBoardIds);
		latest.addAll(replies);
		List<NoticeEntity> noticeEntities = trendDao.getNoticesSince(activeBoardIds, since, userid);
		
		List<Notice> notices = noticeMapper.entityToDto(noticeEntities);
		decorateWithBoardDetails(notices);

		latest.addAll(notices);
		return latest;
	}

	private List<Reply> getReplies(int userid, int since, List<Integer> activeBoardIds) {
		List<ReplyEntity> replyEntities = trendDao.getReplies(userid, since);

		List<Reply> filteredReplies = filterAndDecorateRepliesWithBoardDetails(replyEntities, activeBoardIds);
		return filteredReplies;
	}

	private List<Integer> getBoardIds(List<NoticeBoardEntity> activeBoards) {
		List<Integer> ints = new ArrayList<>();
		for (NoticeBoardEntity noticeBoardEntity : activeBoards) {
			ints.add(noticeBoardEntity.getId());
		}

		return ints;
	}

	private List<ShineObject> getAllLatest(int userid, int max, int since) {
		List<ShineObject> latest = new ArrayList<ShineObject>();
		latest.addAll(mapper.entityToDto(trendDao.getBoards(null, userid, since, 0)));
		latest.addAll(playerMapper.entityToDto(trendDao.getPlayers(null, userid, since, 0)));
		List<Reply> replies = getRepliesAllLatest(userid, since);
		latest.addAll(replies);
		List<Notice> notices = getNoticesAllLatest(userid, since);
		latest.addAll(notices);
		return latest;
	}

	private List<Notice> getNoticesAllLatest(int userid, int since) {
		List<NoticeEntity> noticeEntities = trendDao.getNotices(userid, since);
		List<Notice> notices = noticeMapper.entityToDto(noticeEntities);
		List<Notice> filteredNotices = decorateWithBoardDetails(notices);
		return filteredNotices;
	}

	private List<Reply> getRepliesAllLatest(int userid, int since) {
		List<ReplyEntity> replyEntities = trendDao.getReplies(userid, since);
		List<Reply> replies = noticeMapper.replyEntitiesToDto(replyEntities);
		decorateRepliesWithBoardDetails(replies);
		return replies;
	}

	private void decorateRepliesWithBoardDetails(List<Reply> replies) {

		for (Reply reply : replies) {
			NoticeEntity notice = noticeDao.getNotice(reply.getNoticeId());
			BoardDetails bd = popBoardDetails(notice.getBoardId());
			reply.setBoardDetails(bd);
		}
	}

	private List<Reply> filterAndDecorateRepliesWithBoardDetails(List<ReplyEntity> replies, List<Integer> activeBoardIds) {

		List<Reply> filtered = new ArrayList<>();
		for (ReplyEntity replyEntity : replies) {
			NoticeEntity notice = noticeDao.getNotice(replyEntity.getNoticeId());
			if (activeBoardIds.contains(notice.getBoardId())) {
				Reply reply = noticeMapper.entityToDto(replyEntity);
				BoardDetails bd = popBoardDetails(notice.getBoardId());
				reply.setBoardDetails(bd);
				filtered.add(reply);
			}

		}
		return filtered;
	}


	private List<Notice> decorateWithBoardDetails(List<Notice> notices) {

		List<Notice> filteredNotices = new ArrayList<>();
		for (Notice notice : notices) {
			BoardDetails bd = popBoardDetails(notice.getBoardId());
//			if (bd != null) {
				notice.setBoardDetails(bd);
				filteredNotices.add(notice);
//			}
		}
		return filteredNotices;
	}

	private BoardDetails popBoardDetails(int boardId) {

		NoticeBoardEntity board = boardDao.getNoticeBoard(boardId);
		BoardDetails bd = popBoardDetails(board);
		return bd;
	}

	private BoardDetails popBoardDetails(NoticeBoardEntity board) {
		if (board == null) {
			return null;
		}
		return new BoardDetails(board.getName(), board.getLocationName());
	}

	private void sortLatest(List<ShineObject> latest) {

		Collections.sort(latest, new Comparator<ShineObject>() {

			@Override
			public int compare(ShineObject o1, ShineObject o2) {
				Date d1 = o1.getCreated();
				Date d2 = o2.getCreated();
				if (d1 != null && d2 != null) {
					return d2.compareTo(d1);
				} else if (d1 == null && d2 == null) {
					return 0;
				}
				return d1 == null ? 1 : -1;

			}
		});

	}

	public String toTrendString(List<ShineObject> latest) {
		if (latest == null || latest.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder("<trends>");
		for (ShineObject shineObject : latest) {
			if (shineObject instanceof NoticeBoard) {
				NoticeBoardXml xml = new NoticeBoardXml((NoticeBoard) shineObject);
				sb.append(xml.toXmlLite());
			} else if (shineObject instanceof OtherPlayer) {
				OtherPlayerXml xml = new OtherPlayerXml((OtherPlayer) shineObject);
				sb.append(xml.toXmlLite());
			}
			else {
				sb.append(XmlUtil.getXml(shineObject));
			}
		}
		sb.append("</trends>");
		return sb.toString();
	}

	private void loadProperties() {
		MAX_DISTANCE = ShineProperties.getIntProperty(ShineProperties.TREND_DEFAULT_MAX_DISTANCE, 2);
		SECONDS_IN_PAST = ShineProperties.getIntProperty(ShineProperties.TREND_DEFAULT_MAX_AGE, 5 * 60 * 60 * 4);
		MAX_COUNT = ShineProperties.getIntProperty(ShineProperties.TREND_DEFAULT_MAX_COUNT, 10);
	}

	private List<Reply> filterByBoardCreator(List<ReplyEntity> replyEntities, int userid) {

		List<Reply> filtered = new ArrayList<>();
		for (ReplyEntity entity : replyEntities) {
			NoticeEntity notice = noticeDao.getNotice(entity.getNoticeId());
			if (idsEqual(notice.getPlayer(), userid)) {
				Reply reply = noticeMapper.entityToDto(entity);
				BoardDetails bd = popBoardDetails(notice.getBoardId());
				reply.setBoardDetails(bd);
				filtered.add(reply);
			} else {

				NoticeBoardEntity board = boardDao.getNoticeBoard(notice.getBoardId());
				if (board.getPlayer() != null && board.getPlayer().getId() == userid) {
					Reply reply = noticeMapper.entityToDto(entity);
					BoardDetails bd = popBoardDetails(board);
					reply.setBoardDetails(bd);
					filtered.add(reply);
				}
			}
		}
		return filtered;
	}

	private boolean idsEqual(PlayerEntity player, int userId) {

		if (player == null) {
			return false;
		}
		return player.getId() == userId;

	}

}
