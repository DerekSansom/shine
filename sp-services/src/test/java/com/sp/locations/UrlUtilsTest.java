package com.sp.locations;

import org.junit.Before;
import org.junit.Test;

public class UrlUtilsTest {

	private String url = "http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&latlng=0.0,0.0";
	private String london = "http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&latlng=51.0,0.0";

	private UrlUtils urlUtils = new UrlUtils();

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testNoResults() {
		urlUtils.getInputStream(url, true);

	}

	@Test
	public void testLondon() {
		urlUtils.getInputStream(london, true);

	}
}