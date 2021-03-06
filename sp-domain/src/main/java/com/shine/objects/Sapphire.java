package com.shine.objects;

public class Sapphire extends Stone {

	private Integer creatorId;
	private Integer ownerId;

	public Sapphire(int id) {
		super(id);
	}

	public Sapphire() {
		super();
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
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
		xml.append("/>");
		return xml.toString();
	}

	@Override
	public int getPoints() {
		return 1;
	}

	public Integer getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Integer creatorId) {
		this.creatorId = creatorId;
	}

	@Override
	public int hashCode() {
		final int prime = 59;
		return prime * id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Sapphire))
			return false;
		Sapphire other = (Sapphire) obj;
		return id == other.id;
	}

}
