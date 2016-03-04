package com.pos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.pos.dao.IGoodsDao;
import com.pos.entity.Goods;
import com.pos.service.IPreferentialService;

public abstract class AbstractPreferentialService implements IPreferentialService {

	@Autowired
	private IGoodsDao goodsDao;

	/**
	 * 根据条形码获取商品信息
	 * 
	 * @param barcode
	 * @return
	 */
	protected Goods getGoodsByBarcode(String barcode) {
		return goodsDao.getGoodsByBarcode(barcode);
	}
}
