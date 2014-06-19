package com.sp.entity.track;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TrackObject {

	private int client;

	public TrackObject(int client) {
		super();
		this.client = client;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}
}
