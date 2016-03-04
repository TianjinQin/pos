package com.pos.dao;

import com.pos.entity.Goods;

/**
 * 商品信息持久层
 *
 * @author qintj
 *
 */
public interface IGoodsDao {

	/**
	 * 根据条形码获取商品信息
	 *
	 * @param barcode
	 * @return
	 */
	Goods getGoodsByBarcode(String barcode);
}
