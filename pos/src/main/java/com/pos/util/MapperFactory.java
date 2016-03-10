package com.pos.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;

import com.pos.entity.Goods;

public class MapperFactory {

	private static Map<Class<?>, RowMapper> mapperMap = new HashMap<Class<?>, RowMapper>();

	public static RowMapper createRowMapper(Class<?> clazz) {
		if (!mapperMap.containsKey(clazz)) {
			mapperMap.put(clazz, new BeanMapper(clazz));
		}
		return mapperMap.get(clazz);
	}

	public static void main(String[] args) {
		MapperFactory.createRowMapper(Goods.class);
	}
}