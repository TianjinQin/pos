package com.pos.service;

import java.util.List;

import com.pos.entity.Goods;
import com.pos.model.GoodsSalesModel;

/**
 * 结算
 *
 * @author qintj
 *
 */
public interface ICalculateService {

	/**
	 * 计算商品总价
	 *
	 * @param models
	 */
	void calculateGoods(List<GoodsSalesModel> models);

	Goods getGoodsByBarcode(String barcode);
}
