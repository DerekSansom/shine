package com.sp.entity.track;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class TrackObject {

	private int client;

	public TrackObject() {
		this.client = 0;
	}
	
	public TrackObject(int client) {
		this.client = client;
	}

	public int getClient() {
		return client;
	}

	public void setClient(int client) {
		this.client = client;
	}
}
