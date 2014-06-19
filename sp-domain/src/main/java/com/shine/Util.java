package com.shine;

import java.text.SimpleDateFormat;

//import android.location.Location;

public final class Util {

	public static final double x6 = 1000000;
	private static final double earthRadius = 6371000;

	public static final SimpleDateFormat datadf = new SimpleDateFormat(
			"yyyy-M-d-k-m");

	public static final SimpleDateFormat datadfWithTz = new SimpleDateFormat(
			"yyyy-M-d-k-m Z");

	public static final SimpleDateFormat viewdf = new SimpleDateFormat(
			"dd-MMM-yy k:mm");

	public static double calculateDistance(double latVal1, double lonVal1,
			double latVal2, double lonVal2

	) {

		double dist = 0.0;
		double deltaLat = Math.toRadians(latVal2 - latVal1);
		double deltaLon = Math.toRadians(lonVal2 - lonVal1);
		latVal1 = Math.toRadians(latVal1);
		latVal2 = Math.toRadians(latVal2);
		lonVal1 = Math.toRadians(lonVal1);
		lonVal2 = Math.toRadians(lonVal2);
		double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
				+ Math.cos(latVal1) * Math.cos(latVal2)
				* Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);

		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		dist = earthRadius * c;

		return dist;
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		return str.trim().length() == 0;
	}

	public static boolean isNotEmpty(CharSequence str) {
		if (str == null) {
			return false;
		}
		return str.length() > 0;
	}

}
