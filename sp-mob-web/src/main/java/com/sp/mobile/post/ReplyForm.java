package com.sp.mobile.post;

public class ReplyForm {

	private String reply;
	private String noticeTitle;
	private int boardId;
	private int noticeId;

	ReplyForm(int noticeId, int boardId, String noticeTitle) {
		this.noticeTitle = noticeTitle;
		this.boardId = boardId;
		this.noticeId = noticeId;
	}

	ReplyForm() {
	}

	public String getReply() {
		return reply;
	}

	public void setReply(String reply) {
		this.reply = reply;
	}

	public String getNoticeTitle() {
		return noticeTitle;
	}

	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

}
