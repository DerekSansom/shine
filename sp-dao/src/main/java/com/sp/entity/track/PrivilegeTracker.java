package com.sp.entity.track;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trk_privileges")
public class PrivilegeTracker extends TrackObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "userid")
	private int userid;

	@Column(name = "friendid")
	private int friendid;

	private int privilege;

	public PrivilegeTracker() {
		super(0);
	}

	public PrivilegeTracker(int userid, int friendid, int privilege, int client) {
		super(client);
		this.userid = userid;
		this.friendid = friendid;
		this.privilege = privilege;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getFriendid() {
		return friendid;
	}

	public void setFriendid(int friendid) {
		this.friendid = friendid;
	}

	public int getPrivilege() {
		return privilege;
	}

	public void setPrivilege(int privilege) {
		this.privilege = privilege;
	}

}
