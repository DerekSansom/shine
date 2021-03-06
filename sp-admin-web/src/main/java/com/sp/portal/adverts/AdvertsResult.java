package com.sp.portal.adverts;

import java.util.List;

import com.shine.boards.Advert;

public class AdvertsResult {

	private List<Advert> adverts;
	private Integer start;
	private Integer advertCount;
	private Integer totalAdverts;

	public AdvertsResult(List<Advert> adverts, Integer start, Integer advertCount, Integer totalAdverts) {
		this.adverts = adverts;
		this.start = start;
		this.advertCount = advertCount;
		this.totalAdverts = totalAdverts;
	}

	public List<Advert> getAdverts() {
		return adverts;
	}

	public Integer getStart() {
		return start;
	}

	public Integer getBoardCount() {
		return advertCount;
	}

	public Integer getTotalBoards() {
		return totalAdverts;
	}
}
