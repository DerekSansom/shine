package com.shine.objects;

import java.util.Date;

/**
 * Represents another Player, with only basic information. Full info is in the
 * User object
 * 
 * 
 * @author derek
 * 
 */
public class OtherPlayer extends LocObject implements Sp {

	private String username, status, biog;
	private int score;
	private Integer avatar;
	private boolean hasIcon;
	private Date suspended, updated;

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

	public void addScore(int score) {
		this.score += score;
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

	@Override
	public String toString() {

		return id + ":" + username;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof OtherPlayer && id != 0) {
			return id == ((OtherPlayer) o).id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * id;
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
