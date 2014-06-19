package com.shine.xml;

import com.shine.Util;
import com.shine.boards.Advert;

public class AdvertXml extends XmlObject {

	private Advert advert;

	private String xml = null;

	public AdvertXml(Advert advert) {
		super(advert);
		this.advert = advert;
	}


	@Override
	public String toXml() {

		if (xml == null) {
			String icon = advert.getIconUrl();
			if (Util.isEmpty(icon) && advert.getBrand() != null && Util.isNotEmpty(advert.getBrand().getLogo())) {
				icon = advert.getBrand().getLogo();
			}

			StringBuilder xmlSb = new StringBuilder("<advert");
			xmlSb.append(formatIntAttribute("id", advert.getId()))
					.append(formatAttribute("displayname", advert.getDisplayname()))
					.append(formatAttribute("title", advert.getTitle()))
					.append(formatAttribute("text", advert.getText()))
					.append(formatAttribute("iconUrl", advert.getIconUrl()))
					.append(formatAttribute("imageUrl", advert.getImageUrl()))
					.append(formatAttribute("url", advert.getUrl()))
					.append(formatIntAttribute("catid", advert.getCategoryId()))
					.append(formatPhoneAttribute(advert.getPhoneno()))
					.append(formatAttribute("email", advert.getEmail()))
					.append(formatIntAttribute("categoryId", advert.getCategoryId()))
					.append(formatIntAttribute("advertiserId", advert.getAdvertiserId()))
					.append(formatDoubleAttribute("lat", advert.getLat()))
					.append(formatDoubleAttribute("lng", advert.getLng()))
					.append(formatAttribute("expires", advert.getExpires()))
					.append("/>");
			xml = xmlSb.toString();
		}
		return xml;

	}


}
