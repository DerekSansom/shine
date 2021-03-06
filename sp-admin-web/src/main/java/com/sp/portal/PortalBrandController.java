package com.sp.portal;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.imageio.ImageIO;

import org.hibernate.ejb.criteria.ParameterContainer.Helper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import shine.app.BrandManager;
import shine.app.ImageHandler;
import shine.app.UserManager;
import shine.app.utils.ShineProperties;
import shine.dao.exception.ShineException;

import com.shine.boards.CorpBrand;
import com.sp.portal.brands.BrandResult;
import com.sp.portal.brands.BrandsResult;
import com.sp.security.Role;

@Controller
@RequestMapping(value = "/portal/brands")

public class PortalBrandController extends PortalBaseController {

	private static final Logger logger = LoggerFactory.getLogger(PortalBrandController.class);

	@Autowired
	private BrandManager brandManager;
	
	@Autowired
	private UserManager userManager;
	
	@RequestMapping(value = "")
	@Secured({ Role.ADMIN, Role.USER })
	public String brands(	Model model, 
							Principal principal,
							@RequestParam(required = false, value = "start") Integer start)
									throws ShineException {
		
	    logger.info("PortalBrandController - brands");

	    int count = 20;
		start = controllerHelper.calculateStart(start);
		int totalBrands = 0;
		List<CorpBrand> brands = null;

		if (controllerHelper.isAdmin(principal)) {
	    	brands = brandManager.getAllBrandsPaginated(start, count);
		} else {
		    int userId = controllerHelper.getUserId(principal);
		    brands = brandManager.getBrandsForUserId(userId);
		}

		BrandsResult result = populateResult(brands, start, count, totalBrands);
		model.addAttribute("result", result);
		return "portal/portalbrands";
	}

	@RequestMapping(value = "/showNew")
	@Secured({ Role.ADMIN, Role.USER })
	public String showNewBrand(	Model model) throws ShineException {
		Logger logger = LoggerFactory.getLogger(PortalBrandController.class);
	    logger.info("PortalBrandController - showNewBrand");
		return "portal/portalbrandsnew";
	}
	
	@RequestMapping(value = "/saveNew", method = RequestMethod.POST)
	@Secured({ Role.ADMIN, Role.USER })
	public String saveNewBrand(	Model model, 
								Principal principal,
								@RequestParam("name") String name, 
								@RequestParam("bgFile") MultipartFile bgFile,
								@RequestParam("logoFile") MultipartFile logoFile,
								@RequestParam("bgColour") String bgColour,
								@RequestParam("linkColour") String linkColour,
								@RequestParam("textColour") String textColour
								//@RequestParam(value = "decision", required = false) String acceptedStr
							) throws ShineException {
		Logger logger = LoggerFactory.getLogger(PortalBrandController.class);
	    logger.info("PortalBrandController - saveNewBrand");

	    BufferedImage biBackground = null;
	    BufferedImage biLogo = null;
	    CorpBrand brand = null;

	    // VALIDATION
	    ArrayList<String> errorMessages = new ArrayList<String>();
	    
		if (name == null || name.trim().length() < 6) {
			errorMessages.add("Name must be given and at least 6 chars");
		} 
		
		if (bgFile.isEmpty()) {
			errorMessages.add("An image for the background must be provided");
        }
		if (logoFile.isEmpty()) {
			errorMessages.add("An image for the logo must be provided");
        }
		
		Integer objCorpId = userManager.getCompanyForUserId(controllerHelper.getUserId(principal));
		if(objCorpId==null){
			errorMessages.add("You must have a company to create brands");
		}
        try {
        	biBackground = controllerHelper.getBufferedImageFromBytes(bgFile.getBytes());
        }
        catch (Exception e){
        	errorMessages.add("Background file invalid (" + e.getMessage() + ")");;
        }

        try {
        	biLogo = controllerHelper.getBufferedImageFromBytes(logoFile.getBytes());
        }
        catch (Exception e){
        	errorMessages.add("Logo file invalid (" + e.getMessage() + ")");
        }

        // TRY TO SAVE
        if(errorMessages.isEmpty()) {
	        try {
	        	brand = new CorpBrand();
		        brand.setName(name);
		        brand.setCorporateId(objCorpId.intValue());
		        brand.setBgColour(bgColour);
		        brand.setLinkColour(linkColour);
		        brand.setTextColour(textColour);
		        brand.setBackgroundimg(controllerHelper.saveImage(biBackground, ShineProperties.getBrandBGfolderPath()));
		        brand.setLogo(controllerHelper.saveImage(biLogo, ShineProperties.getLogoimgfolderPath()));
		        
		        brandManager.saveOrUpdateBrand(brand);
	        }
	        catch (Exception e){
	        	errorMessages.add("Failed to save brand:" + e.getMessage());
	        }
        }
        
	    // RETURN
        BrandResult result = new BrandResult(brand,errorMessages);
       	model.addAttribute("result", result);
		return (this.showNewBrand(model));
	}

	public BrandsResult populateResult(List<CorpBrand> brands, Integer start, Integer count, Integer totalBrands) {

		if (brands.isEmpty()) {
			return new BrandsResult(brands, null, null, null);
		}

		Collections.sort(brands, new Comparator<CorpBrand>() {
			@Override
			public int compare(CorpBrand o1, CorpBrand o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});

		BrandsResult retResult = new BrandsResult(brands, start, count, totalBrands);
		return retResult;
	}
}
