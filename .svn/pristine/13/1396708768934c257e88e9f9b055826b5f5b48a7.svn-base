package shine.app;

import java.util.Comparator;

import com.shine.objects.LocObject;
import com.shine.objects.ShineLocation;

public class BoardDistanceComparator implements Comparator<LocObject> {

	private ShineLocation centrePoint;
	private static final double AVERAGE_RADIUS_OF_EARTH = 6371000;

	public BoardDistanceComparator(ShineLocation centrePoint) {
		this.centrePoint = centrePoint;
	}

	@Override
	public int compare(LocObject o1, LocObject o2) {
		if (o1.getDistanceMeters() == null) {
			setDistance(o1);
		}
		if (o2.getDistanceMeters() == null) {
			setDistance(o2);
		}

		return o1.getDistanceMeters().compareTo(o2.getDistanceMeters());
	}

	private void setDistance(LocObject locObject) {
		int distance = calculateDistance(centrePoint.getLat(), centrePoint.getLng(), locObject.getLat(),
				locObject.getLng());
		locObject.setDistanceMeters(distance);
	}

	public int calculateDistance(double userLat, double userLng, double venueLat, double venueLng) {

		double latDistance = Math.toRadians(userLat - venueLat);
		double lngDistance = Math.toRadians(userLng - venueLng);

		double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
				(Math.cos(Math.toRadians(userLat))) *
				(Math.cos(Math.toRadians(venueLat))) *
				(Math.sin(lngDistance / 2)) *
				(Math.sin(lngDistance / 2));

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));

	}

}
