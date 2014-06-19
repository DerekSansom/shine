package com.sp.portal;

import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import shine.app.AdManager;
import shine.app.BrandManager;
import shine.dao.exception.ShineException;

import com.shine.boards.AdCategory;
import com.shine.boards.Advert;
import com.shine.boards.CorpBrand;
import com.sp.admin.CompanyDao;
import com.sp.portal.adverts.AdvertsResult;
import com.sp.portal.boards.AdvertNewResult;
import com.sp.security.Role;
import com.sp.user.UserDao;

@Controller
@RequestMapping(value = "/portal")
public class PortalAdvertController extends PortalBaseController {

	private static final Logger logger = LoggerFactory.getLogger(PortalAdvertController.class);
	
	@Autowired
	private AdManager adManager;

	@Autowired
	private BrandManager brandManager;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@RequestMapping(value = "/adverts")
	@Secured({ Role.ADMIN, Role.USER })
	public String adverts(	Model model, 
			Principal principal,
			@RequestParam(required = false, value = "start") Integer start)
					throws ShineException {
		
	    logger.info("PortalAdvertController - adverts");
	    
	    int count = 20;
		start = controllerHelper.calculateStart(start);
		int totalBrands = 0;
		List<Advert> adverts = null;

		if (controllerHelper.isAdmin(principal)) {
	    	adverts = adManager.getAllAdvertsPaginated(start, count);
		} else {
	    	adverts = adManager.getAllAdvertsPaginated(start, count);
		/*
		    int userId = getUserId(principal);
		    UserEntity userEntity = userDao.getUserById(userId);
		    Integer companyId = userEntity.getCorp_id();
		    if(companyId != null) {
		    	CompanyEntity company = companyDao.findById(companyId);
		    	adverts = adManager.getAdvertsByBrandIds(userEntity.getCorp_id());
		    	}
*/
		}

		AdvertsResult result = populateResult(adverts, start, count, totalBrands);
		model.addAttribute("result", result);

		return "portal/portaladverts";
	}

	@RequestMapping(value = "/adverts/new")
	@Secured({ Role.ADMIN, Role.USER })
	public String showNewAdvert(Model model, 
								Principal principal) throws ShineException {
	    logger.info("PortalAdvertController - adverts/new");
		List<AdCategory> adCategories = adManager.getAllAdvertCategories();
	    int userId = controllerHelper.getUserId(principal);
	    List<CorpBrand> userBrands = brandManager.getBrandsForUserId(userId);

		Collections.sort(userBrands, new Comparator<CorpBrand>() {
			@Override
			public int compare(CorpBrand o1, CorpBrand o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		AdvertNewResult result = new AdvertNewResult(userBrands, adCategories);
		model.addAttribute("result", result);
		return "portal/portaladvertsnew";
	}
	
	@RequestMapping(value = "/adverts/addAdvert")
	@Secured({ Role.ADMIN, Role.USER })
	public String addNewAdvert(	@RequestParam("reportId") int reportId, 
								@RequestParam("rationale") String rationale,
								@RequestParam(value = "decision", required = false) String acceptedStr, 
								Model model, 
								Principal principal) throws ShineException {
		logger.info("PortalAdvertController - /adverts/addAdvert");
		
		return (this.showNewAdvert(model, principal));
	}

	private AdvertsResult populateResult(List<Advert> adverts, Integer start, Integer count, Integer totalAdverts) {

		if (adverts.isEmpty()) {
			return new AdvertsResult(adverts, null, null, null);
		}

		Collections.sort(adverts, new Comparator<Advert>() {
			@Override
			public int compare(Advert o1, Advert o2) {
				return o1.getDisplayname().compareTo(o2.getDisplayname());
			}
		});

		return new AdvertsResult(adverts, start, count, totalAdverts);
	}

}
