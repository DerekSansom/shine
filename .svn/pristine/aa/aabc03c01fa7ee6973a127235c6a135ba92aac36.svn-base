package com.sp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import shine.app.BoardManager;

import com.sp.board.BoardDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:test-context.xml" })
public class ContextTest {

	@Autowired
	private BoardManager boardManager;

	@Autowired
	private BoardDao boardDao;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertNotNull(boardManager);
		int boardCount = boardDao.countBoards();
		assertTrue(boardCount > 0);
	}

}
