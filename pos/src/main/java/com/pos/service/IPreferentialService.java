package com.pos.service;

import com.pos.model.GoodsSalesModel;
import com.pos.model.PrintInfo;

/**
 * 优惠计算
 *
 * @author qintj
 *
 */
public interface IPreferentialService {

	String calculate(GoodsSalesModel model,PrintInfo info);

}
