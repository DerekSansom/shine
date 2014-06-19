package com.shine.objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//import com.google.android.maps.OverlayItem;
@JsonIgnoreProperties({ "overlayItem", "startXml" })
public abstract class LocObject extends ShineObject {

	Object overlayItem;
	Double lat, lng;
	private Integer distanceMeters;

	protected LocObject(int id) {
		super(id);
	}

	protected LocObject() {
	}

	public void setLocation(ShineLocation loc) {
		if (loc != null) {
			this.lat = loc.getLat();
			this.lng = loc.getLng();
		} else {
			this.lat = null;
			this.lng = null;

		}
	}

	public ShineLocation getLocation() {
		if (lat == null || lng == null) {
			return null;
		}
		return new ShineLocation(lat, lng);
	}

	public Double getLat() {
		return lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setOverlayItem(Object oi) {
		this.overlayItem = oi;

	}

	public Object getOverlayItem() {
		return overlayItem;
	}

	public StringBuilder getStartXml() {
		StringBuilder xml = new StringBuilder("<object t=\"")
				.append(getClass().getSimpleName())
				.append("\" ")
				.append(formatDoubleAttribute("lat", lat))
				.append(formatDoubleAttribute("lng", lng))
				.append(formatIntAttribute("id", id));

		return xml;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	@Override
	public int hashCode() {
		final int prime = 57;
		int result = 1;
		result = prime * result + ((lat == null) ? 0 : lat.hashCode());
		result = prime * result + ((lng == null) ? 0 : lng.hashCode());
		result = prime * result + ((overlayItem == null) ? 0 : overlayItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocObject other = (LocObject) obj;
		if (lat == null) {
			if (other.lat != null)
				return false;
		} else if (!lat.equals(other.lat))
			return false;
		if (lng == null) {
			if (other.lng != null)
				return false;
		} else if (!lng.equals(other.lng))
			return false;
		if (overlayItem == null) {
			if (other.overlayItem != null)
				return false;
		} else if (!overlayItem.equals(other.overlayItem))
			return false;
		return true;
	}

	public Integer getDistanceMeters() {
		return distanceMeters;
	}

	public void setDistanceMeters(Integer distanceMeters) {
		this.distanceMeters = distanceMeters;
	}

}
