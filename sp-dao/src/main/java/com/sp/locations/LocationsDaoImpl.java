package com.sp.locations;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import com.sp.entity.loc.Area1;
import com.sp.entity.loc.Area2;
import com.sp.entity.loc.Area3;
import com.sp.entity.loc.BoardLoc;
import com.sp.entity.loc.Country;
import com.sp.entity.loc.Location;


@Repository
public class LocationsDaoImpl implements LocationsDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public <T extends Location> List<T> searchLocs(Class<T> locationSubClass, String nameSearch) {

		CriteriaBuilder criteriaBuilder= entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = criteriaBuilder.createQuery(locationSubClass);

		Root<T> locRoot = cq.from(locationSubClass);
		cq.where(criteriaBuilder.like(locRoot.<String> get("name"), nameSearch));
		TypedQuery<T> typedQuery = entityManager.createQuery(cq);
		
		return (List<T>) typedQuery.getResultList();
	}

	@Override
	public List<Location> getLocations(String name) {

		List<Location> locs = new ArrayList<>();

		List<Area3> areas3 = getLocationsByNameAndClass(name, Area3.class);
		locs.addAll(areas3);

		List<Area2> areas2 = getLocationsByNameAndClass(name, Area2.class);
		locs.addAll(areas2);

		List<Area1> areas1 = getLocationsByNameAndClass(name, Area1.class);
		locs.addAll(areas1);


		List<Country> countries = getLocationsByNameAndClass(name, Country.class);
		locs.addAll(countries);

		return locs;
	}

	@Override
	public <T extends Location> List<T> getLocationsByNameAndClass(String name, Class<T> clazz) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = criteriaBuilder.createQuery(clazz);
		Root<T> locRoot = cq.from(clazz);
		cq.where(criteriaBuilder.like(locRoot.<String> get("name"), name));
		TypedQuery<T> typedQuery = entityManager.createQuery(cq);

		return (List<T>) typedQuery.getResultList();

	}

	@Override
	public Country getCountry(int id) {

		return entityManager.find(Country.class, id);
	}

	@Override
	public Area1 getArea1(int id) {

		return getArea(id, Area1.class);
	}

	private <T> T getArea(int id, Class<T> areaClass) {

		try {
			return entityManager.find(areaClass, id);
		} catch (NoResultException | EmptyResultDataAccessException nre) {
			return null;
		}
	}

	@Override
	public Area2 getArea2(int id) {

		return getArea(id, Area2.class);
	}

	@Override
	public Area3 getArea3(int id) {
		return getArea(id, Area3.class);
	}

	@Override
	public void save(Location loc) {
		entityManager.persist(loc);
	}

	@Override
	public void create(BoardLoc loc) {
		entityManager.persist(loc);
	}

	@Override
	public BoardLoc getBoardLocation(int boardId) {
		return entityManager.find(BoardLoc.class, boardId);
	}


	@Override
	public List<Location> getUnlocatedLocations() {
		List<Location> locs = new ArrayList<Location>();
		locs.addAll(getUnlocatedLocs(Country.class));
		locs.addAll(getUnlocatedLocs(Area1.class));
		locs.addAll(getUnlocatedLocs(Area2.class));
		locs.addAll(getUnlocatedLocs(Area3.class));

		return locs;
	}

	@Override
	public Area1 getArea1(String name, int parentid) {

		TypedQuery<Area1> query = entityManager.createQuery(
				"from Area1 a where a.name=:name and a.country.id=:parentid", Area1.class);
		query.setParameter("name", name);
		query.setParameter("parentid", parentid);
		return singleResultOrNull(query);

	}

	@Override
	public List<Area1> getArea1ByCountry(int countryid) {

		TypedQuery<Area1> query = entityManager.createQuery(
				"from Area1 a where a.country.id=:parentid", Area1.class);
		query.setParameter("parentid", countryid);
		return query.getResultList();

	}

	private <T> T singleResultOrNull(TypedQuery<T> query) {
		try {
			return query.getSingleResult();
		} catch (EmptyResultDataAccessException nre) {
			return null;
		} catch (NoResultException ne) {
			return null;
		}


	}

	@Override
	public Area2 getArea2(String name, int parentid) {

		TypedQuery<Area2> query = entityManager.createQuery(
				"from Area2 a where a.name=:name and a.area1Id=:parentid", Area2.class);
		query.setParameter("name", name);
		query.setParameter("parentid", parentid);
		return singleResultOrNull(query);
	}

	@Override
	public Area3 getArea3(String name, int parentid) {

		TypedQuery<Area3> query = entityManager.createQuery(
				"from Area3 a where a.name=:name and a.area2Id=:parentid", Area3.class);
		query.setParameter("name", name);
		query.setParameter("parentid", parentid);
		return singleResultOrNull(query);

	}

	public Country getCountry(String name, String code) {

		TypedQuery<Country> query = entityManager.createQuery(
				"from Country a where a.name=:name and a.code=:code", Country.class);
		query.setParameter("name", name);
		query.setParameter("code", code);
		return singleResultOrNull(query);
	}

	private <T extends Location> List<T> getUnlocatedLocs(Class<T> locationSubClass) {

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = criteriaBuilder.createQuery(locationSubClass);
		Root<T> locRoot = cq.from(locationSubClass);
		cq.select(locRoot);
		cq.where(
				criteriaBuilder.or(
						criteriaBuilder.isNull(locRoot.get("lat")),
						criteriaBuilder.isNull(locRoot.get("lng"))
						)
				);
		TypedQuery<T> typedQuery = entityManager.createQuery(cq);

		return (List<T>) typedQuery.getResultList();


	}

	@Override
	public <T extends Location> List<T> getLocations(Class<T> type) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();

		CriteriaQuery<T> cq = criteriaBuilder.createQuery(type);
		Root<T> locRoot = cq.from(type);
		cq.select(locRoot);
		TypedQuery<T> typedQuery = entityManager.createQuery(cq);
		return (List<T>) typedQuery.getResultList();
	}

}
