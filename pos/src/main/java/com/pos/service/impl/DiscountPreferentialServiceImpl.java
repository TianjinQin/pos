package com.pos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.pos.entity.Goods;
import com.pos.model.GoodsSalesModel;
import com.pos.model.PrintInfo;
import com.pos.util.SystemEnv;

/**
 * 95折优惠
 * 
 * @author qintj
 *
 */
@Service
public class DiscountPreferentialServiceImpl extends AbstractPreferentialService {

	private static final BigDecimal DISCOUNT = new BigDecimal(SystemEnv.getProperty("discount"));

	private static final String INFO = "名称：%s，数量：%s %s，单价：%s(元)，小计：%s(元)，节省%s(元)";

	@Override
	public String calculate(GoodsSalesModel model, PrintInfo info) {
		Goods goods = getGoodsByBarcode(model.getBarcode());
		// 原始金额
		BigDecimal originalPrice = BigDecimal.valueOf(goods.getPrice().floatValue() * model.getNumber()).setScale(2,
				RoundingMode.HALF_UP);
		// 优惠金额
		BigDecimal realPrice = originalPrice.multiply(DISCOUNT).setScale(2, RoundingMode.HALF_UP);
		// 差异金额
		BigDecimal balancePrice = originalPrice.subtract(realPrice);
		String print = String.format(INFO, goods.getName(), model.getNumber(), goods.getUnit(), goods.getPrice(),
				realPrice, balancePrice);
		info.addTotalRealPrice(realPrice);
		info.addTotalBalancePrice(balancePrice);
		info.addNormal(print);
		return print;
	}
}
