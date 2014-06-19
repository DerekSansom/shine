package com.sp.entity.track;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trk_login")
public class Login extends TrackObject {
	private String cred, phone, password;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	public Login() {
		super(0);
	}

	public Login(String cred, String phone, String password, int client) {
		super(client);
		this.cred = cred;
		this.phone = phone;
		this.password = password;
	}

	public String getCred() {
		return cred;
	}

	public void setCred(String cred) {
		this.cred = cred;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
