package com.pos.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.pos.util.SpringApplicationUtil;

public class CalculateServiceImplTest extends BaseServiceTest {
	@Autowired
	ICalculateService calculateService;

	@Test
	public void calculateGoodsTest() {

		ComboPooledDataSource dataSource=(ComboPooledDataSource) SpringApplicationUtil.getBean("dataSource");
		dataSource.setPassword("");
		
		calculateService.calculateGoods(null);
	}

}
