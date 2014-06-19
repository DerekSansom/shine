package com.shine.mail;

import java.util.Date;

import com.shine.UserActivity;
import com.shine.objects.ShineObject;

public class Mail extends ShineObject implements UserActivity {

	private static final long serialVersionUID = 1L;
	private int senderId, recipientId, systemType;
	private Integer repliedMailId;
	private String subject, message, sender;
	private Date created, delivered;
	private boolean reported, suspended, read, replied, newMail;

	public boolean isNewMail() {
		return newMail;
	}

	public void setNewMail(boolean newMail) {
		this.newMail = newMail;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Integer getCreatorId() {
		return senderId;
	}

	public int getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public boolean isReported() {
		return reported;
	}

	public void setReported(boolean reported) {
		this.reported = reported;
	}

	public boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	public boolean isReplied() {
		return replied;
	}

	public void setReplied(boolean replied) {
		this.replied = replied;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public Integer getRepliedMailId() {
		return repliedMailId;
	}

	public void setRepliedMailId(Integer repliedMailId) {
		this.repliedMailId = repliedMailId;
	}

	@Override
	public String getActivitySummary() {
		return sender + ": " + subject;
	}

	@Override
	public String getActivityTitle() {
		return "Sent mail";
	}

	@Override
	public Action getAction() {
		return new Action(id, "mail");
	}

	public Date getDelivered() {
		return delivered;
	}

	public void setDelivered(Date delivered) {
		this.delivered = delivered;
	}

	public int getSystemType() {
		return systemType;
	}

	public void setSystemType(int systemType) {
		this.systemType = systemType;
	}

	@Override
	public String toString() {
		return "Mail [from=" + senderId + ", to=" + recipientId
				+ ", sysType=" + systemType + ", " + subject
				+ ", " + created + "]";
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

}
