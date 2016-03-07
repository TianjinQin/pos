package com.pos.schema;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class PeopleNamespaceHandler extends NamespaceHandlerSupport {

	@Override
	public void init() {
		// TODO Auto-generated method stub
		registerBeanDefinitionParser("people", new PeopleBeanDefinitionParser());
	}

}
