package com.shine.boards;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.shine.UserActivity;
import com.shine.objects.OtherPlayer;
import com.shine.objects.ShineObject;

@JsonRootName("reply")
public class Reply extends ShineObject implements Serializable, UserActivity {

	private int noticeId;
	private int creatorId;
	private String author, reply;
	private Boolean reported = Boolean.FALSE, suspended = Boolean.FALSE;
	private OtherPlayer player;
	private BoardDetails boardDetails;

	public Reply() {
	}

	/**
	 * The basic constructor with required fields for the app to create a new
	 * Reply
	 * 
	 * @param id
	 * @param noticeId
	 * @param creatorId
	 * @param notice
	 */
	public Reply(int id, int noticeId, int creatorId, String reply) {
		super(id);
		this.creatorId = creatorId;
		this.reply = reply;
		this.noticeId = noticeId;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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
		if (o != null && o instanceof Reply) {
			Reply r = (Reply) o;
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
		return "Reply: id=" + id + ", noticeid=" + noticeId + ", creatorid="
 + creatorId + ", created=" + getCreated();
	}

	@Override
	public String getActivitySummary() {
		return reply;
	}

	@Override
	public String getActivityTitle() {
		return "replied to notice";
	}

	@Override
	public Action getAction() {
		return new Action(noticeId, "notice");
	}

	public OtherPlayer getPlayer() {
		return player;
	}

	public void setPlayer(OtherPlayer player) {
		this.player = player;
	}

	public BoardDetails getBoardDetails() {
		return boardDetails;
	}

	public void setBoardDetails(BoardDetails boardDetails) {
		this.boardDetails = boardDetails;
	}

}
