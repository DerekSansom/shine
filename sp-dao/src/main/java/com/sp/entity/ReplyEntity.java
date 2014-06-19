package com.sp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "replies")
public class ReplyEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "noticeid")
	private int noticeId;

	@Column(name = "reply")
	private String reply;

	@Column(name = "reported")
	private Boolean reported = Boolean.FALSE;

	@Column(name = "suspended")
	private Boolean suspended = Boolean.FALSE;

	@ManyToOne
	@JoinColumn(name = "creatorid")
	private PlayerEntity player;

	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	public ReplyEntity() {

	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public Boolean isReported() {
		return reported;
	}

	public void setReported(Boolean reported) {
		this.reported = reported;
	}

	public Boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null && o instanceof ReplyEntity) {
			ReplyEntity r = (ReplyEntity) o;
			int rid = r.getId();
			int rnotid = r.getNoticeId();
			boolean eq = rid == id;
			boolean eq2 = rnotid == noticeId;

			boolean ret = eq && eq2;

			return ret;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return (id + 1) * (noticeId + 1);

	}

	@Override
	public String toString() {
		return "Reply: id=" + id + ", noticeid=" + noticeId + ", created=" + getCreated();
	}


	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
