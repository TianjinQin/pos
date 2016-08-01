package com.pos.service.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BeanAction {

	private static final Map<String, Object> MAP = new ConcurrentHashMap<String, Object>();

	public static void register(String id, Object bean) {
		MAP.put(id, bean);
	}

	public static Object getBean(String id) {
		return MAP.get(id);
	}
}
