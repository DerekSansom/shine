package com.sp.entity.loc;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.sp.entity.ad.DefaultAdParams;

@Entity
@Table(name = "loc_area_2")
public class Area2 extends Location {

	@Transient
	private Area1 area1;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "area2Id")
	private List<DefaultAdParams> defaultAdParams;

	//
	// @OneToMany(fetch = FetchType.EAGER)
	// @JoinColumn(name = "area2Id")
	// private List<Area3> children;
	//
	// public List<Area3> getChildren() {
	// return children;
	// }
	//
	// public void setChildren(List<Area3> children) {
	// this.children = children;
	// }

	public List<DefaultAdParams> getDefaultAdParams() {
		return defaultAdParams;
	}

	public void setDefaultAdParams(List<DefaultAdParams> defaultAdParams) {
		this.defaultAdParams = defaultAdParams;
	}

	@Column(name = "level_1_id")
	private int area1Id;

	public Area1 getArea1() {
		return area1;
	}

	public void setArea1(Area1 area1) {
		this.area1 = area1;
	}

	public int getArea1Id() {
		return area1Id;
	}

	public void setArea1Id(int area1Id) {
		this.area1Id = area1Id;
	}

	@Override
	public String toString() {
		if (descr != null) {
			return descr;
		}
		descr = new StringBuilder(name).append(", ")
				.append(area1.getCountry().toString())
				.toString();
		return descr;
	}

}
