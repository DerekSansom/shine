package shine.app;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.BoardMapper;
import shine.app.mapper.NoticeMapper;
import shine.app.mapper.PlayerMapper;
import shine.app.utils.ShineProperties;
import shine.dao.ads.ReportBoard;
import shine.dao.exception.ShineException;
import shine.util.WebAppUtils;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.Advert;
import com.shine.boards.Notice;
import com.shine.boards.NoticeBoard;
import com.shine.boards.NoticeCategory;
import com.shine.objects.OtherPlayer;
import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.ReplyEntity;
import com.sp.entity.ad.ExcludedCategory;
import com.sp.entity.loc.BoardLoc;
import com.sp.notice.NoticeDao;
import com.sp.notice.ReplyDao;
import com.sp.player.PlayerDao;

@Component
public class BoardManager {

	private static Logger log = LoggerFactory.getLogger(BoardManager.class);

	@Autowired
	private BoardMapper mapper;

	@Autowired
	private NoticeMapper noticeMapper;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private ReplyDao replyDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private DefaultNoticeCreator defaultNoticeCreator;

	@Autowired
	private AdServer adServer;

	private PlayerMapper pmapper = new PlayerMapper();

	@Transactional
	public NoticeBoard getAnonNoticeBoard(int boardId) throws ShineException {

		NoticeBoardEntity board = boardDao.getNoticeBoard(boardId);
		if (board == null) {
			throw new ShineException(GeneralError.NOT_FOUND, "Board id:" + boardId + "not found");
		}
		NoticeBoard boardDto = mapper.entityToDto(board);
		populateNoticeDetails(boardDto, boardId, 0);
		List<OtherPlayer> activeUsers = getActiveUsers(boardDto.getId());
		boardDto.setUserCount(activeUsers.size());

		populateAds(boardDto, 0);

		return boardDto;

	}

	private void populateAds(NoticeBoard board, int userId) {
		List<Advert> ads = getAds(board, userId);
		for (Advert advert : ads) {
			populateDisplayName(advert);
		}

		board.setAds(ads);
		board.setAdvertCount(ads.size());

	}

	private void populateDisplayName(Advert advert) {

		if (advert.getBrand() != null) {
			advert.setDisplayname(advert.getBrand().getName());
		} else if (advert.getUserId() != null) {
			PlayerEntity player = playerDao.getPlayerById(advert.getUserId());
			advert.setDisplayname(player.getUsername());
		}
	}

	public NoticeBoard getBoardByName(String name, int userid) throws ShineException {

		List<NoticeBoardEntity> boards = boardDao.getBoardsByName(name);
		if (boards.isEmpty()) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}

		if (boards.size() > 1) {
			throw new ShineException(GeneralError.NOT_FOUND, "More than one result");

		}

		return validatePopulateBoard(boards.get(0), userid);
	}

	public List<NoticeBoard> getAllBoardsPaginated(int start, int count) throws ShineException {
		List<NoticeBoardEntity> boardEntities = boardDao.getAllBoards(start, count);
		List<NoticeBoard> boards = mapper.entityToDto(boardEntities);
		return boards;
	}

	public List<NoticeBoard> getBoardsByCreatorId(int creatorId) throws ShineException {
		List<NoticeBoardEntity> boardEntities = boardDao.getBoardsByCreator(creatorId, null);
		List<NoticeBoard> boards = mapper.entityToDto(boardEntities);
		return boards;
	}
	
	public NoticeBoard getNoticeBoard(int id, int userid) throws ShineException {

			NoticeBoardEntity board = boardDao.getNoticeBoard(id);
			return validatePopulateBoard(board, userid);
			
	}

	private NoticeBoard validatePopulateBoard(NoticeBoardEntity board, int userid) throws ShineException {

			if (board == null || BooleanUtils.isFalse(board.getActive())) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			if (board.getSuspended() != null) {
				throw new ShineException(GeneralError.SUSPENDED);
			}

			NoticeBoard boardDto = mapper.entityToDto(board);

			populateAds(boardDto, userid);

			populateNoticeDetails(boardDto, 0, ShineProperties.getDefaultNoticesCount());
			List<OtherPlayer> activeUsers = getActiveUsers(boardDto.getId());
			boardDto.setUserCount(activeUsers.size());

			return boardDto;

	}

	@Transactional
	public List<ShineObject> getNoticeBoard(int id, int userid, int noticeId, int replyId, boolean internaliseUsers)
			throws ShineException {

			NoticeBoardEntity board = null;
			if (id > 0) {
				board = boardDao.getNoticeBoard(id);
			} else if (noticeId > 0) {
				board = getBoardByNotice(noticeId);

			} else if (replyId > 0) {
				board = getBoardByReply(replyId);

			}

			if (board == null || (board.getActive() != null && !board.getActive())) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			if (board.getSuspended() != null) {
				throw new ShineException(GeneralError.SUSPENDED);
			}

			NoticeBoard boardDto = mapper.entityToDto(board);
			populateAds(boardDto, userid);

			populateNoticeDetails(boardDto, noticeId, ShineProperties.getDefaultNoticesCount());

			List<ShineObject> list = new ArrayList<ShineObject>();

			list.add(boardDto);
			try {
				List<OtherPlayer> activeUsers = getActiveUsers(boardDto.getId());
				boardDto.setUserCount(activeUsers.size());
				if (internaliseUsers) {
					List<ShineObject> ll = new ArrayList<ShineObject>();
					ll.addAll(activeUsers);
					boardDto.setListExtra(ll);
				} else {
					list.addAll(activeUsers);
				}
			} catch (Exception e) {
				log.warn("error populating users for board, continue", e);
				// do nothing, continue and return board
			}
			return list;

	}

	private void populateNoticeDetails(NoticeBoard boardDto, int noticeIdToInclude, int count) {
		/*
		 * all boards should have the default 3 posts. Those created via the app
		 * have them created at the time, but those created through the portal,
		 * or older ones need them adding, Therefore any board with less than 3
		 * posts needs the default creating.
		 */

		List<NoticeEntity> noticeEntities;
		if (noticeIdToInclude > 0) {
			noticeEntities = noticeDao.getNotices(boardDto.getId(), noticeIdToInclude, false);
		} else {
			noticeEntities = noticeDao.getNotices(boardDto.getId(), 0, count, NoticeCategory.ALL, false);
		}

		int noticeCount = noticeDao.getNoticesCount(boardDto.getId());

		if (noticeCount < 3) {

			List<NoticeEntity> defaults = defaultNoticeCreator.createDefaultNotices(boardDto.getId(),
					boardDto.getCreated(), Locale.UK);
			noticeEntities.addAll(defaults);
			noticeCount += defaults.size();
		}
		boardDto.setNoticeCount(noticeCount);
		List<Notice> noticeDtos = noticeMapper.entityToDto(noticeEntities);
		for (Notice notice : noticeDtos) {
			int repliesCount = replyDao.getRepliesCount(notice.getId());
			notice.setRepliesCount(repliesCount);

		}

		boardDto.setNotices(noticeDtos);

	}

	private NoticeBoardEntity getBoardByReply(int replyId) throws ShineException {

		ReplyEntity r = replyDao.getReply(replyId);
		if (r == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		return getBoardByNotice(r.getNoticeId());
	}

	private NoticeBoardEntity getBoardByNotice(int noticeId) throws ShineException {

		NoticeEntity n = noticeDao.getNotice(noticeId);
		if (n == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		NoticeBoardEntity nb = boardDao.getNoticeBoard(n.getBoardId());

		return nb;
	}


	private List<Advert> getAds(NoticeBoard board, int userId) {

		Integer[] userPrefCats = getPreferredCategoriesPerUser(userId);
		Integer[] excludedCats = getExcludedCats(board.getId());
		BoardLoc boardLocation = boardDao.getBoardLocation(board.getId());

		List<Advert> ads = adServer.getAds(boardLocation, excludedCats, userPrefCats);

		return ads;
	}


	private Integer[] getPreferredCategoriesPerUser(int userId) {

		if (userId < 1) {
			return null;
		}

		PlayerEntity u = playerDao.getPlayerById(userId);
		if (u == null) {
			return null;
		}
		Integer[] userPrefCats = WebAppUtils.getUserCategories(u.getOffers());
		return userPrefCats;
	}
	private Integer[] getExcludedCats(int boardId) {

		List<ExcludedCategory> adcats = boardDao.getExcludedAdCategories(boardId);
		Integer[] arr = new Integer[adcats.size()];

		for (int i = 0; i < adcats.size(); i++) {
			arr[i] = adcats.get(i).getId().getCatId();
		}
		return arr;
	}

	public void handleBoardReport(int reportId, boolean accepted, String judgement, int adminId)
			throws ShineException {

		ReportBoard rb;
		if (reportId > 0) {
			rb = boardDao.getBoardReport(reportId);
			if (rb == null) {
				throw new ShineException(GeneralError.NOT_FOUND);
			}
			rb.setAccepted(accepted);
			rb.setJudgement(judgement);
			rb.setAdminId(adminId);

		} else {
			throw new ShineException(GeneralError.PARAM_MISSING);
		}
		handleBoardReport(rb);
	}

	/**
	 * Suspend (or unsuspend) a board
	 * 
	 * @param boardId
	 * @param judgement
	 * @param suspend
	 *            true to suspend, false to reinstate
	 * @param adminId
	 * @throws ShineException
	 */
	public void suspendBoard(int boardId, String judgement, boolean suspend, int adminId)
			throws ShineException {

		ReportBoard rb;
		rb = new ReportBoard();
		rb.setBoardId(boardId);
		rb.setAdminId(adminId);
		rb.setAccepted(suspend);
		rb.setJudgement(judgement);
		handleBoardReport(rb);

	}

	private void handleBoardReport(ReportBoard rb)
			throws ShineException {
		NoticeBoardEntity board = boardDao.getNoticeBoard(rb.getBoardId());
		if (board == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}

		Date now = new Date();
		rb.setJudgementDay(now);
		if (rb.getAccepted() && board.getSuspended() == null) {
			board.setSuspended(now);
			boardDao.saveBoardAndReport(rb, board);
		} else if (!rb.getAccepted() && board.getSuspended() != null) {
			board.setSuspended(null);
			boardDao.saveBoardAndReport(rb, board);
		} else {
			boardDao.saveBoardReport(rb);
		}
	}

	private List<OtherPlayer> getActiveUsers(int boardId) {

		Date now = new Date();
		Date earliestToRetrieve = WebAppUtils.getDateInPast(ShineProperties.getRecentActivityDays());
		List<NoticeEntity> notices = noticeDao.getUnExpiredNotices(boardId, now, earliestToRetrieve);

		List<OtherPlayer> players = new ArrayList<OtherPlayer>();

		Set<PlayerEntity> set = new HashSet<PlayerEntity>();
		for (NoticeEntity notice : notices) {

			PlayerEntity playerEntity = notice.getPlayer();
			if (playerEntity != null && !set.contains(playerEntity)) {

				OtherPlayer p = getActiveUserForNotice(playerEntity, notice, SharedConstants.POSTED);
				players.add(p);
				set.add(playerEntity);
			}
			addRepliers(notice, set, players, SharedConstants.REPLIED);
		}

		List<NoticeEntity> expirednotices = noticeDao.getExpiredNotices(boardId, now, earliestToRetrieve);

		for (NoticeEntity notice : expirednotices) {
			PlayerEntity playerEntity = notice.getPlayer();
			if (playerEntity != null && !set.contains(playerEntity)) {
				OtherPlayer p = getActiveUserForNotice(playerEntity, notice, SharedConstants.EXPIRED);
				players.add(p);
				set.add(playerEntity);
			}
			addRepliers(notice, set, players, SharedConstants.EXPIRED);
		}

		return players;
	}

	private OtherPlayer getActiveUserForNotice(PlayerEntity playerEntity, NoticeEntity notice, String activityStatus) {
		OtherPlayer p = pmapper.entityToDto(playerEntity);
		p.setDateExtra(notice.getCreated());
		p.setIntExtra(notice.getId());
		p.setStringExtra(activityStatus);
		return p;

	}

	private void addRepliers(NoticeEntity notice, Set<PlayerEntity> set, List<OtherPlayer> players, String action) {
		List<ReplyEntity> replies = replyDao.getReplies(notice.getId(), true);
		for (ReplyEntity reply : replies) {
			PlayerEntity entity = reply.getPlayer();

			if (entity != null && !set.contains(entity)) {
				OtherPlayer p = pmapper.entityToDto(entity);
				p.setDateExtra(reply.getCreated());
				p.setIntExtra(reply.getNoticeId());
				if (avoidNull(notice.isSuspended())) {
					p.setStringExtra(SharedConstants.EXPIRED);
				} else {
					p.setStringExtra(action);
				}
				players.add(p);
				set.add(entity);
			}

		}

	}

	protected boolean avoidNull(Boolean b) {
		if (b == null) {
			return false;
		}
		return b.booleanValue();
	}

	public int getAllBoardsCount() {
		return boardDao.countBoards();
	}

	public List<NoticeBoard> getNearBoards(ShineLocation loc) {

		List<NoticeBoardEntity> boards = boardDao.getNearBoards(loc, 0, 0);

		List<NoticeBoard> boardDtos = mapper.entityToDto(boards);

		return boardDtos;
	}

}
