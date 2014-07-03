package com.sp.entity.loc;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.sp.entity.ad.DefaultAdParams;

@Entity
@Table(name = "loc_area_3")
public class Area3 extends Location {

	@ManyToOne
	@JoinColumn(name = "level_2_id", insertable = false, updatable = false)
	private Area2 parent;

	@OneToMany
	@JoinColumn(name = "area3Id")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<DefaultAdParams> defaultAdParams;

	public List<DefaultAdParams> getDefaultAdParams() {
		return defaultAdParams;
	}

	public void setDefaultAdParams(List<DefaultAdParams> defaultAdParams) {
		this.defaultAdParams = defaultAdParams;
	}

	public Area2 getParent() {
		return parent;
	}

	public void setParent(Area2 parent) {
		this.parent = parent;
	}

	@Override
	public String toString() {
		if (getDescr() != null) {
			return getDescr();
		}
		descr = new StringBuilder(name).append(", ").append(parent.toString())
				.append(", ").append(parent.getParent().getParent().toString())
				.toString();
		return descr;
	}

	@Override
	@Transient
	public List<? extends Location> getChildren() {
		return null;
	}

}
