package com.sp.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Transient;

@Entity
@Table(name = "notices")
public class NoticeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(name = "boardid")
	private int boardId;

	@Column(name = "categoryid")
	private int categoryId;

	@Column(name = "title")
	private String title;

	@Column(name = "notice")
	private String notice;

	@Column(name = "image")
	private String imageUrl;

	@Column(name = "expires")
	@Temporal(TemporalType.TIMESTAMP)
	private Date expires;

	@ManyToOne
	@JoinColumn(name = "creatorid")
	private PlayerEntity player;

	@Column(name = "reported")
	private Boolean reported = Boolean.FALSE;

	@Column(name = "suspended")
	private Boolean suspended = Boolean.FALSE;

	@Transient
	private List<ReplyEntity> replies;

	public NoticeEntity() {

	}

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public Date getExpires() {
		return expires;
	}

	public void setExpires(Date expires) {
		this.expires = expires;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public Boolean isReported() {
		return reported;
	}

	public void setReported(Boolean reported) {
		this.reported = reported;
	}

	public List<ReplyEntity> getReplies() {
		return replies;
	}

	public void addReply(ReplyEntity reply) {
		if (replies == null) {
			replies = new ArrayList<ReplyEntity>();
		}
		replies.add(reply);
	}

	public Boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	public void setReplies(List<ReplyEntity> replies) {
		this.replies = replies;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		// this.category = NoticeCategory.valueOf(author);
		this.categoryId = categoryId;
	}

	@Override
	public boolean equals(Object o) {

		if (o != null && o instanceof NoticeEntity) {
			NoticeEntity n = (NoticeEntity) o;
			return n.id == id && n.boardId == boardId;
		}
		return false;
	}

	@Override
	public int hashCode() {

		return 34 * id * boardId;
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder(getClass().getSimpleName());
		sb.append(": id=").append(id);

		return sb.toString();
	}

	public PlayerEntity getPlayer() {
		return player;
	}

	public void setPlayer(PlayerEntity player) {
		this.player = player;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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