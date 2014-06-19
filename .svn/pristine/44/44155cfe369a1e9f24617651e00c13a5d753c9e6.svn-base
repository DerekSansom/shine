package com.sp.player;

import java.io.Serializable;
import java.util.Date;

public class Privileges implements Serializable {
	private static final long serialVersionUID = -62061261485447787L;
	private int userId, friendId;
	private long privilege;
	private Date created, accepted, blocked;
	private boolean blockedByFriend;

	public long getPrivilege() {
		return privilege;
	}

	public void setPrivilege(long privilege) {
		this.privilege = privilege;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getFriendId() {
		return friendId;
	}

	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Privileges) {
			Privileges other = (Privileges) obj;

			if (userId == other.userId
					&& friendId == other.friendId) {
				return true;
			}

		}
		return false;
	}

	@Override
	public int hashCode() {

		int hc = (361 * userId) + (37 * friendId);

		return hc;
	}

	public Date getAccepted() {
		return accepted;
	}

	public void setAccepted(Date accepted) {
		this.accepted = accepted;
	}

	/**
	 * 
	 * @return if friend has blocked
	 */
	public boolean isBlockedByFriend() {
		return blockedByFriend;
	}

	public void setBlockedByFriend(boolean blocked) {
		this.blockedByFriend = blocked;
	}

	public Date getBlocked() {
		return blocked;
	}

	public void setBlocked(Date blocked) {
		this.blocked = blocked;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
