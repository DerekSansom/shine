package com.sp.mobile;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;

import shine.app.BoardManager;
import shine.dao.exception.ShineException;

import com.shine.boards.Advert;
import com.shine.boards.BoardCategory;
import com.shine.boards.Notice;
import com.shine.boards.NoticeBoard;
import com.sp.mobile.board.BoardController;
import com.sp.security.StreetPinUserDetails;

@RunWith(MockitoJUnitRunner.class)
public class BoardControllerTest {

	private static final String BOARD_VIEW_LOC = "board";
	private static final String BOARD_NOT_FOUND_VIEW_LOC = "boardnotfound";

	@Mock
	private BoardManager boardManager;

	@Mock
	private Model model;

	@Mock
	private Authentication principal;

	@Mock
	private StreetPinUserDetails userdetails;

	@InjectMocks
	private BoardController boardController;

	@Mock
	private NoticeBoard board;

	@Before
	public void setUp() throws Exception {

		when(board.getCategory()).thenReturn(BoardCategory.DFLT);

	}

	@Test
	public void shouldReturnAnonBoard() throws ShineException {
		// given
		when(boardManager.getAnonNoticeBoard(1)).thenReturn(board);

		// when
		String response = boardController.getBoard(1, null, model);

		// then
		assertEquals(BOARD_VIEW_LOC, response);
		verify(model).addAttribute(BOARD_VIEW_LOC, board);
		verify(boardManager).getAnonNoticeBoard(1);

	}

	@Test
	public void shouldReturnBoardForUser() throws ShineException {
		// given
		int userId = 2;
		when(boardManager.getNoticeBoard(1, userId)).thenReturn(board);
		when(principal.getPrincipal()).thenReturn(userdetails);
		when(userdetails.getId()).thenReturn(userId);

		// when
		String response = boardController.getBoard(1, principal, model);

		// then
		assertEquals(BOARD_VIEW_LOC, response);
		verify(model).addAttribute(BOARD_VIEW_LOC, board);
		verify(boardManager).getNoticeBoard(1, userId);

	}

	@Test
	public void shouldOnlyReturn3PostsAnd3Ads() throws ShineException {
		// given
		when(board.getNotices()).thenReturn(Arrays.asList(new Notice(), new Notice(), new Notice(), new Notice()));
		when(board.getAds()).thenReturn(Arrays.asList(new Advert(), new Advert(), new Advert(), new Advert()));
		when(boardManager.getAnonNoticeBoard(1)).thenReturn(board);

		// when
		String response = boardController.getBoard(1, null, model);

		// then
		assertEquals(BOARD_VIEW_LOC, response);
		verify(model).addAttribute(BOARD_VIEW_LOC, board);
		verify(boardManager).getAnonNoticeBoard(1);

		ArgumentCaptor<List> noticesArg = ArgumentCaptor.forClass(List.class);
		verify(board).setNotices(noticesArg.capture());
		assertEquals("Should set notices to 3", 3, noticesArg.getValue().size());

		ArgumentCaptor<List> adsArg = ArgumentCaptor.forClass(List.class);
		verify(board).setAds(adsArg.capture());
		assertEquals("Should set ads to 3", 3, adsArg.getValue().size());

	}

	@Test
	public void shouldReturnAllAds() throws ShineException {

		// given
		when(board.getNotices()).thenReturn(Arrays.asList(new Notice(), new Notice(), new Notice(), new Notice()));
		when(board.getAds()).thenReturn(Arrays.asList(new Advert(), new Advert(), new Advert(), new Advert()));
		when(boardManager.getAnonNoticeBoard(1)).thenReturn(board);

		// when
		String response = boardController.getBoardWithAds(1, null, model);

		// then
		assertEquals(BOARD_VIEW_LOC, response);
		verify(model).addAttribute(BOARD_VIEW_LOC, board);
		verify(boardManager).getAnonNoticeBoard(1);

		ArgumentCaptor<List> noticesArg = ArgumentCaptor.forClass(List.class);
		verify(board).setNotices(noticesArg.capture());
		assertTrue("Should set notices to empty", CollectionUtils.isEmpty(noticesArg.getValue()));
		// shouldn't reduce ads list
		verify(board, never()).setAds(anyListOf(Advert.class));

	}

	@Test
	public void shouldReturnAllNotices() throws ShineException {

		// given
		when(board.getNotices()).thenReturn(Arrays.asList(new Notice(), new Notice(), new Notice(), new Notice()));
		when(board.getAds()).thenReturn(Arrays.asList(new Advert(), new Advert(), new Advert(), new Advert()));
		when(boardManager.getAnonNoticeBoard(1)).thenReturn(board);

		// when
		String response = boardController.getBoardWithPosts(1, null, model);

		// then
		assertEquals(BOARD_VIEW_LOC, response);
		verify(model).addAttribute(BOARD_VIEW_LOC, board);
		verify(boardManager).getAnonNoticeBoard(1);

		// shouldn't reduce ads list
		verify(board, never()).setNotices(anyListOf(Notice.class));
		// should still leave 3 notices
		ArgumentCaptor<List> adsArg = ArgumentCaptor.forClass(List.class);
		verify(board).setAds(adsArg.capture());
		assertEquals("Should set ads to 3", 3, adsArg.getValue().size());

	}

	@Test
	public void shouldReturnNotFound() throws ShineException {
		// given
		when(boardManager.getAnonNoticeBoard(1)).thenThrow(ShineException.class);

		// when
		String response = boardController.getBoard(1, null, model);

		// then
		assertEquals(BOARD_NOT_FOUND_VIEW_LOC, response);
		verify(boardManager).getAnonNoticeBoard(1);

	}

}
