package com.shine.xml;

import com.shine.objects.Player;

/**
 * Represents another Player, with only basic information. Full info is in the
 * User object
 * 
 */
public class PlayerXml extends XmlObject {

	private Player player;

	public PlayerXml(Player player) {
		super(player);
		this.player = player;
	}

	@Override
	public String toXml() {
		StringBuilder xml = getStartXml();
		xml.append(formatAttribute("username", player.getUsername()))
				.append(formatIntAttribute("score", player.getScore()))
				.append(formatAttribute("forename", player.getForename()))
				.append(formatAttribute("offers", player.getOffers()))
				.append(formatAttribute("gender", player.getGender()))
				.append(formatAttribute("email", player.getEmail()))
				.append(formatAttribute("biog", player.getBiog()))
				.append(formatAttribute("dob", player.getDob()))
				.append(formatAttribute("status", player.getStatus()))
				.append(formatAttribute("token", player.getToken()))
				.append(formatAttribute("hasicon", player.isHasIcon()))
				.append(formatIntAttribute("avatar", player.getAvatar()))
				.append("/>");
		return xml.toString();
	}

	public StringBuilder getStartXml() {
		StringBuilder xml = new StringBuilder("<object t=\"")
				.append("Player")
				.append("\" ")
				.append(formatDoubleAttribute("lat", player.getLat()))
				.append(formatDoubleAttribute("lng", player.getLng()))
				.append(formatIntAttribute("id", player.getId()));

		return xml;
	}

}
