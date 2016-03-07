package com.pos.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CalculateServiceImplTest extends BaseServiceTest {
	@Autowired
	ICalculateService calculateService;

	@Test
	public void calculateGoodsTest() {

		calculateService.calculateGoods(null);
	}

}
