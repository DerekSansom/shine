package com.sp.entity.loc;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import com.sp.entity.ad.DefaultAdParams;

@MappedSuperclass
public abstract class Location
// <Parent extends Location>
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	private Double lat;
	private Double lng;

	protected String descr;

	protected String name;

	@Transient
	protected String type;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getType() {
		if(type == null){
			type = getClass().getSimpleName().toLowerCase();
		}
		return type;
	}

	public abstract List<DefaultAdParams> getDefaultAdParams();

	public abstract List<? extends Location> getChildren();

	public abstract <P extends Location> P getParent();

}
