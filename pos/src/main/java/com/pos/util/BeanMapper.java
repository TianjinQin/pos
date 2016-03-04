package com.pos.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

public class BeanMapper<E> implements RowMapper<E> {

	private Class<E> cls;

	@SuppressWarnings("rawtypes")
	private static Map<Class, ClassSwitchTableTools> cacheMap = new ConcurrentHashMap<Class, ClassSwitchTableTools>();

	private static final Map<Class, Object> primitiveDefault = new HashMap<Class, Object>(8);
	private ClassSwitchTableTools<E> cwt;
	ClassSwitchTableTools<E> fClassSwitchTableTools;

	static {
		primitiveDefault.put(Boolean.class, Boolean.FALSE);
		primitiveDefault.put(Integer.class, Integer.valueOf(0));
		primitiveDefault.put(Byte.class, Byte.valueOf((byte) 0));
		primitiveDefault.put(Character.class, Character.valueOf((char) 0));
		primitiveDefault.put(Short.class, Short.valueOf((short) 0));
		primitiveDefault.put(Float.class, Float.valueOf(0));
		primitiveDefault.put(Long.class, Long.valueOf(0));
		primitiveDefault.put(Double.class, Double.valueOf(0));
	}

	@SuppressWarnings("unchecked")
	public BeanMapper(Class<E> cls) {
		if (!cacheMap.containsKey(cls)) {
			cacheMap.put(cls, new ClassSwitchTableTools<E>(cls));
		}
		this.cls = cls;
		this.cwt = cacheMap.get(cls);
	}

	@SuppressWarnings({ "unused" })
	@Override
	public E mapRow(ResultSet rs, int rowNum) throws SQLException {

		E bean = null;
		try {
			bean = cls.newInstance();
			ResultSetMetaData metaData = rs.getMetaData();
			for (int cols = 1; cols <= metaData.getColumnCount(); cols++) {
				String column = metaData.getColumnLabel(cols);
				if (StringUtils.isBlank(column)) {
					column = metaData.getColumnName(cols);
				}
				PropertyDescriptor pd = cwt.getColumnMap().get(column);
				if (pd != null) {
					Class<?> propType = pd.getPropertyType();
					Object value = processColumn(rs, column, propType);
					if (propType != null && value == null && propType.isPrimitive()) {
						value = primitiveDefault.get(propType);
					}
					callSetter(bean, pd, value);
				}
			}

		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return bean;
	}

	/**
	 * 未写完,待续
	 *
	 * @param bean
	 * @param pd
	 * @param value
	 */
	private static void callSetter(Object bean, PropertyDescriptor pd, Object value) {

		Method method = pd.getWriteMethod();
		try {
			Class<?>[] params = method.getParameterTypes();
			if (value instanceof Date) {
				if ("java.sql.Date".equals(params[0])) {
					value = new java.sql.Date(((Date) value).getTime());
				} else if ("java.sql.Time".equals(params[0])) {
					value = new java.sql.Time(((Date) value).getTime());
				} else if ("java.sql.Timestamp".equals(params[0])) {
					value = new java.sql.Timestamp(((Date) value).getTime());
				}
			}
			if (isCompatibleType(params[0], value))
				method.invoke(bean, new Object[] { value });
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	private static boolean isCompatibleType(Class<?> type, Object value) {
		if (value == null || type.isInstance(value)) {
			return true;
		} else if (type.equals(Integer.TYPE) && Integer.class.isInstance(value)) {
			return true;
		} else if (type.equals(Long.TYPE) && Long.class.isInstance(value)) {
			return true;
		} else if (type.equals(Double.TYPE) && Double.class.isInstance(value)) {
			return true;
		} else if (type.equals(Float.TYPE) && Float.class.isInstance(value)) {
			return true;
		} else if (type.equals(Short.TYPE) && Short.class.isInstance(value)) {
			return true;
		} else if (type.equals(Byte.TYPE) && Byte.class.isInstance(value)) {
			return true;
		} else if (type.equals(Character.TYPE) && Character.class.isInstance(value)) {
			return true;
		} else if (type.equals(Boolean.TYPE) && Boolean.class.isInstance(value)) {
			return true;
		}
		return false;

	}

	/**
	 *
	 *
	 * @param rs
	 * @param columnIndex
	 * @param propType
	 * @return
	 * @throws SQLException
	 */
	private static Object processColumn(ResultSet rs, String columnIndex, Class<?> propType) throws SQLException {
		if (!propType.isPrimitive() && rs.getObject(columnIndex) == null) {
			return null;
		}
		// 基本数据类型需要转换成对应的包装类
		if (propType.equals(Integer.TYPE) || propType.equals(Integer.class))
			return Integer.valueOf(rs.getInt(columnIndex));
		if (propType.equals(Boolean.TYPE) || propType.equals(Boolean.class))
			return Boolean.valueOf(rs.getBoolean(columnIndex));
		if (propType.equals(Long.TYPE) || propType.equals(Long.class))
			return Long.valueOf(rs.getLong(columnIndex));
		if (propType.equals(Double.TYPE) || propType.equals(Double.class))
			return Double.valueOf(rs.getDouble(columnIndex));
		if (propType.equals(Float.TYPE) || propType.equals(Float.class))
			return Float.valueOf(rs.getFloat(columnIndex));
		if (propType.equals(Short.TYPE) || propType.equals(Short.class))
			return Short.valueOf(rs.getShort(columnIndex));
		if (propType.equals(Byte.TYPE) || propType.equals(Byte.class))
			return Byte.valueOf(rs.getByte(columnIndex));
		if (propType.equals(BigDecimal.class))
			return rs.getBigDecimal(columnIndex);
		return rs.getObject(columnIndex);
	}

	public static void main(String[] args) {
		System.out.println(Integer.class);
	}
}
