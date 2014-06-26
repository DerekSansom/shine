package com.sp.admin.properties;

import java.security.Principal;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sp.properties.PropertyDao;
import com.sp.properties.PropertyDaoImpl;
import com.sp.properties.PropertyEntity;
import com.sp.security.Role;
import com.sp.security.StreetPinUserDetails;

@Controller
@RequestMapping(value = "/admin/properties")
public class PropertiesController {
	
	private static Logger log = LoggerFactory.getLogger(PropertiesController.class);

	@Autowired
	private PropertyDao propertyDao;

	@RequestMapping(value = "", method = RequestMethod.GET)
	@Secured(Role.ADMIN)
	public String viewProperties(Model model) {

		List<PropertyEntity> properties = propertyDao.getAllProperties();
		model.addAttribute("properties", properties);
		
		return "admin/properties";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@Secured(Role.ADMIN)
	@Transactional
	public String updateProperty(Principal principal, PropertyForm propertyForm, Model model, BindingResult errors) {

		String key = propertyForm.getKey();
		String value = propertyForm.getValue();
		log.info(String.format("updating property [%s] with value [%s]", key, value));
		PropertyEntity entity = propertyDao.getProperty(key);
		if(entity == null){
			log.info("property not found");
			errors.addError(new ObjectError("global", "property not found"));
		}else{
			int userId = getUserId(principal);
			entity.setValue(value);
			entity.setUpdatedById(userId);
			entity.setUpdated(new DateTime().toDate());
			log.info("property updated by userid [%s]",userId);
		}
		return viewProperties(model);
	}
	
	@ModelAttribute("propertyForm")
	public PropertyForm getPropertyForm() {
		return new PropertyForm();
	}
	
	private int getUserId(Principal principal) {
		StreetPinUserDetails user = (StreetPinUserDetails) ((Authentication) principal).getPrincipal();
		if (user == null) {
			return 0;
		}
		return user.getId();
	}

}
