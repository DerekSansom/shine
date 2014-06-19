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
@Table(name = "loc_area_3")
public class Area3 extends Location {

	@Transient
	private Area2 area2;

	@Column(name = "level_2_id")
	private int area2Id;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "area3Id")
	private List<DefaultAdParams> defaultAdParams;

	public List<DefaultAdParams> getDefaultAdParams() {
		return defaultAdParams;
	}

	public void setDefaultAdParams(List<DefaultAdParams> defaultAdParams) {
		this.defaultAdParams = defaultAdParams;
	}

	public Area2 getArea2() {
		return area2;
	}

	public void setArea2(Area2 area2) {
		this.area2 = area2;
	}

	public int getArea2Id() {
		return area2Id;
	}

	public void setArea2Id(int area2Id) {
		this.area2Id = area2Id;
	}

	@Override
	public String toString() {
		if (getDescr() != null) {
			return getDescr();
		}
		descr = new StringBuilder(name).append(", ").append(area2.toString()).append(", ")
				.append(area2.getArea1().getCountry().toString())
				.toString();
		return descr;
	}

}
