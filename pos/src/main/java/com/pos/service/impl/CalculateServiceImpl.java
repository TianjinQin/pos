package com.pos.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;

import com.pos.entity.Goods;
import com.pos.model.GoodsPrivilegeModel;
import com.pos.model.GoodsSalesModel;
import com.pos.service.ICalculateService;
import com.pos.service.IPrivilegeService;

public class CalculateServiceImpl implements ICalculateService {

	// private static final Logger LOGGER =
	// LoggerFactory.getLogger(CalculateServiceImpl.class);

	private static Map<String, GoodsPrivilegeModel> privilegeMap;

	private static Map<String, Goods> goodsMap;

	static {
		goodsMap = new HashMap<String, Goods>();
		goodsMap.put("ITEM000001", new Goods("可口可乐", "瓶", new BigDecimal("3.00"), "ITEM000001", null));
		goodsMap.put("ITEM000003", new Goods("羽毛球", "个", new BigDecimal("1.00"), "ITEM000003", null));
		goodsMap.put("ITEM000005", new Goods("苹果", "斤", new BigDecimal("5.50"), "ITEM000003", null));
		GoodsPrivilegeModel model = new GoodsPrivilegeModel();
		model.setBarcode("ITEM000001");
		model.addPrivilege(new AlternativePrivilegeServiceImpl());
		privilegeMap.put("ITEM000001", model);

		GoodsPrivilegeModel model2 = new GoodsPrivilegeModel();
		model.setBarcode("ITEM000003");
		model.addPrivilege(new NormalPrivilegeServiceImpl());
		privilegeMap.put("ITEM000003", model2);

		GoodsPrivilegeModel model3 = new GoodsPrivilegeModel();
		model.setBarcode("ITEM000005");
		model.addPrivilege(new DiscountPrivilegeServiceImpl());
		privilegeMap.put("ITEM000005", model2);

	}

	public void calculateGoods(List<GoodsSalesModel> models) {
		if (CollectionUtils.isEmpty(models)) {
			// LOGGER.info("无交易信息!");
			return;
		}
		for (Iterator iterator = models.iterator(); iterator.hasNext();) {
			GoodsSalesModel goodsSalesModel = (GoodsSalesModel) iterator.next();
			// 通过条形码,获取具体优惠处理类
		}

	}

	public String calculate(GoodsSalesModel model) {
		GoodsPrivilegeModel privilegeModel = privilegeMap.get(model.getBarcode());
		// 处理类
		Set<IPrivilegeService> set = privilegeModel.getPrivilege();

		return "";
	}

}
