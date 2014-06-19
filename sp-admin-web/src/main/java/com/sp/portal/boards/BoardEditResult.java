package com.sp.portal.boards;

import java.util.List;

import com.shine.boards.AdCategory;
import com.shine.boards.CorpBrand;
import com.shine.boards.NoticeBoard;

public class BoardEditResult {
	private List<CorpBrand> userBrands;
	private List<AdCategory> adCategories;
	private NoticeBoard board;
	
	public BoardEditResult(	List<CorpBrand> userBrands,
							List<AdCategory> adCategories,
							NoticeBoard board) {
		super();
		this.userBrands = userBrands;
		this.adCategories = adCategories;
		this.board = board;
	}
	
	public List<CorpBrand> getUserBrands() {
		return userBrands;
	}
	public void setUserBrands(List<CorpBrand> userBrands) {
		this.userBrands = userBrands;
	}
	public List<AdCategory> getAdCategories() {
		return adCategories;
	}
	public void setAdCategories(List<AdCategory> adCategories) {
		this.adCategories = adCategories;
	}
	public NoticeBoard getBoard() {
		return board;
	}
	public void setBoard(NoticeBoard board) {
		this.board = board;
	}
	

}
