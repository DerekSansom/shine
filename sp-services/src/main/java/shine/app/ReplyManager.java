package shine.app;

import java.util.Date;
import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.app.mapper.NoticeMapper;
import shine.dao.exception.ShineException;

import com.shine.SharedConstants;
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
public class ReplyManager extends BaseHandler {

	private NoticeMapper noticeMapper = new NoticeMapper();

	@Autowired
	private ReplyDao replyDao;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private UserMessageNotifier userMessageNotifier;

	@Transactional
	public int createReply(Reply r) throws ShineException {

		try {

			PlayerEntity p = playerDao.getPlayerById(r.getCreatorId());
			if (p == null) {
				throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
			}
			ReplyEntity entity = noticeMapper.dtoToEntity(r);
			entity.setPlayer(p);
			notifyPosterAndUpdateBoard(r.getNoticeId());
			replyDao.createReply(entity);
			r.setId(entity.getId());
			r.setCreated(new Date());
			r.setAuthor(p.getUsername());
			return entity.getId();

		} catch (ShineException e) {
			throw e;
		} catch (ConstraintViolationException e) {
			log.warn("Failed to create reply", e);
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		} catch (Exception e) {
			log.warn("Failed to create reply", e);
			throw new ShineException(e);
		}

	}

	private void notifyPosterAndUpdateBoard(int noticeId) {
		NoticeEntity notice = noticeDao.getNotice(noticeId);
		NoticeBoardEntity boardEntity = boardDao.getNoticeBoard(notice.getBoardId());
		boardEntity.setLastUpdate(new Date());
		if (notice != null && notice.getPlayer() != null) {
			userMessageNotifier.notifyUserPostReplied(notice.getPlayer().getId(), noticeId, notice.getTitle());

		}

	}

	public List<Reply> getReplies(int noticeid, int start, int repliesCount, boolean b) {

		List<ReplyEntity> entities = replyDao.getReplies(noticeid, start, repliesCount, false);
		return noticeMapper.replyEntitiesToDto(entities);

	}

}
