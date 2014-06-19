package com.sp.entity.ad;

import java.io.Serializable;
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
@Table(name = "advert_display_params")
public class AdDisplayParams implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "advertid")
	private int advertId;

	@Column(name = "frequency")
	private int frequency;

	@Column(name = "start")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date start;

	@Column(name = "end")
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date end;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAdvertId() {
		return advertId;
	}

	public void setAdvertId(int advertId) {
		this.advertId = advertId;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

}
