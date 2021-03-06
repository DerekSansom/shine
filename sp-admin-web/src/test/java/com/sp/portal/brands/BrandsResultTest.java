package com.sp.portal.brands;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.shine.boards.CorpBrand;

@RunWith(MockitoJUnitRunner.class)
public class BrandsResultTest {

	@Test
	public void testSingleBrand() {
		CorpBrand user1b1 = new CorpBrand(1,1,"User1 Brand 1");
		final int START = 0;
		final int BRAND_COUNT = 1;
		final int BRAND_TOTAL = 1;
		
		ArrayList<CorpBrand> brands = new ArrayList<CorpBrand>(1);
		brands.add(user1b1);
		
		BrandsResult result = new BrandsResult(brands,START, BRAND_COUNT, BRAND_TOTAL);
				
		assertNotNull(result);
		assertTrue("Array of brands should be the same",brands.equals(result.getBrands()));
		assertTrue("Start values should be the same",result.getStart() == START);
		assertTrue("Board Count values should be the same",result.getBrandCount()== BRAND_COUNT);
		assertTrue("Board Total values should be the same",result.getTotalBrands() == BRAND_TOTAL);
	}

}
