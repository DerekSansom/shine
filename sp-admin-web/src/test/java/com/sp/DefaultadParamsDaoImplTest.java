package com.sp;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shine.app.BoardManager;

import com.sp.board.BoardDao;
import com.sp.entity.loc.Country;
import com.sp.locations.LocationsDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
@Ignore
public class DefaultadParamsDaoImplTest {

	@Autowired
	private BoardManager boardManager;

	@Autowired
	private BoardDao boardDao;

	@Autowired
	private LocationsDao locationsDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(locationsDao);
		Country country = locationsDao.getCountry(1);
		assertNotNull(country);
		assertNotNull(country.getChildren());
		// assertNotNull(country.getDefaultAdParams());
		// assertNotNull(country.getChildren().get(0).getDefaultAdParams());
	}
}
