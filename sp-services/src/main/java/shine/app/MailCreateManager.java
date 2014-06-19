package shine.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import shine.dao.exception.ShineException;

import com.shine.GeneralError;
import com.sp.entity.msg.MailEntity;
import com.sp.mail.MailDao;
import com.sp.user.notification.UserMessageNotifier;

@Component
public class MailCreateManager extends BaseHandler {

	@Autowired
	private MailDao mailDao;

	@Autowired
	private UserMessageNotifier userMessageNotifier;

	@Transactional
	public int createMail(String subject, int recipientId, int senderId, String text, int inReplyTo, String message)
			throws ShineException {
		MailEntity mail = null;
		//message is new model of simpler message threads
		if (message != null) {
			mail = createMessage(message, recipientId, senderId);

		} else {

			mail = createMail(subject, recipientId, senderId, text, inReplyTo);
		}

		try {
			mailDao.createMail(mail);
			if (mail.getId() > 0) {
				notifyRecipient(mail.getId(), recipientId);
			}

			return mail.getId();
		} catch (Exception e) {
			log.error("failed to create Mail" + mail, e);
			throw new ShineException(GeneralError.SYSTEM_ERROR, e.getMessage());
		}
	}

	private MailEntity createMail(String subject, int recipientId, int senderId, String text, int inReplyTo) {

		// new msgthread will get rid of subject, but for now need to hack.
		if (subject != null && subject.length() > 140 && text == null) {
			text = subject.substring(140);
			subject = subject.substring(0, 140);
		}

		MailEntity mail = new MailEntity();
		mail.setSubject(subject);
		mail.setSenderId(senderId);
		mail.setMessage(text);
		mail.setRecipientId(recipientId);
		if (inReplyTo > 0) {
			mail.setRepliedMailId(inReplyTo);
		}
		return mail;
	}

	private MailEntity createMessage(String message, int recipientId, int senderId) {

		String subject;

		String text = null;
		if (message != null && message.length() > 140) {
			text = message.substring(140);
			subject = message.substring(0, 140);
		} else {
			subject = message;
		}

		MailEntity mail = new MailEntity();
		mail.setSubject(subject);
		mail.setSenderId(senderId);
		mail.setMessage(text);
		mail.setRecipientId(recipientId);
		return mail;
	}

	private void notifyRecipient(int mailId, int recipientId) {
		userMessageNotifier.notifyMail(mailId, recipientId);
	}

}
