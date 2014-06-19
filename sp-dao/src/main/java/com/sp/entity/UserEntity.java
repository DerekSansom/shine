package com.sp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "users")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "corp_id")
	private Integer corp_id;
	
	@Column(name = "salt")
	private String salt;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "date_created")
	@Temporal(TemporalType.TIMESTAMP)
	private Date date_created;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "active")
	private Boolean accepted = Boolean.FALSE;

	@Column(name = "confirmation_key")
	private String confirmation_key;

	@Column(name = "reset_key")
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

	public Integer getCorp_id() {
		return corp_id;
	}

	public void setCorp_id(Integer corp_id) {
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

	public Date getDate_created() {
		return date_created;
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

	// public double getLat() {
	// return lat;
	// }
	//
	// public void setLat(double lat) {
	// this.lat = lat;
	// }
	//
	// public double getLng() {
	// return lng;
	// }
	//
	// public void setLng(double lng) {
	// this.lng = lng;
	// }
	//
	// public Boolean getAccepted() {
	// return accepted;
	// }
	//
	// public void setAccepted(Boolean accepted) {
	// this.accepted = accepted;
	// }

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
