package com.sp.entity.track;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trk_ad_saves")
public class AdSave extends TrackObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "userid")
	private int userId;

	@Column(name = "saveid")
	private int saveId;

	@Column(name = "deleteid")
	private int deleteId;

	private int action;

	public AdSave() {
		super(0);
	}

	public AdSave(int userId, int saveId, int deleteId, int action, int client) {
		super(client);
		this.userId = userId;
		this.saveId = saveId;
		this.deleteId = deleteId;
		this.action = action;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getSaveId() {
		return saveId;
	}

	public void setSaveId(int saveId) {
		this.saveId = saveId;
	}

	public int getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(int deleteId) {
		this.deleteId = deleteId;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

}
