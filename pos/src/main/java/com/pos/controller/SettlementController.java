package com.pos.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.pos.model.GoodsSaleModel;

/**
 * 结算
 *
 * @author qintj
 *
 */
public class SettlementController {

	public static void settlement(String data) {
		if (StringUtils.isBlank(data))
			return;
		final String string = "['ITEM000001','ITEM000001', 'ITEM000001','ITEM000001','ITEM000001','ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";
		final String s = string.substring(1, string.length() - 1);
		System.out.println(s);
	}

	public static void main(String[] args) {
		final String string = "['ITEM000001','ITEM000001', 'ITEM000001','ITEM000001','ITEM000001','ITEM000003-2','ITEM000005','ITEM000005','ITEM000005']";
		String s = string.substring(1, string.length() - 1);
		System.out.println(s);
		s = s.replaceAll("'", "").replaceAll(" +", "");
		final String[] arr = s.split(",");
		final Map<String, GoodsSaleModel> goodsMap = new HashMap<String, GoodsSaleModel>();
		for (final String string2 : arr) {
			System.out.println(string2);
			String code = null;
			int number = 1;
			if (string2.contains("-")) {
				code = string2.substring(0, string2.indexOf("-"));
				number = Integer.parseInt(string2.substring(string2.indexOf("-") + 1));
			} else {
				code = string2;
			}
			GoodsSaleModel model = goodsMap.get(code);
			if (model == null) {
				goodsMap.put(code, new GoodsSaleModel(code, number));
			} else {
				model.setTotal(model.getTotal() + number);
			}
		}
		for (Map.Entry entry : goodsMap.entrySet()) {
			GoodsSaleModel model = (GoodsSaleModel) entry.getValue();
			System.out.println(model.getBarcode() + "=" + model.getTotal());
		}
	}
}
