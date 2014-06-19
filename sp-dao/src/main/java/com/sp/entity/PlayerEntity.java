package com.sp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class PlayerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "lat")
	private Double lat;

	@Column(name = "lng")
	private Double lng;

	@Column(name = "username")
	private String username;

	@Column(name = "forename")
	private String forename;

	@Column(name = "gender")
	private String gender;

	@Column(name = "surname")
	private String surname;

	@Column(name = "status")
	private String status;

	@Column(name = "biog")
	private String biog;

	@Column(name = "offers")
	private String offers;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "token")
	private String token;

	@Column(name = "pkey")
	private String pkey;

	@Column(name = "score")
	private int score;

	@Column(name = "client")
	private Integer client;

	@Column(name = "avatar")
	private Integer avatar;

	@Column(name = "hasIcon")
	private boolean hasIcon;

	@Column(name = "suspended")
	private Date suspended;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "updated")
	private Date updated;

	@Column(name = "dateCreated")
	private Date created;

	@Column(name = "platform")
	private String platform;

	@Column(name = "os")
	private String os;

	@Column(name = "version")
	private String version;

	public Double getLat() {
		return lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void addScore(int score) {
		this.score += score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isHasIcon() {
		return hasIcon;
	}

	public void setHasIcon(boolean hasIcon) {
		this.hasIcon = hasIcon;
	}

	@Override
	public String toString() {

		return id + ":" + username;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof PlayerEntity && id != 0) {
			return id == ((PlayerEntity) o).id;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return 31 * id;
	}

	public Date getSuspended() {
		return suspended;
	}

	public void setSuspended(Date suspended) {
		this.suspended = suspended;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Integer getAvatar() {
		return avatar;
	}

	public void setAvatar(Integer avatar) {
		this.avatar = avatar;
	}

	public String getBiog() {
		return biog;
	}

	public void setBiog(String biog) {
		this.biog = biog;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getOffers() {
		return offers;
	}

	public void setOffers(String offers) {
		this.offers = offers;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getPkey() {
		return pkey;
	}

	public void setPkey(String pkey) {
		this.pkey = pkey;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getClient() {
		return client;
	}

	public void setClient(Integer client) {
		this.client = client;
	}

}
