package com.pos.dao;

import java.util.List;

import com.pos.entity.Preferential;

/**
 * 优惠信息持久层
 *
 * @author qintj
 *
 */
public interface IPreferentialDao {

	/**
	 * 根据条形码获取优惠信息
	 *
	 * @param barcode
	 * @return
	 */
	List<Preferential> getPreferentialsByBarcode(String barcode);

	/**
	 * 获取所有优惠，并且通过优先级排序
	 * 
	 * @return
	 */
	List<Preferential> getAllPreferentials();
}
