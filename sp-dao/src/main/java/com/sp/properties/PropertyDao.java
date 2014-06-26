package com.sp.properties;

import java.util.List;

public interface PropertyDao {

	PropertyEntity getProperty(String key);
	List<PropertyEntity> getAllProperties();
	
}
