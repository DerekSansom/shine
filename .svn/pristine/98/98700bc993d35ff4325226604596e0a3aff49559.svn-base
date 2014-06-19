/*
 * Created on 11-Jan-2005
 *
 * 
 */
package shine.mailsender;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shine.app.utils.ShineProperties;

import com.sun.mail.smtp.SMTPMessage;

public class MsgUtils {

	private static Logger log = LoggerFactory.getLogger(MsgUtils.class);

	public static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.UK);
	public static DateFormat df2 = DateFormat.getDateInstance(DateFormat.LONG);

	private static String addressDelimiter = ";";

	private static Session session;
//    private static String defaultencoding="ISO-8859-1";
	private static String defaultencoding = null;

	public static Session getSession() {
		return getSession(defaultencoding);
	}

	public static Session getSession(String encoding) {
		if (session == null ||
				(encoding != null && !encoding.equals(defaultencoding))) {
			Properties props = System.getProperties();

			props.setProperty("mail.transport.protocol", "smtp");
			props.setProperty("mail.from", ShineProperties.getPwdReminderFromAddress());
			props.setProperty("mail.smtp.auth", "true");
			if (encoding != null) {
				props.setProperty("mail.mime.charset", encoding);
			} else {
				System.out.println(props.getProperty("mail.mime.charset"));
			}
			Authenticator auth = new MyAuth();
			session = Session.getInstance(props, auth);
			session.setDebug(false);
			defaultencoding = encoding;
		}
		return session;

	}

	/**
	 * @return
	 */
	static public SMTPMessage getSMTPMessage() throws MessagingException {

		String fromAddrStr = ShineProperties.getPwdReminderFromAddress();
		log.debug("Got from addr: " + fromAddrStr);
		InternetAddress fromAddr = new InternetAddress(fromAddrStr);
		InternetAddress[] fromArr = { fromAddr };
//	    this.fromArr=fromArr;
		SMTPMessage msg = new SMTPMessage(getSession());
		msg.setSentDate(new Date());
		msg.setEnvelopeFrom(ShineProperties.getPwdReminderFromAddress());
		msg.setFrom(fromAddr);
		msg.setReplyTo(fromArr);
//msg.setText("Original Text");
		//send a copy to ourselves
		//TODO: this should be gotten from properties
		if (true) {
			msg.addRecipient(Message.RecipientType.BCC, fromAddr);
		}
		return msg;
	}

	/**
	 * @param recipients
	 * @return
	 */
	public static String addressArrayToString(Address[] addresses) {

		String ret = "";
		if (addresses == null)
			return ret;

		if (addresses.length > 0) {
			ret += addresses[0].toString();
		}

		for (int i = 1; i < addresses.length; i++) {

			ret += addressDelimiter + addresses[i].toString();
		}

		return ret;
	}

	/**
	 * @param toAddresses
	 * @throws AddressException
	 */
	public static InternetAddress[] stringToAddresses(String toAddresses, boolean validate) throws AddressException {
		return stringToAddresses(toAddresses, validate, ";");
	}

	public static InternetAddress[] stringToAddresses(String toAddresses, boolean validate, String delimiter)
			throws AddressException {
		String[] addrStrings = toAddresses.split(delimiter);
		InternetAddress[] addr = new InternetAddress[addrStrings.length];

		for (int i = 0; i < addrStrings.length; i++) {
			try {

				if (addrStrings[i].trim().equals("")) {
					addr[i] = null;
				} else {
					addr[i] = new InternetAddress(addrStrings[i].trim());
					if (validate)
						addr[i].validate();
				}
			} catch (AddressException ae) {
				ae.printStackTrace();
				System.out.println(ae.getLocalizedMessage());
				System.out.println(addrStrings[i]);
			}
		}

		return addr;
	}

	/**
	 * @param from
	 * @return
	 */
	public static String avoidNullAddress(Address[] addrs) {

		String from = null;
		if (addrs != null &&
				addrs.length > 0) {
			InternetAddress fromadd = null;
			for (int i = 0; i < addrs.length; i++) {
				fromadd = (InternetAddress) addrs[i];
				if (fromadd != null)
					break;
			}

			if (fromadd != null) {
				from = fromadd.getPersonal();
				if (from == null) {
					from = fromadd.toString();
				}
			}
			if (from == null) {
				from = "  (none)  ";
			}
		}
		return from;
	}

	private static String formatDate(Date date) {
		String str = df2.format(date);
		if (str.charAt(0) == '0') {
			return str.substring(1);
		}
		return str;
	}

}
