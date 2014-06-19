package com.sp.locations;

import java.util.List;

import com.sp.entity.loc.Area1;
import com.sp.entity.loc.Area2;
import com.sp.entity.loc.Area3;
import com.sp.entity.loc.BoardLoc;
import com.sp.entity.loc.Country;
import com.sp.entity.loc.Location;


public interface LocationsDao {

	BoardLoc getBoardLocation(int boardId);

	void create(BoardLoc loc);

	void save(Location loc);

	Area3 getArea3(int id);

	Area2 getArea2(int id);

	Area1 getArea1(int id);

	Country getCountry(int id);

	<T extends Location> List<T> getLocationsByNameAndClass(String name, Class<T> clazz);

	List<Location> getLocations(String str);

	<T extends Location> List<T> searchLocs(Class<T> t, String nameSearch);

	List<Location> getUnlocatedLocations();

	Country getCountry(String name, String code);

	Area3 getArea3(String name, int area2id);

	Area2 getArea2(String name, int area1id);

	Area1 getArea1(String name, int countryId);

	<T extends Location> List<T> getLocations(Class<T> type);

	List<Area1> getArea1ByCountry(int countryid);
}
