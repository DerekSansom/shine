package com.shine.objects;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public abstract class ShineObject implements Serializable {

	protected int id;
	private static final String EMPTY_STR = "";
	protected Date dateExtra;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MMM-yy HH:mm")
	private Date created;

	protected String stringExtra;
	protected int intExtra;
	private List<ShineObject> listExtra;

	public Date getDateExtra() {
		return dateExtra;
	}

	public void setDateExtra(Date dateExtra) {
		this.dateExtra = dateExtra;
	}

	public String getStringExtra() {
		return stringExtra;
	}

	public void setStringExtra(String stringExtra) {
		this.stringExtra = stringExtra;
	}

	public ShineObject(int id) {
		this.id = id;
	}

	public ShineObject() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	protected String formatIntAttribute(String attr, Integer value) {
		if (value == null) {
			return EMPTY_STR;
		}
		return formatIntAttribute(attr, value.intValue());
	}

	protected String formatDoubleAttribute(String attr, Double value) {
		if (value == null) {
			return EMPTY_STR;
		}
		return formatDoubleAttribute(attr, value.doubleValue());

	}

	private String formatPhoneNo(String value) {
		value = value.replaceAll(" ", "");
		value = value.replaceAll("-", "");
		return value;
	}

	public int getIntExtra() {
		return intExtra;
	}

	public void setIntExtra(int intExtra) {
		this.intExtra = intExtra;
	}

	public List<ShineObject> getListExtra() {
		return listExtra;
	}

	public void setListExtra(List<ShineObject> listExtra) {
		this.listExtra = listExtra;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}
