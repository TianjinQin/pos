package com.pos.service.impl;

import org.springframework.stereotype.Service;

import com.pos.model.GoodsSalesModel;
import com.pos.service.IPreferentialService;

/**
 * 二选一优惠
 *
 * @author qintj
 *
 */
@Service
public class AlternativePreferentialServiceImpl implements IPreferentialService {

	@Override
	public String calculate(GoodsSalesModel model) {
		// TODO Auto-generated method stub
		return "二选一优惠";
	}

}
