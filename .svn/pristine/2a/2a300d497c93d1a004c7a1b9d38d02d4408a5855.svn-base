package com.shine.objects;

public class Ruby extends Stone {

	private String clue;

	Integer childId, parentId;

	public Integer getChildId() {
		return childId;
	}

	public void setChildId(Integer childId) {
		this.childId = childId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer("Id=");
		buf.append(id).append(",")
				.append(" parent=").append(parentId)
				.append(", child=").append(childId)
				.append(", lat=").append(getLat())
				.append(", lng=").append(getLng())
				.append(", points=").append(getPoints());
		return buf.toString();

	}

	// @Override
	public String toXml() {
		StringBuilder xml = getStartXml();
		xml.append(" points=\"");
		xml.append(points);
		xml.append("\">");
		xml.append("<clue>");
		xml.append(clue);
		xml.append("</clue>");
		xml.append("</object>");

		return xml.toString();
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getClue() {
		return clue;
	}

	public void setClue(String clue) {
		this.clue = clue;
	}

	@Override
	public int hashCode() {
		final int prime = 57;
		return prime * id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Ruby))
			return false;
		Ruby other = (Ruby) obj;
		return id == other.id;
	}

}
