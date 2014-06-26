package com.sp;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shine.app.BoardManager;

import com.sp.board.BoardDao;
import com.sp.entity.loc.Country;
import com.sp.locations.LocationsDao;
import com.sp.properties.PropertyDao;
import com.sp.properties.PropertyEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class LocationsEntityTest {

	@Autowired
	private BoardManager boardManager;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private LocationsDao locationsDao;
	@Autowired
	private PropertyDao propertyDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(locationsDao);
		Country country = locationsDao.getCountry(1);
		assertNotNull(country);
		assertNotNull(country.getChildren());
		assertNotNull(country.getDefaultAdParams());
		assertNotNull(country.getChildren().get(0).getDefaultAdParams());
	}

	@Test
	public void testProperties() {
		assertNotNull(propertyDao);
		List<PropertyEntity> props = propertyDao.getAllProperties();
		assertNotNull(props);
		assertNotNull(props.get(0));
	}

}
