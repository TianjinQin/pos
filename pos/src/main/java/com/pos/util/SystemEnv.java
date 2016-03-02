package com.pos.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

public class SystemEnv {
	private static Map<String, String> map = new HashMap<String, String>();

	static {
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			Enumeration enumeration = prop.propertyNames();
			while (enumeration.hasMoreElements()) {
				String key = (String) enumeration.nextElement();
				map.put(key, prop.getProperty(key));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getProperty(String key) {
		if (StringUtils.isBlank(key))
			return null;
		return map.get(key);
	}
}
