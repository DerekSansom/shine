package com.sp;

import static org.junit.Assert.assertNotNull;

<<<<<<< HEAD
import java.util.List;

=======
import org.junit.Before;
>>>>>>> 482eb53fad7036601388e8d65c7f5a704415ab05
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

<<<<<<< HEAD
import com.sp.advert.DefaultAdParamsDao;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.BoardLoc;
=======
import shine.app.BoardManager;

import com.sp.board.BoardDao;
import com.sp.entity.loc.Country;
import com.sp.entity.loc.Location;
import com.sp.locations.LocationsDao;
>>>>>>> 482eb53fad7036601388e8d65c7f5a704415ab05

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class LocationsEntityTest {


	@Autowired
	private DefaultAdParamsDao defaultAdParamsDao;

	@Test
	public void test() {
<<<<<<< HEAD
		BoardLoc boardLoc = new BoardLoc();
		boardLoc.setCountryId(1);
		Integer[] idsToExclude = { 1, 2, 3 };
		assertNotNull(defaultAdParamsDao);
		List<DefaultAdParams> params = defaultAdParamsDao.getDefaultAdParams(boardLoc, idsToExclude, 2);
		assertNotNull(params);
		// assertNotNull(country.getDefaultAdParams());
		// assertNotNull(country.getChildren().get(0).getDefaultAdParams());
=======
		assertNotNull(locationsDao);
		Country country = locationsDao.getCountry(1);
		assertNotNull(country);
		assertNotNull(country.getChildren());
		testLocation(country.getChildren().get(0));
	}

	private void testLocation(Location child) {
		assertNotNull(child);
		System.out.println(child.getName());
		if (!child.getType().equals("area3")) {
			assertNotNull(child.getChildren());
			testLocation(child.getChildren().get(0));
		}
>>>>>>> 482eb53fad7036601388e8d65c7f5a704415ab05
	}
}
