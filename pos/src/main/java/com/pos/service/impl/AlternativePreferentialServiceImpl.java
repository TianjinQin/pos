package com.pos.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.pos.entity.Goods;
import com.pos.model.GoodsSalesModel;
import com.pos.model.PrintInfo;
import com.pos.util.SystemEnv;

/**
 * 买二赠一
 *
 * @author qintj
 *
 */
@Service
public class AlternativePreferentialServiceImpl extends AbstractPreferentialService {

	private static final String INFO = "名称：%s，数量：%s %s，单价：%s(元)，小计：%s(元)";
	private static final String INFO_ATIVE = "名称：%s，数量：%s %s";
	private static final int QUANTITY_BASE = Integer.parseInt(SystemEnv.getProperty("quantity"));

	@Override
	public String calculate(GoodsSalesModel model, PrintInfo info) {
		Goods goods = getGoodsByBarcode(model.getBarcode());
		if (model.getNumber().compareTo(QUANTITY_BASE) < 0)
			return null;
		int ativeNumber = model.getNumber() / QUANTITY_BASE;
		// 原始金额
		BigDecimal originalPrice = BigDecimal.valueOf(goods.getPrice().floatValue() * model.getNumber()).setScale(2,
				RoundingMode.HALF_UP);
		// 优惠金额
		BigDecimal realPrice = BigDecimal.valueOf(goods.getPrice().floatValue() * (model.getNumber() - ativeNumber))
				.setScale(2, RoundingMode.HALF_UP);
		// 差异金额
		BigDecimal balancePrice = originalPrice.subtract(realPrice);
		String print = String.format(INFO, goods.getName(), model.getNumber(), goods.getUnit(), goods.getPrice(),
				realPrice, balancePrice);
		info.addTotalRealPrice(realPrice);
		info.addTotalBalancePrice(balancePrice);
		String ativePrint = String.format(INFO_ATIVE, goods.getName(), ativeNumber, goods.getUnit());
		info.addNormal(print);
		info.addAlternative(ativePrint);
		return print;
	}
}
