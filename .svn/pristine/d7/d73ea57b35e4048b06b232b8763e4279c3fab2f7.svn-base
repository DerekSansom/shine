package com.sp.board;

import java.util.Date;
import java.util.List;

import shine.dao.ads.ReportBoard;

import com.shine.objects.ShineLocation;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.ad.ExcludedCategory;
import com.sp.entity.loc.BoardLoc;
import com.sp.entity.loc.Location;

public interface BoardDao {

	void saveBoardAndReport(ReportBoard report, NoticeBoardEntity board);

	int saveBoardReport(ReportBoard reportBoard);

	ReportBoard getBoardReport(int id);

	List<Integer> getBoardIdsWithCountryOnlyLocations();

	List<NoticeBoardEntity> getBoardsByLocation(Location details);

	List<Integer> getBoardIdsWithNoLocation();

	List<NoticeBoardEntity> getBoardsSearch(String str);

	List<ExcludedCategory> getExcludedAdCategories(int boardId);

	NoticeBoardEntity getNoticeBoard(int id);

	List<NoticeBoardEntity> getBoardsByCreator(int creatorId, Date since);

	List<NoticeBoardEntity> getBoardsByPlayer(int creatorId, Date since);

	BoardLoc getBoardLocation(int id);

	List<NoticeBoardEntity> getNearBoards(ShineLocation viewloc, long secondsSince, double radius);

	List<NoticeBoardEntity> getBoardsByName(String name);

	List<NoticeBoardEntity> getAllBoards(int start, int count);

	int countBoards();

	List<NoticeBoardEntity> getBoardsWithNoLocation();

}
