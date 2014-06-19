package com.shine.mail;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MsgThread {

	public class Message {

		public Message(Date created, boolean isNew, String message, boolean from, int sysType, int intx) {
			super();
			this.created = created;
			this.isNew = isNew;
			this.message = message;
			this.from = from;
			this.sysType = sysType;
			this.intx = intx;
		}

		Date created;
		boolean isNew;
		String message;
		boolean from;
		int sysType;
		int intx;

		public Date getCreated() {
			return created;
		}

		public boolean isNew() {
			return isNew;
		}

		public boolean isFrom() {
			return from;
		}

		public String getMessage() {
			return message;
		}

		public int getSysType() {
			return sysType;
		}

		public int getIntx() {
			return intx;
		}

	}

	private int userId, correspondentId;
	private List<Message> msgs;
	private Date recent;
	private String correspodentName;
	private boolean hasNew, hasUnread;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getCorrespondentId() {
		return correspondentId;
	}

	public void setCorrespondentId(int correspondentId) {
		this.correspondentId = correspondentId;
	}

	public List<Message> getMsgs() {
		if (msgs == null) {
			msgs = new ArrayList<MsgThread.Message>();
		}
		return msgs;
	}

	public void setMsgs(List<Message> msgs) {
		this.msgs = msgs;
	}

	public Date getRecent() {
		return recent;
	}

	public void setRecent(Date recent) {
		this.recent = recent;
	}

	public String getCorrespodentName() {
		return correspodentName;
	}

	public void setCorrespodentName(String correspodentName) {
		this.correspodentName = correspodentName;
	}

	public boolean isHasNew() {
		return hasNew;
	}

	public void setHasNew(boolean hasNew) {
		this.hasNew = hasNew;
	}

	public boolean isHasUnread() {
		return hasUnread;
	}

	public void setHasUnread(boolean hasUnread) {
		this.hasUnread = hasUnread;
	}

}
