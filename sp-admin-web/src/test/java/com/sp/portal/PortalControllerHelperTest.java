package com.sp.portal;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "servlet-context.xml" })
@Ignore
public class PortalControllerHelperTest {

	@Autowired
	private PortalControllerHelper portalControllerHelper;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(portalControllerHelper);
	}
}


