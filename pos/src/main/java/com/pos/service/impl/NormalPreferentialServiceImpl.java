package com.pos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.pos.entity.Goods;
import com.pos.model.GoodsSalesModel;
import com.pos.model.PrintInfo;

/**
 * 无优惠处理类
 *
 * @author qintj
 *
 */
@Service
public class NormalPreferentialServiceImpl extends AbstractPreferentialService {

	private static final String INFO = "名称：%s，数量：%s %s，单价：%s(元)，小计：%s(元)";

	@Override
	public String calculate(GoodsSalesModel model, PrintInfo info) {
		Goods goods = getGoodsByBarcode(model.getBarcode());
		// 原始金额
		BigDecimal originalPrice = BigDecimal.valueOf(goods.getPrice().floatValue() * model.getNumber()).setScale(2,
				RoundingMode.HALF_UP);

		String print = String.format(INFO, goods.getName(), model.getNumber(), goods.getUnit(), goods.getPrice(),
				originalPrice);
		info.addTotalRealPrice(originalPrice);
		info.addNormal(print);
		return print;
	}

}
