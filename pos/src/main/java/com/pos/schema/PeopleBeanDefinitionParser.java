package com.pos.schema;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class PeopleBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {

	private static final String NAME = "name";
	private static final String AGE = "age";
	private static final String ID = "id";

	@Override
	protected Class getBeanClass(Element element) {
		// TODO Auto-generated method stub
		return People.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		// TODO Auto-generated method stub
		// doParse(element, parserContext, builder);

		element.getAttribute("");
		RootBeanDefinition definition = new RootBeanDefinition();

		PropertyValue pValue = new PropertyValue("sd", "");

		RuntimeBeanReference reference = new RuntimeBeanReference("");
		definition.getPropertyValues().addPropertyValue("", reference);
		definition.setSource(element);

		parserContext.getRegistry().registerBeanDefinition("", definition);
	}

	@Override
	protected void doParse(Element element, BeanDefinitionBuilder builder) {
		// TODO Auto-generated method stub
		String name = element.getAttribute(NAME);
		String age = element.getAttribute(AGE);
		// String id = element.getAttribute(ID);
		// if (StringUtils.isNumeric(id)) {
		// builder.addPropertyValue(ID, Long.parseLong(id));
		// }
		if (StringUtils.isNotBlank(name)) {
			builder.addPropertyValue(NAME, name);
		}
		if (StringUtils.isNumeric(age)) {
			builder.addPropertyValue(AGE, Integer.parseInt(age));
		}

	}

}
