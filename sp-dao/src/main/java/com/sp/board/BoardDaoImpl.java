package com.sp.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.joda.time.DateTime;
import org.springframework.stereotype.Repository;

import shine.dao.ads.ReportBoard;

import com.shine.objects.ShineLocation;
import com.sp.dao.util.DaoUtils;
import com.sp.entity.NoticeBoardEntity;
import com.sp.entity.ad.ExcludedCategory;
import com.sp.entity.loc.Area1;
import com.sp.entity.loc.Area2;
import com.sp.entity.loc.Area3;
import com.sp.entity.loc.BoardLoc;
import com.sp.entity.loc.Country;
import com.sp.entity.loc.Location;

@Repository
public class BoardDaoImpl implements BoardDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public NoticeBoardEntity getNoticeBoard(int id) {

		NoticeBoardEntity board = entityManager.find(NoticeBoardEntity.class, id);
		return board;
	}

	@Override
	public List<NoticeBoardEntity> getBoardsByCreator(int creatorId, Date since) {
		TypedQuery<NoticeBoardEntity> query = null;
		if (since == null) {
			query = entityManager.createQuery(
					"from NoticeBoardEntity nbe where nbe.creatorId = :id", NoticeBoardEntity.class);

		} else {
			query = entityManager.createQuery(
					"from NoticeBoardEntity nbe where nbe.creatorId = :id and nbe.created > :since",
					NoticeBoardEntity.class);
			query.setParameter("since", since);

		}
		query.setParameter("id", creatorId);

		List<NoticeBoardEntity> list = query.getResultList();
		return list;
	}

	@Override
	public List<NoticeBoardEntity> getBoardsByPlayer(int playerId, Date since) {
		TypedQuery<NoticeBoardEntity> query = null;
		if (since == null) {
			query = entityManager.createQuery(
					"from NoticeBoardEntity nbe where nbe.player.id = :id", NoticeBoardEntity.class);

		} else {
			query = entityManager.createQuery(
					"from NoticeBoardEntity nbe where nbe.player.id = :id and nbe.created > :since",
					NoticeBoardEntity.class);
			query.setParameter("since", since);

		}
		query.setParameter("id", playerId);

		List<NoticeBoardEntity> list = query.getResultList();
		return list;
	}

	@Override
	public List<NoticeBoardEntity> getBoardsByName(String name) {
		TypedQuery<NoticeBoardEntity> query = entityManager.createQuery(
				"from NoticeBoardEntity nbe where nbe.name = :name", NoticeBoardEntity.class);
		query.setParameter("name", name);

		List<NoticeBoardEntity> list = query.getResultList();
		return list;
	}

	@Override
	public List<ExcludedCategory> getExcludedAdCategories(int boardId) {

		TypedQuery<ExcludedCategory> query = entityManager.createQuery(
				"from ExcludedCategory ec where ec.id.boardId = :id", ExcludedCategory.class);
		query.setParameter("id", boardId);

		List<ExcludedCategory> list = query.getResultList();
		return list;

	}

	@Override
	public List<NoticeBoardEntity> getBoardsSearch(String str) {

		String queryStr = "from NoticeBoardEntity where name like '%:searchstr%' or locationName  like '%:searchstr%'";

		TypedQuery<NoticeBoardEntity> query = entityManager.createQuery(
				queryStr, NoticeBoardEntity.class);
		query.setParameter("searchstr", str);

		List<NoticeBoardEntity> list = query.getResultList();
		return list;

	}

	@Override
	public List<Integer> getBoardIdsWithNoLocation() {

		String query = "select noticeboards.id from noticeboards where id not in (select boardid from loc_board)";

		Query sqlq = entityManager.createNativeQuery(query);
		List<Integer> ids = sqlq.getResultList();
		return ids;
	}

	@Override
	public List<NoticeBoardEntity> getBoardsByLocation(Location details) {

		List<Integer> ids = getBoardIdsByLocation(details);
		if (ids.isEmpty()) {
			return new ArrayList<NoticeBoardEntity>();
		}
		String queryStr = "from NoticeBoardEntity where id in (:boardids)";

		TypedQuery<NoticeBoardEntity> query = entityManager.createQuery(
				queryStr, NoticeBoardEntity.class);
		query.setParameter("boardids", ids);

		List<NoticeBoardEntity> list = query.getResultList();
		return list;
	}

	private List<Integer> getBoardIdsByLocation(Location details) {

		String query = "select boardid from loc_board where ";

		if (details instanceof Area3) {
			query = "select boardid from loc_board where area3id=" + details.getId();
		} else if (details instanceof Area2) {
			query = "select boardid from loc_board where area2id=" + details.getId();
		} else if (details instanceof Area1) {
			query = "select boardid from loc_board where area1id=" + details.getId();
		} else if (details instanceof Country) {
			query = "select boardid from loc_board where countryid=" + details.getId();
		} else {
			throw new IllegalArgumentException("unknown type of location: " + details.getClass());
		}

		Query sqlq = entityManager.createNativeQuery(query, Integer.class);
		List<Integer> ids = sqlq.getResultList();
		return ids;
	}

	@Override
	public List<Integer> getBoardIdsWithCountryOnlyLocations() {
		String query =
				"select boardid from loc_board where area3id is null and area2id is null and area1id is null";
		Query sqlq = entityManager.createNativeQuery(query);
		List<Integer> ids = sqlq.getResultList();
		return ids;

	}

	private List<Integer> getBoardLocIds() {
		String query =
				"select boardid from loc_board";
		Query sqlq = entityManager.createNativeQuery(query);
		List<Integer> ids = sqlq.getResultList();
		return ids;

	}

	@Override
	public ReportBoard getBoardReport(int id) {

		ReportBoard report = entityManager.find(ReportBoard.class, id);
		return report;
	}

	@Override
	public int saveBoardReport(ReportBoard reportBoard) {

		entityManager.persist(reportBoard);
		return reportBoard.getBoardId();
	}


	@Override
	public void saveBoardAndReport(ReportBoard report, NoticeBoardEntity board) {

		entityManager.persist(report);
		entityManager.persist(board);

	}

	@Override
	public BoardLoc getBoardLocation(int boardid) {
		String queryStr = "from BoardLoc where boardId =:boardid";

		TypedQuery<BoardLoc> query = entityManager.createQuery(queryStr, BoardLoc.class);
		query.setParameter("boardid", boardid);
		try{
			return query.getSingleResult();
		} catch (Exception nre) {
			return null;
		}
	}

	public List<NoticeBoardEntity> getNearBoards(ShineLocation loc, long secondssince, double radius) {

		
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<NoticeBoardEntity> cq = criteriaBuilder.createQuery(NoticeBoardEntity.class);

		Root<NoticeBoardEntity> locRoot = cq.from(NoticeBoardEntity.class);

		List<Predicate> predicates = new ArrayList<>();
		predicates.add(criteriaBuilder.isTrue(locRoot.<Boolean> get("active")));
		predicates.add(criteriaBuilder.isNull(locRoot.get("suspended")));

		if (secondssince > 0) {
			DateTime since = DateTime.now().minusSeconds((int) secondssince);
			predicates.add(criteriaBuilder.greaterThan(locRoot.<Date> get("created"), since.toDate()));
		}
		
		if (loc != null && radius > 0) {
			predicates.add(DaoUtils.createLocationCriterion(loc, radius, criteriaBuilder, locRoot));
		}

		Predicate conjunction = DaoUtils.addAllPredicates(predicates, criteriaBuilder);
		cq.where(conjunction);


		cq.orderBy(criteriaBuilder.desc(locRoot.get("created")));


		TypedQuery<NoticeBoardEntity> typedQuery = entityManager.createQuery(cq);
		return typedQuery.getResultList();

	}

	@Override
	public List<NoticeBoardEntity> getAllBoards(int start, int count) {

		String queryStr = "from NoticeBoardEntity";

		TypedQuery<NoticeBoardEntity> query = entityManager.createQuery(queryStr, NoticeBoardEntity.class);
		query.setMaxResults(count);
		query.setFirstResult(start);

		List<NoticeBoardEntity> list = query.getResultList();
		return list;
	}

	@Override
	public int countBoards() {
		return ((Long) entityManager.createQuery("select count(*) from NoticeBoardEntity").getSingleResult()).intValue();
	}

	@Override
	public List<NoticeBoardEntity> getBoardsWithNoLocation() {

		TypedQuery<NoticeBoardEntity> query = null;
		query = entityManager.createQuery(
				"from NoticeBoardEntity nbe where nbe.id not in (:ids) ",
				NoticeBoardEntity.class);
		List<Integer> ids = getBoardLocIds();

		query.setParameter("ids", ids);

		List<NoticeBoardEntity> list = query.getResultList();

		return list;

		// CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		// CriteriaQuery<NoticeBoardEntity> query =
		// criteriaBuilder.createQuery(NoticeBoardEntity.class);
		// Root<NoticeBoardEntity> locRoot =
		// query.from(NoticeBoardEntity.class);
		// query.where(criteriaBuilder.isNull(locRoot.get("boardLoc")));
		//
		// TypedQuery<NoticeBoardEntity> typedQuery =
		// entityManager.createQuery(query);
		// return typedQuery.getResultList();

	}

}
