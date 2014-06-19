package shine.dao.hib;

import com.shine.objects.ShineLocation;

public class TrackObj {

	int userId;
	double lat, lng;
	public TrackObj(int id, ShineLocation loc) {
		this.userId = id;
		if(loc != null){
			lat = loc.getLat();
			lng = loc.getLng();
		}
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getLat() {
		return lat;
	}
	public void setLat(double lat) {
		this.lat = lat;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}

}
