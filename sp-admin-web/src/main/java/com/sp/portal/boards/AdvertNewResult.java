package com.sp.portal.boards;

import java.util.List;

import com.shine.boards.AdCategory;
import com.shine.boards.CorpBrand;

public class AdvertNewResult {

	private List<CorpBrand> userBrands;
	private List<AdCategory> adCategories;
	
	public AdvertNewResult(List<CorpBrand> userBrands,
			List<AdCategory> adCategories) {
		super();
		this.userBrands = userBrands;
		this.adCategories = adCategories;
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

}
