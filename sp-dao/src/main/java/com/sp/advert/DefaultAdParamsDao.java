package com.sp.advert;

import java.util.List;

import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.BoardLoc;


public interface DefaultAdParamsDao {

	List<DefaultAdParams> getDefaultAdParams(BoardLoc boardLocation, Integer[] adIdsToexclude, int maxNumber);

	List<DefaultAdParams> getNullLocationDefaultAdParams(int maxNumber);

	void removeCountryDefaultAds(int countryId, int adId);

	void save(DefaultAdParams newParams);

	void delete(DefaultAdParams toDelete);

}
