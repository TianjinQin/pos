package com.pos.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class SpringApplicationUtil {

	private static WebApplicationContext wac;

	static {
		wac = ContextLoader.getCurrentWebApplicationContext();
	}

	public static Object getBean(String beanName) {
		return wac.getBean(beanName);
	}

	public static Object getBean(Class clz) {
		return wac.getBean(clz);
	}
}
