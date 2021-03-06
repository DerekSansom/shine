package com.shine.xml;

import com.shine.objects.OtherPlayer;

/**
 * Represents another Player, with only basic information. Full info is in the
 * User object
 * 
 */
public class OtherPlayerXml extends XmlObject {

	private OtherPlayer otherPlayer;

	public OtherPlayerXml(OtherPlayer otherPlayer) {
		super(otherPlayer);
		this.otherPlayer = otherPlayer;
	}

	@Override
	public String toXml() {
		StringBuilder xml = getStartXml();
		xml.append(formatAttribute("username", otherPlayer.getUsername()))
				.append(formatIntAttribute("score", otherPlayer.getScore()))
				.append(formatAttribute("status", otherPlayer.getStatus()))
				.append(formatAttribute("biog", otherPlayer.getBiog()))
				.append(formatAttribute("icon", otherPlayer.isHasIcon()))
				.append(formatIntAttribute("avatar", otherPlayer.getAvatar()))
				.append(formatExtras())
				.append("/>");

		return xml.toString();
	}

	public String toXmlLite() {
		StringBuffer xml = new StringBuffer("<object t=\"Player\" ")
				.append(formatIntAttribute("id", otherPlayer.getId()))
				.append(formatAttribute("username", otherPlayer.getUsername()))
				.append(formatIntAttribute("score", otherPlayer.getScore()))
				.append(formatAttribute("created", otherPlayer.getCreated()))
				.append(formatAttribute("status", otherPlayer.getStatus()))
				.append(formatAttribute("icon", otherPlayer.isHasIcon()))
				.append(formatIntAttribute("avatar", otherPlayer.getAvatar()))
				.append(formatExtras())
				.append("/>");

		return xml.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof OtherPlayerXml && otherPlayer.getId() != 0) {
			return otherPlayer.getId() == ((OtherPlayerXml) o).getId();
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * getId();
	}

	int getId() {
		return otherPlayer.getId();
	}

	public StringBuilder getStartXml() {
		StringBuilder xml = new StringBuilder("<object t=\"")
				.append("Player")
				.append("\" ")
				.append(formatDoubleAttribute("lat", otherPlayer.getLat()))
				.append(formatDoubleAttribute("lng", otherPlayer.getLng()))
				.append(formatIntAttribute("id", otherPlayer.getId()));

		return xml;
	}

}
