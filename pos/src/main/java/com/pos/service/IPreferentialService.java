package com.pos.service;

import com.pos.model.GoodsSalesModel;

/**
 * 优惠计算
 *
 * @author qintj
 *
 */
public interface IPreferentialService {

	String calculate(GoodsSalesModel model);

}
