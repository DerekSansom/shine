package com.sp.entity.msg;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "mail_msg")
public class MailEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "creatorid")
	private int senderId;

	@Column(name = "recipientid")
	private int recipientId;

	@Column(name = "sysType")
	private int systemType;

	@Column(name = "intx")
	private int intExtra;

	@Column(name = "replytoid")
	private Integer repliedMailId;

	@Column(name = "subject")
	private String subject;

	@Column(name = "message")
	private String message;

	@Column(name = "delivered")
	private Date delivered;

	@Column(name = "created")
	private Date created;

	@Column(name = "reported")
	private boolean reported;

	@Column(name = "suspended")
	private boolean suspended;

	@Column(name = "is_read")
	private boolean read;

	@Column(name = "replied")
	private boolean replied;

	@Transient
	private boolean newMail;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Integer getRepliedMailId() {
		return repliedMailId;
	}

	public void setRepliedMailId(Integer repliedMailId) {
		this.repliedMailId = repliedMailId;
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

	public boolean isNewMail() {
		return newMail;
	}

	public void setNewMail(boolean newMail) {
		this.newMail = newMail;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getIntExtra() {
		return intExtra;
	}

	public void setIntExtra(int intExtra) {
		this.intExtra = intExtra;
	}

}
