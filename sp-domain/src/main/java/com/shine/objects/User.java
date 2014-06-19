package com.shine.objects;

public class User {
	private int id;
	private String username;
	private String password;
	private String corp_id;
	private String salt;
	private String role;
	private String date_created;
	private String email;
	private String phone;
	private Double lat;
	private Double lng;
	private boolean active;
	private String confirmation_key;
	private String reset_key;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCorp_id() {
		return corp_id;
	}
	public void setCorp_id(String corp_id) {
		this.corp_id = corp_id;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getDate_created() {
		return date_created;
	}
	public void setDate_created(String date_created) {
		this.date_created = date_created;
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
	public Double getLat() {
		return lat;
	}
	public void setLat(Double lat) {
		this.lat = lat;
	}
	public Double getLng() {
		return lng;
	}
	public void setLng(Double lng) {
		this.lng = lng;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getConfirmation_key() {
		return confirmation_key;
	}
	public void setConfirmation_key(String confirmation_key) {
		this.confirmation_key = confirmation_key;
	}
	public String getReset_key() {
		return reset_key;
	}
	public void setReset_key(String reset_key) {
		this.reset_key = reset_key;
	}
}
