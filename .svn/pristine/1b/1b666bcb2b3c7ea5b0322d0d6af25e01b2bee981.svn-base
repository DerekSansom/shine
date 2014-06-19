package com.sp.entity.loc;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.sp.entity.ad.DefaultAdParams;

@Entity
@Table(name = "loc_area_1")
public class Area1 extends Location {

	@ManyToOne
	@JoinColumn(name = "countryId", insertable = false, updatable = false)
	private Country country;

	// private int countryId;

	@OneToMany
	@JoinColumn(name = "area1Id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DefaultAdParams> defaultAdParams;

	// @OneToMany(fetch = FetchType.EAGER)
	// @JoinColumn(name = "area1Id")
	// private List<Area2> children;
	//
	// public List<Area2> getChildren() {
	// return children;
	// }
	//
	// public void setChildren(List<Area2> children) {
	// this.children = children;
	// }

	public List<DefaultAdParams> getDefaultAdParams() {
		return defaultAdParams;
	}

	public void setDefaultAdParams(List<DefaultAdParams> defaultAdParams) {
		this.defaultAdParams = defaultAdParams;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public int getCountryId() {
		return country.getId();
	}

	@Override
	public String toString() {
		if (descr != null) {
			return descr;
		}

		descr = new StringBuilder(name).append(", ")
				.append(country.toString())
				.toString();
		return descr;
	}
}
