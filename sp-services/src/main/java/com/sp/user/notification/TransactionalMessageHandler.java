package com.sp.user.notification;

import static com.shine.SharedConstants.BOARD_POSTED_TO;
import static com.shine.SharedConstants.C2DM_NEW_MSG;
import static com.shine.SharedConstants.C2DM_NEW_POST_ON_BOARD;
import static com.shine.SharedConstants.C2DM_POST_REPLIED;
import static com.shine.SharedConstants.MAIL_RECEIVED;
import static com.shine.SharedConstants.POST_REPLIED_TO;

import java.util.Date;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.dao.exception.ShineException;
import shine.mailsender.MailSender;

import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.NoticeEntity;
import com.sp.entity.PlayerEntity;
import com.sp.entity.msg.MailEntity;
import com.sp.mail.MailDao;
import com.sp.notice.NoticeDao;
import com.sp.player.PlayerDao;
import com.sp.user.notification.UserMessageNotifier.UserNotification;

@Component
public class TransactionalMessageHandler {

	private static Logger log = LoggerFactory.getLogger(UserMessageNotifier.class);

	private static final String POST_REPLIED_TEXT = "Greetings from StreetPin\n\n" +
			"You have a reply to your post: '%s'\n%s\n\n" +
			"Happy pinning\n\n" +
			"The StreetPin team";

	private static final String BOARD_POSTED_TEXT = "Greetings from StreetPin\n\n" +
			"You have a new post on your pin board : '%s'\n%s\n\n" +
			"Happy pinning\n\n" +
			"The StreetPin team";

	@Autowired
	private NoticeDao noticeDao;

	@Autowired
	private MailDao mailDao;

	@Autowired
	private PlayerDao playerDao;

	@Autowired
	private BoardDao boardDao;

	@Transactional
	void persistMessages(Queue<UserNotification> messageQueue) {
		UserNotification userMessage = messageQueue.poll();
		while (userMessage != null) {
			try {
				handleUserNotification(userMessage);
			} catch (Exception e) {
				log.warn("Failed to queue message", e);
			}
			Thread.yield();
			userMessage = messageQueue.poll();
		}

	}

	private void handleUserNotification(UserNotification un) throws ShineException {
//TODO: decide what to do here e.g. push notify, send internal message, send email

		PlayerEntity p = playerDao.getPlayerById(un.userId);

		if (p != null) {
			boolean sentPushNotification = false;
			switch (un.type) {
			case POST_REPLIED_TO:
				sentPushNotification = sendUserPushNotification(p, C2DM_POST_REPLIED.charAt(0), un.intExtra);
				sendMessage(p, un, sentPushNotification);
				sendPostRepliedToEmail(p, un);
				break;
			case BOARD_POSTED_TO:
				sentPushNotification = sendUserPushNotification(p,
						C2DM_NEW_POST_ON_BOARD.charAt(0), un.intExtra);
				sendMessage(p, un, sentPushNotification);
				sendBoardPostedToEmail(p, un);
				break;
			case MAIL_RECEIVED:
				sentPushNotification = sendUserPushNotification(p,
						C2DM_NEW_MSG.charAt(0), null);

			default:
				break;
			}

		}
	}

	private void sendBoardPostedToEmail(PlayerEntity p, UserNotification un) throws ShineException {

		NoticeBoardEntity board = boardDao.getNoticeBoard(un.intExtra);

		String message = String.format(BOARD_POSTED_TEXT, board.getName(), createBoardUrl("boardid", board.getId()));

		String subject = String.format(getLanguageString("en", "Streetpin board %s posted to"), board.getName());
		sendEmail(p.getForename(), p.getEmail(), subject, message);

	}

	private void sendPostRepliedToEmail(PlayerEntity p, UserNotification un) throws ShineException {

		NoticeEntity notice = noticeDao.getNotice(un.intExtra);
		String message = String.format(POST_REPLIED_TEXT, notice.getTitle(), createBoardUrl("id", notice.getBoardId()));
		String subject = String.format(getLanguageString("en", "Streetpin post %s replied to"), notice.getTitle());
		sendEmail(p.getForename(), p.getEmail(), subject, message);
	}

	private Object createBoardUrl(String idparam, int id) {

		String url = "http://www.streetpin.com/board?" + idparam + "=" + id;

		return url;
	}

	private void sendEmail(String name, String emailAddress, String subject, String content) throws ShineException {
		MailSender.sendMessage(name, emailAddress, content, subject);

	}

	private void sendMessage(PlayerEntity p, UserNotification un, boolean sentPushNotification) {

		String message = getLanguageString("en", un.messageKey);
		String subject = getLanguageString("en", un.subjectKey);
		sendUserInternalMessage(p, subject, message, sentPushNotification, un.type, un.intExtra);

	}

	private boolean sendUserPushNotification(PlayerEntity p, char message, Integer intX) {
		// TODO: when using android we can try this again
		// if (p.getPkey() != null) {
		// try {
		// return GoogleC2DMNotifier.sendMessage3(p.getPkey().trim(), "" +
		// message, intX);
		// } catch (ShineException e) {
		// log.error("", e);
		// } catch (IOException e) {
		// log.error("", e);
		// }
		// }
		return false;
	}
	

	private void sendUserInternalMessage(PlayerEntity p, String subject, String message, boolean sentPushNotification, int systype, int intx) {

		MailEntity notificationMessage = new MailEntity();
		notificationMessage.setRecipientId(p.getId());
		notificationMessage.setSenderId(1);
		notificationMessage.setSubject(subject);
		notificationMessage.setMessage(message);
		notificationMessage.setSystemType(systype);
		notificationMessage.setIntExtra(intx);
		if (sentPushNotification) {
			Date now = new Date();
			notificationMessage.setDelivered(now);
			notificationMessage.setRead(true);
		}

		mailDao.createMail(notificationMessage);

	}

	private static String getLanguageString(String lang, String key) {
		// TODO Auto-generated method stub
		return key;
	}

}
