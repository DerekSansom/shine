package com.sp.locations;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.slf4j.Logger;

import shine.dao.exception.ShineException;

import com.sp.board.BoardDao;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.loc.BoardLoc;
import com.sp.entity.loc.Country;

@RunWith(MockitoJUnitRunner.class)
public class BoardLocationsMapperTest {

	private static final String NO_RESULTS = "<GeocodeResponse><status>ZERO_RESULTS</status></GeocodeResponse>";

	@Mock
	private BoardDao boardDao;

	@Mock
	private NoticeBoardEntity board;

	@Mock
	private LocationsDao locationsDao;

	@Mock
	private UrlUtils urlUtils;

	@Mock
	private Logger log;

	@InjectMocks
	private BoardLocationsMapper mapper;

	private int boardId = 1;

	@Before
	public void setUp() throws Exception {
		when(boardDao.getNoticeBoard(boardId)).thenReturn(board);
	}

	@Test
	public void testNoResults() throws ShineException {
		when(urlUtils.getInputStream(anyString())).thenReturn(new ByteArrayInputStream(NO_RESULTS.getBytes()));

		mapper.updateBoardLocation(boardId, log);
		
		verify(log).error("boardid 1 : ZERO_RESULTS : http://maps.googleapis.com/maps/api/geocode/xml?sensor=false&latlng=0.0,0.0");
	}

	@Test
	public void testEastSussex() throws ShineException {
		when(board.getLat()).thenReturn(0.0d);
		when(board.getLat()).thenReturn(51d);
		when(urlUtils.getInputStream(anyString())).thenReturn(new ByteArrayInputStream(GoogleResultStrings.FIFTY_1_ZERO.getBytes()));

		Country country = mock(Country.class);
		when(country.getId()).thenReturn(35);

		when(locationsDao.getCountry("United Kingdom", "GB")).thenReturn(country);

		mapper.updateBoardLocation(boardId, log);

		ArgumentCaptor<BoardLoc> arg = ArgumentCaptor.forClass(BoardLoc.class);
		verify(locationsDao).create(arg.capture());
		BoardLoc result = arg.getValue();
		assertEquals(35, result.getCountryId());

		verify(locationsDao).getCountry("United Kingdom", "GB");
		verify(locationsDao).getArea1("England", 35);
		verify(locationsDao).getArea2("East Sussex", 0);
		verify(log, never()).error(anyString());
	}

}
