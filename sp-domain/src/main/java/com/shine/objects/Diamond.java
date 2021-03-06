package com.shine.objects;

public class Diamond extends Stone {

	public Diamond(int id) {
		super(id);
	}

	public Diamond() {
		super();
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("Id=");
		buf.append(id).append(",")
				.append(", lat=").append(getLat()).append(", lng=").append(getLng());
		return buf.toString();

	}

	// @Override
	public String toXml() {
		StringBuilder xml = getStartXml();
		xml.append(formatIntAttribute("pts", points));
		xml.append("/>");
		return xml.toString();
	}

	@Override
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		final int prime = 61;
		return prime * id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Diamond))
			return false;
		Diamond other = (Diamond) obj;
		return id == other.id;
	}

}
