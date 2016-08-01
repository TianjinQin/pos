package com.pos.service;

import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.RuntimeBeanNameReference;
import org.springframework.beans.factory.support.RootBeanDefinition;

import com.pos.schema.People;

public class BeanDefinationTest {

	public static void main(String[] args) {
		RootBeanDefinition bd=new RootBeanDefinition(People.class);
		RuntimeBeanNameReference reference=new RuntimeBeanNameReference("");
		PropertyValue pv=new PropertyValue("", "");
		bd.getPropertyValues().addPropertyValue(pv);
		bd.setSource("");
		
	}
}
