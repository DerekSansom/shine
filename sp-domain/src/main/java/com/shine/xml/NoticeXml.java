package com.shine.xml;

import com.shine.boards.Notice;
import com.shine.boards.Reply;

public class NoticeXml extends XmlObject {

	private Notice notice;

	public NoticeXml(Notice notice) {
		super(notice);
		this.notice = notice;
	}


	@Override
	public String toXml() {
		StringBuilder xml = new StringBuilder("<notice");
		xml.append(formatIntAttribute("id", notice.getId()))
				.append(formatAttribute("created", notice.getCreated()))
				.append(formatAttribute("expires", notice.getExpires()))
				.append(formatAttribute("imageUrl", notice.getImageUrl()))
				.append(formatAttribute("author", notice.getAuthor()))
				.append(formatIntAttribute("catid", notice.getCategoryId()))
				.append(formatIntAttribute("creatorid", notice.getCreatorId()))
				.append(formatIntAttribute("boardid", notice.getBoardId()))
				.append(formatIntAttribute("repliesCount", notice.getRepliesCount()))
				.append(formatBoardDetails(notice.getBoardDetails()))
				.append(formatExtras())
				.append(">");
		xml.append("<title>").append(escape(notice.getTitle())).append("</title>").append(
				"<text>").append(escape(notice.getNotice())).append("</text>").append(
				"</notice>");

		return xml.toString();

	}

	public String toXmlWithReplies() {
		StringBuilder xml = new StringBuilder("<notice");
		xml.append(formatIntAttribute("id", notice.getId()))
				.append(formatAttribute("created", notice.getCreated()))
				.append(formatAttribute("expires", notice.getExpires()))
				.append(formatAttribute("imageUrl", notice.getImageUrl()))
				.append(formatAttribute("author", notice.getAuthor()))
				.append(formatIntAttribute("catid", notice.getCategoryId()))
				.append(formatIntAttribute("creatorid", notice.getCreatorId()))
				.append(formatIntAttribute("boardid", notice.getBoardId()))
				.append(formatIntAttribute("repliesCount", notice.getRepliesCount()))
				.append(formatBoardDetails(notice.getBoardDetails()))
				.append(">");
		xml.append("<title>").append(escape(notice.getTitle())).append("</title>").append(
				"<text>").append(escape(notice.getNotice())).append("</text>");
		if (notice.getReplies() != null) {
			for (Reply reply : notice.getReplies()) {
				ReplyXml replyXml = new ReplyXml(reply);
				xml.append(replyXml.toXml());
			}
		}

		xml.append(
				"</notice>");

		return xml.toString();

	}

}