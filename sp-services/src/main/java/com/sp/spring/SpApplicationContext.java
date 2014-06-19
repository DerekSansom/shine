package com.sp.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpApplicationContext implements ApplicationContextAware {

	private static ApplicationContext context;

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		context = arg0;

	}

	public static ApplicationContext getContext() {
		return context;
	}

	public static <T> T getBean(Class<T> beanclass) {
		return context.getBean(beanclass);
	}

}
