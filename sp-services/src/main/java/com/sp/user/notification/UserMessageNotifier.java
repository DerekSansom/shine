package com.sp.user.notification;

import static com.shine.SharedConstants.BOARD_POSTED_TO;
import static com.shine.SharedConstants.MAIL_RECEIVED;
import static com.shine.SharedConstants.POST_REPLIED_TO;

import java.util.LinkedList;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Uses a singleton and a very polite background thread, that keeps sleeping and
 * yielding to log tracking records to database.
 * 
 * 
 * 
 */
@Component
public class UserMessageNotifier {


	private Queue<UserNotification> messageQueue;
	private static Logger log = LoggerFactory.getLogger(UserMessageNotifier.class);
	protected boolean running = true;
	public static int SYSTEM_CREATOR_ID = -2;

	@Autowired
	private UserMessageNotifier(MessageHandler messageHandler) {
		messageQueue = new LinkedList<UserNotification>();
		Thread t = new Thread(new UserMailNotifier(messageHandler));
		t.setPriority(Thread.MIN_PRIORITY);
		t.start();

	}

	/**
	 * Notify a user that their post has been replied to.
	 * 
	 * @param userId
	 * @param messageKey
	 * @param subjectKey
	 * @param noticeId
	 */
	public void notifyUserPostReplied(int userId, int noticeId, String noticeTitle) {

		UserNotification un = new UserNotification(userId, POST_REPLIED_TO, "Post replied to",
				"Your notice '" + noticeTitle + "' has been replied to", noticeId);
		messageQueue.add(un);
	}

	public void notifyUserBoardPostedTo(int creatorId, int boardId, String boardName) {
		UserNotification un = new UserNotification(creatorId, BOARD_POSTED_TO, "Board posted to", "The board you created '" + boardName + "' has been posted to", boardId);
		messageQueue.add(un);

	}

	public void notifyMail(int mailId, int recipientId) {
		UserNotification un = new UserNotification(recipientId,
				MAIL_RECEIVED, "You've received a new message",
				null, null);
		messageQueue.add(un);

	}

	@Override
	protected void finalize() throws Throwable {
		running = false;
//		sess.flush();
//		sess.close();
		super.finalize();
	}

	public class UserNotification {
		int userId;
		String messageKey;
		String subjectKey;
		int type;
		Integer intExtra;

		UserNotification(int userId, int type, String messageKey, String subjectKey, Integer intExtra) {
			this.userId = userId;
			this.type = type;
			this.messageKey = messageKey;
			this.subjectKey = subjectKey;
			this.intExtra = intExtra;
		}

	}

	private class UserMailNotifier implements Runnable {

		private MessageHandler messageHandler;

		public UserMailNotifier(MessageHandler messageHandler) {
			this.messageHandler = messageHandler;
		}

		@Override
		public void run() {

			while (running) {
				if (messageQueue != null && messageQueue.isEmpty()) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						log.warn("LogDao interrupted exception", e);
					}
				} else {
					messageHandler.persistMessages(messageQueue);
				}

			}
		}
	}



}
