package com.sp.portal.adverts;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import shine.app.AdManager;
import shine.app.BrandManager;
import shine.app.ImageHandler;
import shine.dao.exception.ShineException;

import com.shine.boards.AdCategory;
import com.shine.boards.Advert;
import com.shine.boards.CorpBrand;
import com.sp.admin.CompanyDao;
import com.sp.advert.AdvertDao;
import com.sp.brand.BrandDao;
import com.sp.entity.CorpBrandEntity;
import com.sp.entity.ad.AdvertEntity;
import com.sp.img.ImageScaler;
import com.sp.portal.PortalBaseController;
import com.sp.portal.PortalControllerHelper;
import com.sp.security.Role;
import com.sp.user.UserDao;

@Controller
@RequestMapping(value = "/portal/adverts")
public class AdvertController extends PortalBaseController {

	private static final Logger logger = LoggerFactory.getLogger(AdvertController.class);
	
	@Autowired
	private AdvertDao advertDao;

	@Autowired
	private AdManager adManager;

	@Autowired
	private PortalControllerHelper helper;

	@Autowired
	private BrandManager brandManager;
	@Autowired
	private BrandDao brandDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CompanyDao companyDao;
	
	@Autowired
	private ImageHandler imageHandler;

	@Autowired
	private ImageScaler imageScaler;

	@RequestMapping(value = "/")
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
		}

		AdvertsResult result = populateResult(adverts, start, count, totalBrands);
		model.addAttribute("result", result);

		return "portal/portaladverts";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	@Secured({ Role.ADMIN, Role.USER })
	public String createAdvertForm(Model model,
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
		model.addAttribute("advertForm", new AdvertForm(userBrands, adCategories));
		return "portal/portaladvertsnew";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@Secured({ Role.ADMIN, Role.USER })
	@Transactional
	public String create(
			@ModelAttribute("advertForm") AdvertForm advertForm, Model model, Principal principal) throws ShineException, IOException {

		logger.info("PortalAdvertController - /adverts/create");

		AdvertEntity ad = createEntity(advertForm);
		ad.setUserId(helper.getUserId(principal));

		advertDao.createAd(ad);

		MultipartFile imageUpload = advertForm.getImageUpload();
		if (imageUpload != null && !imageUpload.isEmpty()) {
			String imageUrl = imageHandler.saveAdImage(imageUpload.getInputStream(), ad.getId());
			if (imageUrl != null) {
				ad.setImageUrl(imageUrl);
			}
		}

		return "redirect:id/" + ad.getId();
	}

	@RequestMapping(value = "/id/{adid}")
	@Secured({ Role.ADMIN, Role.USER })
	@Transactional
	public String showAdvert(@PathVariable(value = "adid") int adid, Model model, Principal principal) {

		Advert ad = adManager.getAdvert(adid);
		if (ad.getUserId() == null || !ad.getUserId().equals(helper.getUserId(principal))) {
			throw new SecurityException("");
		}
		model.addAttribute("advert", ad);

		return "portal/advert";
	}

	private AdvertEntity createEntity(AdvertForm advertForm) {

		AdvertEntity entity = new AdvertEntity();
		if (advertForm.getBrandId() > 0) {
			CorpBrandEntity brand = brandDao.getBrand((int) advertForm.getBrandId());
			entity.setBrand(brand);

		}
		// ad.setCategoryId(advertForm.getCategoryId());
		entity.setDisplayname(advertForm.getDisplayName());
		entity.setEmail(advertForm.getEmail());
		// ad.setFacebook(advertForm.getPhoneno());
		// ad.setIconUrl(advertForm.getDisplayName());
		entity.setPhoneno(advertForm.getPhoneNo());
		// ad.setProfile(advertForm.getDisplayName());
		entity.setText(advertForm.getText());
		entity.setTitle(advertForm.getTitle());
		// ad.setTwitter(advertForm.getDisplayName());
		entity.setUrl(advertForm.getUrl());
		return entity;
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

	// @ModelAttribute("advertForm")
	// public AdvertForm getAdvertForm() {
	// return new AdvertForm();
	// }

}
