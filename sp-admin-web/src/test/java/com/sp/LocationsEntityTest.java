package com.sp;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sp.advert.DefaultAdParamsDao;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.BoardLoc;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class LocationsEntityTest {


	@Autowired
	private DefaultAdParamsDao defaultAdParamsDao;

	@Test
	public void test() {
		BoardLoc boardLoc = new BoardLoc();
		boardLoc.setCountryId(1);
		Integer[] idsToExclude = { 1, 2, 3 };
		assertNotNull(defaultAdParamsDao);
		List<DefaultAdParams> params = defaultAdParamsDao.getDefaultAdParams(boardLoc, idsToExclude, 2);
		assertNotNull(params);
		// assertNotNull(country.getDefaultAdParams());
		// assertNotNull(country.getChildren().get(0).getDefaultAdParams());
	}
}
