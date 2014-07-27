package com.sp.portal.adverts;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.shine.boards.AdCategory;
import com.shine.boards.CorpBrand;

public class AdvertForm {

	private String displayName;
	private String title;
	private String text;
	private long brandId;
	private AdCategory category;
	private String phoneNo;
	private String email;
	private String url;

	private MultipartFile imageUpload;
	private MultipartFile iconUpload;

	// Selection data
	private List<CorpBrand> userBrands;
	private List<AdCategory> adCategories;

	public AdvertForm(List<CorpBrand> userBrands, List<AdCategory> adCategories) {
		this.userBrands = userBrands;
		this.adCategories = adCategories;
	}

	public AdvertForm() {
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public long getBrandId() {
		return brandId;
	}

	public void setBrandId(long brandId) {
		this.brandId = brandId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public MultipartFile getIconUpload() {
		return iconUpload;
	}

	public void setIconUpload(MultipartFile iconUpload) {
		this.iconUpload = iconUpload;
	}

	public MultipartFile getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(MultipartFile imageUpload) {
		this.imageUpload = imageUpload;
	}

	public AdCategory getCategory() {
		return category;
	}

	public void setCategory(AdCategory category) {
		this.category = category;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public List<CorpBrand> getUserBrands() {
		return userBrands;
	}

	public List<AdCategory> getAdCategories() {
		return adCategories;
	}

}
