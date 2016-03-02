package com.pos.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUtil {

	public static Method getGetMethod(Field field) {

		Method m = null;
		try {
			m = field.getDeclaringClass().getMethod("get" + subFieldName(field.getName()));
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return m;
	}

	public static Method getSetMethod(Field field) {
		Method m = null;
		try {
			m = field.getDeclaringClass().getMethod("set" + subFieldName(field.getName()));
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		return m;
	}

	private static String subFieldName(String fieldName) {
		if (fieldName.length() > 1) {
			return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		} else {
			return fieldName.toUpperCase();
		}
	}
}
