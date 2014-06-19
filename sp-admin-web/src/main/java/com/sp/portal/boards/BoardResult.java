package com.sp.portal.boards;

import java.util.List;

import com.shine.boards.NoticeBoard;

public class BoardResult {

	private List<NoticeBoard> activeBoards;

	private List<NoticeBoard> draftBoards;

	private Integer start;
	private Integer boardCount;
	private Integer totalBoards;

	public BoardResult(List<NoticeBoard> activeBoards, List<NoticeBoard> draftBoards, Integer start, Integer boardCount, Integer totalBoards) {
		this.activeBoards = activeBoards;
		this.draftBoards = draftBoards;
		this.start = start;
		this.boardCount = boardCount;
		this.totalBoards = totalBoards;
	}

	public List<NoticeBoard> getActiveBoards() {
		return activeBoards;
	}

	public List<NoticeBoard> getDraftBoards() {
		return draftBoards;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getBoardCount() {
		return boardCount;
	}

	public Integer getTotalBoards() {
		return totalBoards;
	}

}
