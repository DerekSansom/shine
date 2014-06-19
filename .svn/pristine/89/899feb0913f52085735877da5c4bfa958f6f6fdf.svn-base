package com.sp.entity.error;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "errors")
public class ErrorReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	private String error, os, version, platform;

	public ErrorReportEntity() {
	}

	public ErrorReportEntity(String error, String os, String version, String platform) {
		super();
		this.error = error;
		this.os = os;
		this.version = version;
		this.platform = platform;
	}

	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

}
