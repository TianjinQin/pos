package com.pos.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.pos.entity.Goods;

public class CalculateServiceImplTest extends BaseServiceTest {
	@Autowired
	ICalculateService calculateService;

	@Test
	public void getGoods() {
		Goods goods = calculateService.getGoodsByBarcode("ITEM000001");
		System.out.println(goods.getName());
	}

}
