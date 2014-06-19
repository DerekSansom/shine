package com.shine.xml;

import com.shine.mail.Mail;

public class MailXml extends XmlObject {

	private Mail mail;

	public MailXml(Mail shineObject) {
		super(shineObject);
		this.mail = shineObject;
	}

	@Override
	public String toXml() {
		return toXml(true);
	}

	private String toXml(boolean includeRecipientActions) {
		StringBuilder xml = new StringBuilder("<mail")
				.append(formatIntAttribute("id", mail.getId()))
				.append(formatIntAttribute("senderid", mail.getSenderId()))
				.append(formatIntAttribute("replytoid", mail.getRepliedMailId()))
				.append(formatIntAttribute("recipientid", mail.getRecipientId()))
				.append(formatAttribute("created", mail.getCreated()))
				.append(formatAttribute("new", mail.isNewMail()))
				.append(formatAttribute("reported", mail.isReported()));
		if (includeRecipientActions) {
			xml.append(formatAttribute("replied", mail.isReplied()))
					.append(formatAttribute("read", mail.isRead()))
					.append(formatAttribute("sender", mail.getSender()));
		} else {
			xml.append(formatAttribute("recipient", mail.getStringExtra()));
		}

		if (mail.getSystemType() != 0) {
			xml.append(formatIntAttribute("systemType", mail.getSystemType()));
		}
		if (mail.getIntExtra() != 0) {
			xml.append(formatIntAttribute("intx", mail.getIntExtra()));
		}
		xml.append(">");
		xml.append("<subject>").append(escape(mail.getSubject())).append("</subject>")
				.append("<message>").append(escape(mail.getMessage())).append("</message>")
				.append("</mail>");
		return xml.toString();

	}

	/**
	 * creates the xml a srepresented in a snet box, i.e. hides recipient
	 * actions
	 * 
	 * @return
	 */
	public Object toSentXml() {
		return toXml(false);
	}


}
