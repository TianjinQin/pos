package com.pos.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pos.service.impl.BeanAction;

public class CalculateServiceImplTest extends BaseServiceTest {
	@Autowired
	ICalculateService calculateService;

	@Test
	public void calculateGoodsTest() {

		ICalculateService calculateService = (ICalculateService) BeanAction.getBean("x");

		calculateService.calculateGoods(null);
		

	}

}
