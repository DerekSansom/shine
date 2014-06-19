package shine.mailsender;

import java.io.UnsupportedEncodingException;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.sun.mail.smtp.SMTPMessage;

public class MailSender {

	private static Logger log = LoggerFactory.getLogger(MailSender.class);

	public static void sendMessage(String toName, String toAddress, String text, String subject)
			throws ShineException {

		if (ShineProperties.getBoolean("dont.send.email")) {
			return;
		}

		try {
			if (log.isInfoEnabled())
				log.info("Sending email to " + toName + ":" + toAddress + ":" + subject);
			SMTPMessage msg = convertMail(toName, toAddress, text, subject);
			sendMessage(msg);
		} catch (MessagingException e) {
			log.error("sending mail failed to: " + toAddress, e);
			log.error(ShineProperties.getSMTPHost() + ":" + ShineProperties.getSMTPUser() + ":"
					+ ShineProperties.getSMTPPassword());

			throw new ShineException(e);
		}

	}

	private static SMTPMessage convertMail(String toName, String toAddress, String text, String subject)
			throws MessagingException, ShineException {

		SMTPMessage smtpMsg = MsgUtils.getSMTPMessage();
		smtpMsg.setSubject(subject);

		smtpMsg.setEnvelopeFrom(ShineProperties.getSMTPUser());
		smtpMsg.setFrom(getFrom());

		try {
			smtpMsg.setRecipients(Message.RecipientType.TO, getEmailAddress(toName, toAddress));
		} catch (UnsupportedEncodingException e) {

			throw new ShineException(e);

		}
		smtpMsg.setText(text);
		return smtpMsg;
	}

	private static Address getFrom() throws ShineException {
		try {
			InternetAddress fromAddr = new InternetAddress(ShineProperties.getPwdReminderFromAddress(),
					ShineProperties.getPwdReminderFromName());
			return fromAddr;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new ShineException(0, e);
		}
	}

	private static Address[] getEmailAddress(String name, String address) throws AddressException,
			UnsupportedEncodingException {

		InternetAddress fromAddr = new InternetAddress(address, name);
		InternetAddress[] addr = { fromAddr };

		return addr;
	}

	private static void sendMessage(SMTPMessage msg) throws MessagingException {
		Transport t = MsgUtils.getSession().getTransport();
		t.connect(ShineProperties.getSMTPHost(), ShineProperties.getSMTPUser(), ShineProperties.getSMTPPassword());
//		t.connect("auth.smtp.1and1.co.uk", "m35040780-4", "c1ccia");
//		t.connect("auth.smtp.1and1.co.uk", "shine@postacity.co.uk", "t1mbuick");

		t.sendMessage(msg, msg.getAllRecipients());
		t.close();
	}

}
