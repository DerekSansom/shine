package com.shine.xml;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.shine.boards.Advert;
import com.shine.boards.Notice;
import com.shine.boards.NoticeBoard;
import com.shine.objects.OtherPlayer;

@JsonRootName("noticeboard")
public class NoticeBoardXml extends XmlObject
{
	private NoticeBoard noticeBoard;

	public NoticeBoardXml(NoticeBoard noticeBoard) {
		super(noticeBoard);
		this.noticeBoard = noticeBoard;
	}

	@Override
	public String toXml() {
		StringBuilder xml = getStartXml("NoticeBoard");
		xml.append(formatAttribute("name", noticeBoard.getName()))
				.append(formatAttribute("location", noticeBoard.getLocationName()))
				.append(formatIntAttribute("brandid", noticeBoard.getBrandId()))
				.append(formatIntAttribute("creatorid", noticeBoard.getCreatorId()))
				.append(formatIntAttribute("noticecount", noticeBoard.getNoticeCount()))
				.append(formatIntAttribute("adcount", noticeBoard.getAdvertCount()))
				.append(formatIntAttribute("usercount", noticeBoard.getUserCount()))
				.append(formatAttribute("creator", noticeBoard.getCreator()))
				.append(formatAttribute("created", noticeBoard.getCreated()))
				.append(formatCategory())
				.append(formatAttribute("bannerAd", noticeBoard.getBannerAd()))
				.append(formatAttribute("bannerAdUrl", noticeBoard.getBannerUrl()))
				.append(formatAttribute("rss1", noticeBoard.getRss1()))
				.append(formatAttribute("rss1Title", noticeBoard.getRss1Title()))
				.append(formatAttribute("rss2", noticeBoard.getRss2()))
				.append(formatAttribute("rss2Title", noticeBoard.getRss2Title()))
				.append(">");
		for (Notice notice : noticeBoard.getNotices()) {
			NoticeXml nXml = new NoticeXml(notice);
			xml.append(nXml.toXml());
		}
		for (Advert ad : noticeBoard.getAds()) {
			AdvertXml aXml = new AdvertXml(ad);
			xml.append(aXml.toXml());
		}
		if (noticeBoard.getBrand() != null) {
			xml.append(new CorpBrandXml(noticeBoard.getBrand()).toXml());
		}
		if (noticeBoard.getListExtra() != null) {
			for (Object o : noticeBoard.getListExtra()) {
				if (o instanceof OtherPlayer) {
					OtherPlayerXml opXml = new OtherPlayerXml((OtherPlayer) o);
					xml.append(opXml.toXml());
				} else {
					break;
				}
			}

		}

		xml.append("</object>");

		return xml.toString();
	}

	public String toMapXml() {
		StringBuilder xml = getStartXml("NoticeBoard");
		xml.append(formatAttribute("name", noticeBoard.getName()))
				.append(formatIntAttribute("brandid", noticeBoard.getBrandId()))
				.append(formatAttribute("location", noticeBoard.getLocationName()))
				.append(formatIntAttribute("distancem", noticeBoard.getDistanceMeters()))
				.append(formatCategory())
				.append("/>");

		return xml.toString();
	}

	private String formatCategory() {

		return noticeBoard.getCategory() != null ? formatAttribute("category", noticeBoard.getCategory().name()) : "";
	}

	public String toXmlLite() {
		StringBuilder xml = getStartXml("NoticeBoard");
		xml.append(formatAttribute("created", noticeBoard.getCreated()))
				.append(formatAttribute("name", noticeBoard.getName()))
				.append(formatCategory())
				.append(
						formatAttribute("location", noticeBoard.getLocationName())).append("/>");

		return xml.toString();
	}

	public StringBuilder getStartXml(String objectName) {
		StringBuilder xml = new StringBuilder("<object t=\"")
				.append(objectName)
				.append("\" ")
				.append(formatDoubleAttribute("lat", noticeBoard.getLat()))
				.append(formatDoubleAttribute("lng", noticeBoard.getLng()))
				.append(formatIntAttribute("id", noticeBoard.getId()));

		return xml;
	}

}
