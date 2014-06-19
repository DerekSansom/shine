package com.shine.objects;

import java.util.Date;

public class Player extends LocObject implements Sp {

	private String forename;
	private String surname;
	private String email;
	private String phone;
	private String token;
	private String password;
	private String username;
	private String biog;
	private String offers, status, pkey, os, platform, version;

	private Date dob;
	private String gender;
	private int score, client;
	private Integer avatar;
	private boolean hasIcon;
	private boolean hasGame;
	private boolean hasBoards;
	private Date suspended, updated;

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOffers() {
		return offers;
	}

	public void setOffers(String offers) {
		this.offers = offers;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public boolean equals(Object o) {

		if (o != null && o instanceof Player) {
			Player u = (Player) o;
			return id == u.id;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Userid:" + id + " - " + username;
	}

	@Override
	public int hashCode() {
		return 56 * (id + 1);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isHasIcon() {
		return hasIcon;
	}

	public void setHasIcon(boolean hasIcon) {
		this.hasIcon = hasIcon;
	}

	public boolean isHasGame() {
		return hasGame;
	}

	public void setHasGame(boolean hasGame) {
		this.hasGame = hasGame;
	}

	public boolean isHasBoards() {
		return hasBoards;
	}

	public void setHasBoards(boolean hasBoards) {
		this.hasBoards = hasBoards;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}

	public Date getSuspended() {
		return suspended;
	}

	public void setSuspended(Date suspended) {
		this.suspended = suspended;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public Integer getAvatar() {
		return avatar;
	}

	public void setAvatar(Integer avatar) {
		this.avatar = avatar;
	}

	public String getBiog() {
		return biog;
	}

	public void setBiog(String biog) {
		this.biog = biog;
	}

}
