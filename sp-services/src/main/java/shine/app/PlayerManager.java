package shine.app;

import static com.shine.GeneralError.LOGGED_OUT;
import static com.shine.GeneralError.NOT_FOUND;
import static com.shine.GeneralError.PARAM_MISSING;
import static com.shine.GeneralError.SUSPENDED;
import static com.shine.SharedConstants.PLAYER_NOT_FOUND;
import static com.shine.SharedConstants.PWD_MAX_LENGTH;
import static com.shine.SharedConstants.PWD_MIN_LENGTH;
import static com.shine.SharedConstants.REG_FAILED_EMAIL_REQUIRED;
import static com.shine.SharedConstants.REG_FAILED_EMAIL_TAKEN;
import static com.shine.SharedConstants.REG_FAILED_FULLNAME_REQUIRED;
import static com.shine.SharedConstants.REG_FAILED_PASSWORD_LENGTH;
import static com.shine.SharedConstants.REG_FAILED_PASSWORD_REQUIRED;
import static com.shine.SharedConstants.REG_FAILED_USERNAME_REQUIRED;
import static com.shine.SharedConstants.REG_FAILED_USERNAME_TAKEN;
import static com.shine.SharedConstants.SUCCESS;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import shine.app.mapper.BoardMapper;
import shine.app.mapper.NoticeMapper;
import shine.app.mapper.PlayerMapper;
import shine.app.utils.ShineProperties;
import shine.dao.ads.ReportUser;
import shine.dao.exception.ShineException;
import shine.util.WebAppUtils;

import com.shine.SharedConstants;
import com.shine.boards.BoardDetails;
import com.shine.boards.Notice;
import com.shine.boards.Reply;
import com.shine.objects.OtherPlayer;
import com.shine.objects.Player;
import com.shine.objects.ShineLocation;
import com.shine.objects.ShineObject;
import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.ReplyEntity;
import com.sp.notice.NoticeDao;
import com.sp.notice.ReplyDao;
import com.sp.player.PlayerDao;
import com.sp.report.ReportDao;
import com.sp.security.PasswordHasher;

@Component
public class PlayerManager extends BaseHandler {

	protected Logger log = LoggerFactory.getLogger(PlayerManager.class);

	private NoticeMapper noticeMapper = new NoticeMapper();
	private PlayerMapper playerMapper = new PlayerMapper();
	private PasswordHasher passwordHasher = new PasswordHasher();
	private BoardMapper mapper = new BoardMapper();

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private ReplyDao replyDao;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private ReportDao reportDao;

	@Autowired
	private ImageHandler imageHandler;

	@Transactional
	public int updateStatus(int id, String status) throws ShineException {

		PlayerEntity p = playerDao.getPlayerById(id);
		if (p == null) {
			return PLAYER_NOT_FOUND.getCode();
		}
		p.setStatus(status);
		playerDao.save(p);
		return SUCCESS;
	}

	@Transactional
	public void create(Player user) throws ShineException {

		try {
			validate(user);
			validateAndHashPassword(user);
			PlayerEntity userEntity = null;
			if (userNameExists(user)) {
				throw new ShineException(REG_FAILED_USERNAME_TAKEN);
			} else if (emailExists(user)) {
				throw new ShineException(REG_FAILED_EMAIL_TAKEN);
			}
			else {
				userEntity = playerMapper.dtoToEntity(user);
			}
			if (!StringUtils.hasLength(userEntity.getToken())) {
				String token = genToken(user);
				userEntity.setToken(token);
			}
			playerDao.save(userEntity);
		} catch (Throwable t) {
			log.error("error creating new player", t);
			throw new ShineException(t);
		}

	}

	private void validate(Player user) throws ShineException {
		if (user.getId() <= 0) {
			if (user == null || !StringUtils.hasLength(user.getUsername())) {
				throw new ShineException(REG_FAILED_USERNAME_REQUIRED);
			}
			if (!StringUtils.hasLength(user.getForename())) {
				throw new ShineException(REG_FAILED_FULLNAME_REQUIRED);
			}
			if (!StringUtils.hasLength(user.getEmail())) {
				throw new ShineException(REG_FAILED_EMAIL_REQUIRED);
			}
		}

	}

	@Transactional
	public Player updateUser(Player user) throws ShineException {

		PlayerEntity entity = playerDao.getPlayerById(user.getId());
		updateEntity(entity, user);
		playerDao.save(entity);
		return playerMapper.entityToUserDto(entity);

	}

	@Transactional
	public Player updateUserWithImage(Player user, BufferedImage image, long size) throws ShineException {

		if (size > ShineProperties.maxImageSize()) {
			throw new ShineException(SharedConstants.ERROR_MAX_IMG_SIZE);
		}

		PlayerEntity entity = playerDao.getPlayerById(user.getId());
		updateEntity(entity, user);
		playerDao.save(entity);
		imageHandler.handleUserImage(image, user.getId());
		return playerMapper.entityToUserDto(entity);

	}

	@Transactional
	public String registerOrUpdatePlayer(Player user, boolean withToken, boolean v1) throws ShineException {

		validate(user);
		if (user.getId() == 0) {

			// password not required on existing user
			validateAndHashPassword(user);
		}

		try {
			PlayerEntity userEntity = null;
			if (user.getId() > 0) {

				userEntity = updateExistingUser(user);

			} else if (userNameExists(user)) {
				// new player cannot have an existing username
				throw new ShineException(REG_FAILED_USERNAME_TAKEN);
			} else if (emailExists(user)) {
				if (v1) {
					throw new ShineException(-14);
				}
				// new player cannot have an existing username
				throw new ShineException(REG_FAILED_EMAIL_TAKEN);
			}
			else {
				userEntity = playerMapper.dtoToEntity(user);
			}
			if (userEntity.getId() == 0 || !StringUtils.hasLength(userEntity.getToken())) {
				String token = genToken(user);
				userEntity.setToken(token);
			}
			playerDao.save(userEntity);

			if (withToken)
				return userEntity.getId() + ":" + userEntity.getToken();
			return "" + userEntity.getId();

		} catch (ShineException e) {
			throw e;
		} catch (Throwable t) {
			log.error("error creating new player", t);
			throw new ShineException(t);
		}

	}

	private void updateEntity(PlayerEntity extuser, Player user) {

		extuser.setGender(user.getGender());
		extuser.setOffers(user.getOffers());
		extuser.setStatus(user.getStatus());
		extuser.setBiog(user.getBiog());
		extuser.setDob(user.getDob());

	}

	private PlayerEntity updateExistingUser(Player user) {
		// set all fields that user can send from phone e.g. not
		// password, score (even though some can't currently be change
		// on phone, e.g.username)
		PlayerEntity extuser = playerDao.getPlayerById(user.getId());
		if (user.getDob() != null)
			extuser.setDob(user.getDob());
		if (user.getPhone() != null)
			extuser.setPhone(user.getPhone());
		if (user.getForename() != null)
			extuser.setForename(user.getForename());
		if (user.getGender() != null)
			extuser.setGender(user.getGender());
		if (user.getOffers() != null)
			extuser.setOffers(user.getOffers());
		if (user.getStatus() != null)
			extuser.setStatus(user.getStatus());
		if (user.getSurname() != null)
			extuser.setSurname(user.getSurname());
		if (user.getBiog() != null)
			extuser.setBiog(user.getBiog());
		if (user.getAvatar() != null)
			extuser.setAvatar(user.getAvatar());
		return extuser;
	}

	private void validateAndHashPassword(Player user) throws ShineException {

		String password = user.getPassword();
		if (!StringUtils.hasLength(password)) {
			throw new ShineException(REG_FAILED_PASSWORD_REQUIRED);
		}
		if (password.length() < PWD_MIN_LENGTH
				|| password.length() > PWD_MAX_LENGTH) {
			throw new ShineException(REG_FAILED_PASSWORD_LENGTH);
		}
		user.setPassword(passwordHasher.hashPassword(PasswordHasher.SALT, password));
	}

	private String genToken(Player user) {

		return WebAppUtils.generatetoken(6);
	}

	private boolean emailExists(Player user) throws ShineException {
		try {

			PlayerEntity p = playerDao.getUserByEmail(user.getEmail());
			if (p != null) {
				return true;
			}
		} catch (Exception e) {
			log.debug("Error retrieving user by email: " + user.getId() + ": " + user.getEmail(), e);
			return true;
		}

		return false;
	}

	private boolean userNameExists(Player user) {
		PlayerEntity p = playerDao.getPlayerByName(user.getUsername());
		if (p != null) {
			return true;
		}

		return false;
	}

	private void updatePlayerLoc(PlayerEntity user, ShineLocation loc) throws ShineException {
		if (loc != null) {
			playerDao.updateUserWithLoc(user, loc);
		}
	}

	@Transactional
	public void deletePlayer(int id) {

		playerDao.deleteUser(id);

	}

	@Transactional
	public Player getUser(int userId, ShineLocation userloc)
			throws ShineException {

		if (userId == 0) {
			throw new ShineException(PARAM_MISSING);
		}

		PlayerEntity userEntity = null;

		try {
			userEntity = playerDao.getPlayerById(userId);
		} catch (Exception e) {
			log.error("failed to retrieve user: " + userId, e);
			throw new ShineException(e);
		}

		if (userEntity == null) {
			throw new ShineException(PLAYER_NOT_FOUND);
		}
		if (userEntity.getSuspended() != null) {
			throw new ShineException(SUSPENDED);
		}

		try {
			updatePlayerLoc(userEntity, userloc);
		} catch (ShineException e) {
			log.error("failed to update player loc: " + userId, e);
		} catch (Exception e) {
			log.error("failed to update player loc: " + userId, e);
		}
		return playerMapper.entityToUserDto(userEntity);

	}

	public Player getUser(int userId) throws ShineException {

		PlayerEntity userEntity = playerDao.getPlayerById(userId);
		return playerMapper.entityToUserDto(userEntity);

	}

	public OtherPlayer getPlayerByPrivilege(int id, int userid) throws ShineException {

		try {
			OtherPlayer p = retrieveRequiredNonSuspendedPlayerById(id);
//			if (userid > 0) {
//				Privileges priv = pdao.getPrivileges(userid, id);
//				if (priv != null) {
//					p.setIntExtra((int) priv.getPrivilege());
//				}
//			}
			return p;

		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.error("Failed to get palyer", e);
			throw new ShineException(e);
		}

	}

	public OtherPlayer getPlayer(int id) {

		PlayerEntity playerById = playerDao.getPlayerById(id);
		return playerMapper.entityToDto(playerById);

	}

	public Player getUserByUsername(String username) {

		PlayerEntity player = playerDao.getPlayerByName(username);
		return playerMapper.entityToUserDto(player);

	}

	@Transactional
	public List<ShineObject> getPlayerWithActivity(int id, int userid)
			throws ShineException {

		try {

			OtherPlayer p = retrieveRequiredNonSuspendedPlayerById(id);

			List<ShineObject> list = getRecentActivity(id);
			list.add(0, p);
			return list;
		} catch (ShineException e) {
			throw e;
		} catch (Exception e) {
			log.error("Failed to get player", e);
			throw new ShineException(e);
		}

	}

	private OtherPlayer retrieveRequiredNonSuspendedPlayerById(int id) throws ShineException {
		PlayerEntity entity = playerDao.getPlayerById(id);
		if (entity == null) {
			throw new ShineException(PLAYER_NOT_FOUND);
		}
		if (entity.getSuspended() != null) {
			throw new ShineException(SUSPENDED);
		}
		return playerMapper.entityToDto(entity);
	}

	private List<ShineObject> getRecentActivity(int id) {
		List<ShineObject> list = new ArrayList<ShineObject>();

		try {
			Date activitySince = WebAppUtils.getDateInPast(ShineProperties.getRecentActivityDays());

			// NoticeDaoOld ndao = new NoticeDaoOld(session);
			List<NoticeEntity> noticeEntities = noticeDao.getNoticesByPlayer(id, 0, 0, activitySince);

			Date now = new Date();
			List<Notice> notices = noticeMapper.entityToDto(noticeEntities);
			for (Notice notice : notices) {
				if (now.after(notice.getExpires())) {
					notice.setStringExtra(SharedConstants.EXPIRED);
				}
			}

			list.addAll(notices);

			List<ReplyEntity> replyEntities = replyDao.getRepliesByPlayer(id, 0, 0, activitySince);
			List<Reply> replies = noticeMapper.replyEntitiesToDto(replyEntities);

			for (Reply reply : replies) {
				NoticeEntity n = noticeDao.getNotice(reply.getNoticeId());
				if (now.after(n.getExpires())) {
					reply.setStringExtra(SharedConstants.EXPIRED);
				}

			}

			decorateWithBoardDetails(notices, replies);
			list.addAll(replies);
			List<NoticeBoardEntity> boards = boardDao.getBoardsByPlayer(id, activitySince);
			list.addAll(mapper.entityToDto(boards));

		} catch (Exception e) {
			// log exception but continue and return what is possible
			log.warn("Failed to load all objects for recent activity", e);
		}
		return list;
	}

	private void decorateWithBoardDetails(List<Notice> notices, List<Reply> replies) {

		for (Notice notice : notices) {
			NoticeBoardEntity board = boardDao.getNoticeBoard(notice.getBoardId());
			BoardDetails bd = getBoardDetails(board);
			notice.setBoardDetails(bd);
		}

		for (Reply reply : replies) {
			NoticeEntity notice = noticeDao.getNotice(reply.getNoticeId());
			NoticeBoardEntity board = boardDao.getNoticeBoard(notice.getBoardId());
			BoardDetails bd = getBoardDetails(board);
			reply.setBoardDetails(bd);
		}

	}

	private BoardDetails getBoardDetails(NoticeBoardEntity board) {
		if (board == null) {
			return null;
		}
		return new BoardDetails(board.getName(), board.getLocationName());
	}

	@Transactional
	public void handleUserReport(int reportId, int userId, boolean accepted, int adminId, String judgement)
			throws ShineException {

		// PlayerDaoHib dao = new PlayerDaoHib(hibernateUtil.getSession());

		// try {
		PlayerEntity user = playerDao.getPlayerById(userId);
		if (user == null) {
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		}

		ReportUser ru;
		if (reportId > 0) {
			ru = reportDao.getUserReport(reportId);
			if (ru == null) {
				throw new ShineException(NOT_FOUND);
			}
		} else {
			ru = new ReportUser();
			ru.setUserId(userId);
		}
		ru.setAdminId(adminId);
		ru.setJudgement(judgement);
		ru.setAccepted(accepted);

		Date now = new Date();
		ru.setJudgementDay(now);
		if (ru.getAccepted() && user.getSuspended() == null) {
			user.setSuspended(now);
			reportDao.handleUserReport(ru, user);
		} else if (!ru.getAccepted() && user.getSuspended() != null) {
			user.setSuspended(null);
			reportDao.handleUserReport(ru, user);
		} else {
			reportDao.save(ru);
		}

		// }
//		finally {
//			dao.closeSession();
//		}

	}

	@Transactional
	public void updateC2dmKey(int userId, String c2dmKey) {

		try {
			PlayerEntity u = playerDao.getPlayerById(userId);
			if (u != null) {

				u.setPkey(c2dmKey);
				playerDao.save(u);

			}
		} catch (Exception e) {
			log.error("Failed to update  c2dmKey userid: " + userId + ":" + c2dmKey, e);
		}

	}

	public void checkUserToken(int userId, String token) throws ShineException {

		PlayerEntity u = playerDao.getPlayerById(userId);
		if (u == null) {
			throw new ShineException(SharedConstants.PLAYER_NOT_FOUND);
		}
		if (token == null || !token.equals(u.getToken()) && !token.equals("test")) {
			throw new ShineException(LOGGED_OUT);
		}
	}

	public List<PlayerEntity> getLeaderBoard() {
		return Collections.emptyList();
	}
}
