package com.shine.boards;

public class BoardDetails {

	private String location;
	private String name;

	public BoardDetails() {
	}

	public BoardDetails(String name, String location) {
		super();
		this.location = location;
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

}
