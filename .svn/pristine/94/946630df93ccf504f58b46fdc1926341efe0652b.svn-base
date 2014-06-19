package com.sp.mobile;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.Model;

import shine.app.PlayerManager;
import shine.dao.exception.ShineException;

import com.shine.objects.Player;
import com.shine.objects.ShineObject;

@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerTest {

	private static final String PLAYER_VIEW_LOC = "userprofile";
	private static final String PLAYER_NOT_FOUND_VIEW_LOC = "playernotfound";

	@Mock
	private PlayerManager playerManager;

	@Mock
	private Model model;

	@InjectMocks
	private PlayerController playerController;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldReturnPlayer() throws ShineException {
		// given
		Player player = new Player();
		List<ShineObject> objects = new ArrayList<ShineObject>();
		objects.add(player);
		when(playerManager.getPlayerWithActivity(anyInt(), eq(0))).thenReturn(objects);

		// when
		String response = playerController.userProfile(1, null, model);

		// then
		assertEquals(PLAYER_VIEW_LOC, response);
		verify(model).addAttribute(eq("player"), any(Player.class));
		verify(playerManager).getPlayerWithActivity(1, 0);

	}

	@Test
	public void shouldReturnnotFoundOnNullList() throws ShineException {
		// given
		when(playerManager.getPlayerWithActivity(anyInt(), eq(0))).thenReturn(null);

		// when
		String response = playerController.userProfile(1, null, model);

		// then
		assertEquals(PLAYER_NOT_FOUND_VIEW_LOC, response);

	}

}
