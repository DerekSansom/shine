package com.sp.entity.loc;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.sp.entity.ad.DefaultAdParams;

@Entity
@Table(name = "loc_area_2")
public class Area2 extends Location {

	@ManyToOne
	@JoinColumn(name = "level_1_id", insertable = false, updatable = false)
	private Area1 area1;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "area2Id")
	private List<DefaultAdParams> defaultAdParams;

	@OneToMany(mappedBy = "parent")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Area3> children;

	public List<Area3> getChildren() {
		return children;
	}

	public void setChildren(List<Area3> children) {
		this.children = children;
	}

	public List<DefaultAdParams> getDefaultAdParams() {
		return defaultAdParams;
	}

	public void setDefaultAdParams(List<DefaultAdParams> defaultAdParams) {
		this.defaultAdParams = defaultAdParams;
	}

	// public Area1 getArea1() {
	// return area1;
	// }
	//
	// public void setArea1(Area1 area1) {
	// this.area1 = area1;
	// }

	public Area1 getParent() {
		return area1;
	}

	public void setParent(Area1 area1) {
		this.area1 = area1;
	}

	@Override
	public String toString() {
		if (descr != null) {
			return descr;
		}
		descr = new StringBuilder(name).append(", ")
				.append(area1.getParent().toString())
				.toString();
		return descr;
	}

}