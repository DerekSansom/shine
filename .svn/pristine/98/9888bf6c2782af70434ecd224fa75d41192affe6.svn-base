package com.sp.entity.track;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.shine.boards.Advert;

@Entity
@Table(name = "trk_board_views")
public class BoardView extends TrackObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "userid")
	private int userId;

	@Column(name = "boardid")
	private int boardId;

	@Transient
	private List<Advert> ads;

	public BoardView() {
		super(0);
	}

	public BoardView(int userId, int boardId, List<Advert> ads, int client) {
		super(client);
		this.userId = userId;
		this.boardId = boardId;
		this.ads = ads;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public int getBoardId() {
		return boardId;
	}

	public List<Advert> getAds() {
		return ads;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public void setAds(List<Advert> ads) {
		this.ads = ads;
	}

}
