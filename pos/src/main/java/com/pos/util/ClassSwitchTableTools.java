package com.pos.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class ClassSwitchTableTools<E> {

	/**
	 * field->column
	 */
	private Map<String, String> classTypeMap = new HashMap<String, String>();
	/**
	 * column->field[PropertyDescriptor]
	 */
	private Map<String, PropertyDescriptor> columnMap = new HashMap<String, PropertyDescriptor>();
	private Class<E> cls;
	private String tableName;

	public ClassSwitchTableTools(Class<E> cls) {
		this.cls = cls;
		init();
		RootBeanDefinition bd = new RootBeanDefinition(String.class);
		RuntimeBeanReference reference = new RuntimeBeanReference("");

		PropertyValue pv = new PropertyValue("", reference);
		bd.getPropertyValues().addPropertyValue(pv);
	}

	/**
	 * 初始化
	 */
	private void init() {
		parseTable();
		parseField();
		parseColumn();
	}

	/**
	 * 解析表名
	 */
	private void parseTable() {
		Annotation[] anno = cls.getAnnotations();
		boolean hasAnno = false;
		for (Annotation annotation : anno) {
			if (annotation.annotationType().isAssignableFrom(TableFiled.class)) {
				String tableName = ((TableFiled) annotation).tableName();
				if (StringUtils.isNotBlank(tableName)) {
					this.tableName = tableName;
					hasAnno = true;
					return;
				}
			}
		}
		if (!hasAnno) {
			this.tableName = "`" + strClass2Db(cls.getSimpleName()) + "`";

		}
	}

	/**
	 * 解析域
	 */
	private void parseField() {
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			if (Modifier.isStatic(field.getModifiers()))
				continue;
			if (isSampleType(field) && haveSetAndGet(field)) {
				Annotation[] anno = field.getAnnotations();
				String dbName = null;
				for (Annotation annotation : anno) {
					if (annotation.annotationType().isAssignableFrom(CommonField.class)) {
						dbName = ((CommonField) annotation).columnName();
						break;
					}
				}
				if (StringUtils.isBlank(dbName))
					dbName = strClass2Db(field.getName());
				classTypeMap.put(field.getName(), dbName);
				PropertyColumn pro = new PropertyColumn();
				pro.setColumnName(dbName);
				pro.setPropertyName(field.getName());
			}
		}
	}

	/**
	 * 解析字段
	 */
	private void parseColumn() {

		BeanInfo bean = null;
		try {
			bean = Introspector.getBeanInfo(cls);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		}
		if (bean != null) {
			PropertyDescriptor[] props = bean.getPropertyDescriptors();
			for (int i = 0; i < props.length; i++) {
				PropertyDescriptor pd = props[i];
				String column = classTypeMap.get(pd.getName());
				if (null != column) {
					columnMap.put(column, pd);
				}
			}
		}

	}

	/**
	 * 判断此field的类型是否是数据局基础类型
	 *
	 * @param field
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private Boolean isSampleType(Field field) {
		Class cla = field.getType();
		if (cla.isPrimitive()) {
			return Boolean.TRUE;
		}
		if (Number.class.isAssignableFrom(cla) || Boolean.class.isAssignableFrom(cla)
				|| Integer.class.isAssignableFrom(cla) || String.class.isAssignableFrom(cla)
				|| Character.class.isAssignableFrom(cla) || Long.class.isAssignableFrom(cla)
				|| Double.class.isAssignableFrom(cla) || Float.class.isAssignableFrom(cla)
				|| Date.class.isAssignableFrom(cla))
			return Boolean.TRUE;
		return Boolean.FALSE;
	}

	/**
	 * 判断此field是否有set、get方法
	 *
	 * @param field
	 * @return
	 */
	private Boolean haveSetAndGet(Field field) {
		Method m = ClassUtil.getGetMethod(field);
		if (null == m)
			return Boolean.FALSE;
		m = ClassUtil.getSetMethod(field);
		if (null == m)
			return Boolean.FALSE;
		return Boolean.TRUE;
	}

	private static String strClass2Db(String fieldName) {
		String str = fieldName.replaceAll("([A-Z])", "_$1").toLowerCase();
		return str;
	}

	public Map<String, String> getClassTypeMap() {
		return classTypeMap;
	}

	public void setClassTypeMap(Map<String, String> classTypeMap) {
		this.classTypeMap = classTypeMap;
	}

	public Map<String, PropertyDescriptor> getColumnMap() {
		return columnMap;
	}

	public void setColumnMap(Map<String, PropertyDescriptor> columnMap) {
		this.columnMap = columnMap;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public static void main(String[] args) {

	}
}
