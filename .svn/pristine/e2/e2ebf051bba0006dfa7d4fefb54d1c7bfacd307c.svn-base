package com.sp.advert;

import java.util.List;

import com.sp.entity.ad.AdvertEntity;
import com.sp.entity.loc.BoardLoc;

public interface AdvertDao {

	List<AdvertEntity> getPaidAdvertsByLocation(BoardLoc boardLocation, Integer[] excludedCats);

	int saveOrUpdateAd(AdvertEntity ad);

	AdvertEntity getAd(int adid);

	int createAd(AdvertEntity dtoToEntity);

	List<AdvertEntity> advertSearch(int boardid, String searchString, int maxResults);

	List<AdvertEntity> getAdvertsByIds(List<Integer> savedAdIds);

	List<AdvertEntity> getAdvertsByIds(Integer[] adIds, Integer[] excludeCats);

	List<AdvertEntity> getAllAdverts(int start, int count);

	List<AdvertEntity> getAdvertsByBrandIds(List<Integer> brandIds);

}
