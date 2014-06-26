package com.sp.admin.properties;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.matchers.JUnitMatchers;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import com.sp.properties.PropertyEntity;
import com.sp.properties.PropertyDao;
import com.sp.security.StreetPinUserDetails;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesControllerTest {

	@Mock
	private PropertyDao propertyDao;
	
	@Mock
	private Model model;
	
	@InjectMocks
	private PropertiesController propertiesController;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void returnsPropertiesPage() {
		//given
		List<PropertyEntity> properties = new ArrayList<>();
		when(propertyDao.getAllProperties()).thenReturn(properties);
		
		//when
		String propertiesPage = propertiesController.viewProperties(model);
		
		//then
		verify(model).addAttribute("properties", properties);
		assertEquals("admin/properties", propertiesPage );
	}

	@Test
	public void updatesProperty() {
		//given
		PropertyForm form = new PropertyForm();
		form.setKey("aKey");
		form.setValue("aNewValue");
		
		PropertyEntity entity = new PropertyEntity();
		when(propertyDao.getProperty("aKey")).thenReturn(entity);

		Authentication principal=mock(Authentication.class);
		StreetPinUserDetails user = mock(StreetPinUserDetails.class);
		when(principal.getPrincipal()).thenReturn(user);
		when(user.getId()).thenReturn(37);
		
		BindingResult errors=mock(BindingResult.class);
		//when
		String propertiesPage = propertiesController.updateProperty(principal,form, model, errors);
		
		//then
		assertEquals("aNewValue", entity.getValue());
		assertEquals(37, entity.getUpdatedById());
		assertEquals("admin/properties", propertiesPage );
		assertNotNull(entity.getUpdated() );

	}
	
	@Test
	public void returnsErrorWhenPropertyNotFound() {
		//given
		PropertyForm form = new PropertyForm();
		form.setKey("aKey");
		form.setValue("aNewValue");
		
		when(propertyDao.getProperty("aKey")).thenReturn(null);

		Authentication principal=mock(Authentication.class);
		
		BindingResult errors=  mock(BindingResult.class);
		//when
		String propertiesPage = propertiesController.updateProperty(principal,form, model, errors);
		
		//then
		ArgumentCaptor<ObjectError> resultsCaptor = ArgumentCaptor.forClass(ObjectError.class);
		verify(errors).addError(resultsCaptor.capture());
		assertEquals("global", resultsCaptor.getValue().getObjectName()  );
		assertEquals("property not found", resultsCaptor.getValue().getDefaultMessage());
		assertEquals("admin/properties", propertiesPage );

	}
	
}
