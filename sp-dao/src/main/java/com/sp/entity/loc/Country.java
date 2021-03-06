package com.sp.entity.loc;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.sp.entity.ad.DefaultAdParams;

@Entity
@Table(name = "loc_country")
public class Country extends Location {

	private String code;

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "countryId")
	private List<DefaultAdParams> defaultAdParams;

	@OneToMany(mappedBy = "parent")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Area1> children;

	public List<Area1> getChildren() {
		return children;
	}

	public void setChildren(List<Area1> children) {
		this.children = children;
	}

	public List<DefaultAdParams> getDefaultAdParams() {
		return defaultAdParams;
	}

	public void setDefaultAdParams(List<DefaultAdParams> defaultAdParams) {
		this.defaultAdParams = defaultAdParams;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	@Override
	public String toString() {
		if (descr == null) {
			return name;
		}
		return descr;
	}

	@Override
	public <P extends Location> P getParent() {
		return null;
	}
}
