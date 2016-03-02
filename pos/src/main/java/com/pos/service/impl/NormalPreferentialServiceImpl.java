package com.pos.service.impl;

import org.springframework.stereotype.Service;

import com.pos.model.GoodsSalesModel;
import com.pos.service.IPreferentialService;

/**
 * 无优惠处理类
 *
 * @author qintj
 *
 */
@Service
public class NormalPreferentialServiceImpl implements IPreferentialService {

	@Override
	public String calculate(GoodsSalesModel model) {
		// TODO Auto-generated method stub
		System.out.println("ok");
		return "正常价格";
	}

}
