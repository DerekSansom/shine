package com.shine.xml;

import com.shine.boards.Reply;

public class ReplyXml extends XmlObject {

	private Reply reply;

	public ReplyXml(Reply reply) {
		super(reply);
		this.reply = reply;
	}

	@Override
	public String toXml() {

		StringBuilder xml = new StringBuilder("<reply");
		xml.append(formatIntAttribute("id", reply.getId()))
				.append(formatAttribute("created", reply.getCreated()))
				.append(formatAttribute("author", reply.getAuthor()))
				.append(formatIntAttribute("creatorid", reply.getCreatorId()))
				.append(formatIntAttribute("noticeid", reply.getNoticeId()))
				.append(formatExtras())
				.append(formatBoardDetails(reply.getBoardDetails()))
				.append(">")
				.append(escape(reply.getReply())).append("</reply>");

		return xml.toString();

	}

}
