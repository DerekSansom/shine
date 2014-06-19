package com.shine.xml;

import com.shine.boards.CorpBrand;

public class CorpBrandXml extends XmlObject {

	private CorpBrand corpBrand;

	public CorpBrandXml(CorpBrand corpBrand) {
		super(corpBrand);
		this.corpBrand = corpBrand;
	}

	@Override
	public String toXml() {
		StringBuilder xml = new StringBuilder("<brand");
		xml.append(formatIntAttribute("id", corpBrand.getId()))
				.append(formatIntAttribute("corpid", corpBrand.getCorporateId()))
				.append(formatAttribute("name", corpBrand.getName()))
				.append(formatAttribute("backgroundimg", corpBrand.getBackgroundimg()))
				.append(formatAttribute("logo", corpBrand.getLogo()))
				.append(formatAttribute("linkColour", corpBrand.getLinkColour()))
				.append(formatAttribute("textColour", corpBrand.getTextColour()))
				.append(formatAttribute("bgColour", corpBrand.getBgColour()))
				.append(formatAttribute("url", corpBrand.getUrl()))
				.append("/>");
		return xml.toString();
	}

}
