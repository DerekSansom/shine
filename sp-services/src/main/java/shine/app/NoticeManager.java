package shine.app;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.NoticeMapper;
import shine.dao.exception.NotFoundException;
import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.shine.SharedConstants;
import com.shine.boards.Notice;
import com.shine.boards.Reply;
import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.ReplyEntity;
import com.sp.notice.NoticeDao;
import com.sp.notice.ReplyDao;
import com.sp.player.PlayerDao;
import com.sp.user.notification.UserMessageNotifier;

@Component
public class NoticeManager extends BaseHandler {

	@Autowired
	private NoticeMapper mapper;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private ReplyDao replyDao;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private ImageHandler imageHandler;

	@Autowired
	private UserMessageNotifier userMessageNotifier;

	// @Transactional
	// public int createNoticeWithImage(Notice n, ByteArrayOutputStream
	// imagebytes) throws ShineException {
	// return createNoticeWithImage(n, imagebytes.toByteArray());
	// }
	//
	// @Transactional
	// public int createNoticeWithImage(Notice notice, byte[] image) throws
	// ShineException {
	//
	//
	// PlayerEntity p = confirmDataIntegrity(notice.getCreatorId());
	// NoticeBoardEntity nb = confirmBoardIntegrity(notice.getBoardId());
	//
	// int noticeId = imageHandler.createNoticeWithImage(image, notice, p);
	//
	// if (noticeId > 0) {
	// nb.setLastUpdate(new Date());
	// notifyBoardCreator(notice.getBoardId());
	// }
	// return noticeId;
	// }
	//
	@Transactional
	public int createNoticeWithImage(Notice notice, BufferedImage bufferedImage) throws ShineException {
		PlayerEntity p = confirmDataIntegrity(notice.getCreatorId());
		NoticeBoardEntity nb = confirmBoardIntegrity(notice.getBoardId());

		int noticeId = imageHandler.createNoticeWithImage(bufferedImage, notice, p);

		if (noticeId > 0) {
			nb.setLastUpdate(new Date());
			notifyBoardCreator(notice.getBoardId());
		}
		return noticeId;
	}

	@Transactional
	public int createNotice(Notice n) throws ShineException {

		PlayerEntity p = confirmDataIntegrity(n.getCreatorId());
		NoticeBoardEntity nb = confirmBoardIntegrity(n.getBoardId());
		NoticeEntity entity = mapper.dtoToEntity(n);
		entity.setPlayer(p);
		noticeDao.createNotice(entity);
		nb.setLastUpdate(entity.getCreated());
		notifyBoardCreator(n.getBoardId());
		return entity.getId();

	}

	private PlayerEntity confirmDataIntegrity(int creatorId) throws ShineException {

		PlayerEntity p = playerDao.getPlayerById(creatorId);

		if (p == null) {
			throw new NotFoundException(SharedConstants.PLAYER_NOT_FOUND);
		}
		return p;
	}

	private NoticeBoardEntity confirmBoardIntegrity(int boardId) throws ShineException {

		NoticeBoardEntity nb = boardDao.getNoticeBoard(boardId);
		if (nb == null) {
			throw new NotFoundException(GeneralError.NOT_FOUND);
		}

		return nb;
	}

	private void notifyBoardCreator(int boardId) {

		NoticeBoardEntity board = boardDao.getNoticeBoard(boardId);
		if (boardHasCreatorId(board)) {
			userMessageNotifier.notifyUserBoardPostedTo(board.getPlayer().getId(), boardId, board.getName());
		}
	}

	private boolean boardHasCreatorId(NoticeBoardEntity board) {
		return board != null && board.getPlayer() != null;

	}

	@Transactional
	public List<Notice> getNoticesSince(int boardid, int secondsSince) {

		List<NoticeEntity> entities = noticeDao.getNoticesSince(boardid, secondsSince);
			return mapper.entityToDto(entities);
	}

	@Transactional
	public List<Notice> getNotices(int boardid, int start, int maxCount, int catid, boolean includeSuspended) {

		List<NoticeEntity> noticeEntities = noticeDao.getNotices(boardid, start, maxCount, catid, includeSuspended);

		List<Notice> notices = mapper.entityToDto(noticeEntities);
		for (Notice notice : notices) {
			int repliesCount = replyDao.getRepliesCount(notice.getId());
			notice.setRepliesCount(repliesCount);

		}

		return notices;

	}

	@Transactional
	public Notice getNoticeWithReplies(int postId) throws ShineException {

		Notice notice = getNotice(postId);
		List<ReplyEntity> replies = replyDao.getReplies(postId, false);
		List<Reply> dtos = mapper.replyEntitiesToDto(replies);
		notice.setReplies(dtos);
		int repliesCount = replyDao.getRepliesCount(notice.getId());
		notice.setRepliesCount(repliesCount);
		return notice;

	}

	@Transactional
	public NoticeInList getNoticeWithPreviousAndNext(int postId) throws ShineException {

		Notice notice = getNotice(postId);
		int[] scrollIds = getNextNoticeIds(notice);

		return new NoticeInList(notice, scrollIds[0], scrollIds[1]);

	}

	private int[] getNextNoticeIds(Notice notice) {

		List<NoticeEntity> notices = noticeDao.getNotices(notice.getBoardId(), notice.getId(), false);
		int[] ret = new int[2];
		if (!notices.contains(notice)) {
			return ret;
		}
		int pos = notices.indexOf(notice);
		if (pos > 0) {

			NoticeEntity prevNotice = notices.get(pos - 1);
			ret[0] = prevNotice.getId();
		}
		if (pos < notices.size() - 1) {

			NoticeEntity nextNotice = notices.get(pos + 1);
			ret[1] = nextNotice.getId();

		}

		return ret;
	}

	@Transactional
	public Notice getNotice(int postId) throws ShineException {

		NoticeEntity noticeEntity = noticeDao.getNotice(postId);
		if (noticeEntity == null) {
			throw new ShineException(GeneralError.NOT_FOUND);
		}
		Notice notice = mapper.entityToDto(noticeEntity);

		return notice;

	}

	@Transactional
	public List<Notice> getNoticesSearch(int boardid, String searchString, int mode, int maxResults) {

		List<NoticeEntity> entities = noticeDao.getNoticesSearch(boardid, searchString, mode, maxResults);

			return mapper.entityToDto(entities);

	}

	public void testMaxSize(InputStream stream) throws IOException, ShineException {
		// imageHandler.testMaxSize(stream);
	}

}