package com.shine.boards;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shine.UserActivity;
import com.shine.objects.OtherPlayer;
import com.shine.objects.ShineObject;

public class Notice extends ShineObject implements UserActivity {

	private static final long serialVersionUID = 1L;
	private int boardId, repliesCount = -1;
	private Integer creatorId;
	private int categoryId;
	private String title, author, notice;
	private String category, imageUrl;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd,HH:00")
	private Date expires;
	private OtherPlayer player;
	private Boolean reported = Boolean.FALSE, suspended = Boolean.FALSE;
	private List<Reply> replies;

	private BoardDetails boardDetails;

	public Notice() {

	}

	//
	// public Notice(int id, int boardId, String author, int catId,
	// int repliesCount, Date created, Date expires) {
	// super(id);
	// this.boardId = boardId;
	// this.repliesCount = repliesCount;
	// this.author = author;
	// this.categoryId = catId;
	// setCreated(created);
	// this.expires = expires;
	// }
	//
	/**
	 * 
	 * 
	 * @param id
	 * @param boardId
	 * @param creatorid
	 * @param title
	 * @param notice
	 * @param category
	 * @param expires
	 */
	public Notice(int boardId, int creatorId, String title,
			String notice, int catId, Date expires) {
		this(0, boardId, creatorId, title, notice, catId, expires);
	}

	private Notice(int id, int boardId, int creatorId, String title,
			String notice, int catId, Date expires) {
		super(id);
		this.boardId = boardId;
		this.creatorId = creatorId;
		this.title = title;
		this.categoryId = catId;
		this.notice = notice;
		this.expires = expires;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	public Boolean isReported() {
		return reported;
	}

	public void setReported(Boolean reported) {
		this.reported = reported;
	}

	public int getRepliesCount() {
		return repliesCount;
	}

	public void setRepliesCount(int repliesCount) {
		this.repliesCount = repliesCount;
	}

	public List<Reply> getReplies() {
		return replies;
	}

	public void addReply(Reply reply) {
		if (replies == null) {
			replies = new ArrayList<Reply>();
		}
		replies.add(reply);
	}

	@Deprecated
	public String getCategory() {
		return category;
	}

	@Deprecated
	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean isSuspended() {
		return suspended;
	}

	public void setSuspended(Boolean suspended) {
		this.suspended = suspended;
	}

	public void setReplies(List<Reply> replies) {
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

		if (o != null && o instanceof Notice) {
			Notice n = (Notice) o;
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

	public OtherPlayer getPlayer() {
		return player;
	}

	public void setPlayer(OtherPlayer player) {
		this.player = player;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String getActivitySummary() {
		return notice;
	}

	@Override
	public String getActivityTitle() {
		return title;
	}

	@Override
	public Action getAction() {
		return new Action(id, "notice");
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void popAuthor() {
		if (player != null) {
			author = player.getUsername();
		}
	}

	public BoardDetails getBoardDetails() {
		return boardDetails;
	}

	public void setBoardDetails(BoardDetails boardDetails) {
		this.boardDetails = boardDetails;
	}
}