package shine.app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import shine.app.mapper.AdMapper;
import shine.app.utils.ShineProperties;

import com.shine.boards.Advert;
import com.sp.advert.AdvertDao;
import com.sp.advert.DefaultAdParamsDao;
import com.sp.entity.ad.AdvertEntity;
import com.sp.entity.ad.DefaultAdParams;
import com.sp.entity.loc.BoardLoc;

@Component
public class AdServer {

	private static int ADS_MIN_TO_SERVE = ShineProperties.getIntProperty(ShineProperties.MIN_ADS_TO_SERVE, 3);

	@Autowired
	private AdMapper adMapper;

	@Autowired
	private AdvertDao adDao;

	@Autowired
	private DefaultAdParamsDao defaultAdParamsDao;

	public List<Advert> getAds(BoardLoc boardLocation, Integer[] excludedCats, Integer[] userPrefCats) {

		List<AdvertEntity> ads = adDao.getPaidAdvertsByLocation(boardLocation, excludedCats);
		if (!ads.isEmpty()) {
			Collections.shuffle(ads);
			sortAds(ads, userPrefCats);
			if (ads.size() >= ADS_MIN_TO_SERVE) {
				return adMapper.entityToDto(ads);
			}
		}
		Integer[] adIds = getAdIds(ads);
		List<AdvertEntity> defaultAds = getDefaultAds(boardLocation, adIds, excludedCats, ADS_MIN_TO_SERVE - ads.size());
		ads.addAll(defaultAds);
		return adMapper.entityToDto(ads);
	}

	private List<AdvertEntity> getDefaultAds(BoardLoc boardLocation, Integer[] adIdsToexclude, Integer[] catIdsToexclude, int numberReqd) {

		List<DefaultAdParams> params = getDefaultAdParams(boardLocation, adIdsToexclude, numberReqd);

		Integer[] ids = toDefaultAdIds(params);
		List<AdvertEntity> ads = adDao.getAdvertsByIds(ids, catIdsToexclude);
		return ads;
	}

	private List<DefaultAdParams> getDefaultAdParams(BoardLoc boardLocation, Integer[] adIdsToexclude, int numberReqd) {

		List<DefaultAdParams> params;
		if (boardLocation != null) {
			params = defaultAdParamsDao.getDefaultAdParams(boardLocation, adIdsToexclude, numberReqd);
			numberReqd -= params.size();
		} else {
			params = new ArrayList<DefaultAdParams>();
		}
		if(numberReqd > 0){
			params.addAll(defaultAdParamsDao.getNullLocationDefaultAdParams(numberReqd));
		}
		return params;
	}

	private Integer[] toDefaultAdIds(List<DefaultAdParams> defaultAds) {
		Integer[] dispIds = new Integer[defaultAds.size()];
		int i = 0;
		for (DefaultAdParams defaultAd : defaultAds) {
			dispIds[i++] = defaultAd.getAdId();
		}

		return dispIds;
	}

	private void sortAds(List<AdvertEntity> ads, Integer[] userPrefCats) {

		if (userPrefCats == null || userPrefCats.length == 0) {
			return;
		}

		Comparator<AdvertEntity> comp = new AdPrefComparator(userPrefCats);

		Collections.sort(ads, comp);

	}

	class AdPrefComparator implements Comparator<AdvertEntity> {

		Integer[] catids;

		AdPrefComparator(Integer[] catids) {
			this.catids = catids;

		}

		@Override
		public int compare(AdvertEntity o1, AdvertEntity o2) {

			if (isPreferred(o1)) {
				return -1;
			} else if (isPreferred(o2)) {
				return 1;
			}
			return 0;
		}

		private boolean isPreferred(AdvertEntity o1) {

			for (int i = 0; i < catids.length; i++) {

				if (o1.getCategoryId() == catids[i].intValue()) {
					return true;
				}
			}

			return false;
		}

	}

	private Integer[] getAdIds(List<AdvertEntity> paidAds) {

		Integer[] ids = new Integer[paidAds.size()];
		for (int i = 0; i < paidAds.size(); i++) {
			AdvertEntity advert = paidAds.get(i);
			ids[i] = advert.getId();
		}
		return ids;
	}

}
