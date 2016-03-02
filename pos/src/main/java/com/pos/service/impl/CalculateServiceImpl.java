package com.pos.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.dao.IPreferentialDao;
import com.pos.entity.Preferential;
import com.pos.model.GoodsSalesModel;
import com.pos.service.ICalculateService;
import com.pos.service.IPreferentialService;
import com.pos.util.SpringApplicationUtil;

@Service
public class CalculateServiceImpl implements ICalculateService {

	@Autowired
	private IPreferentialDao preferentialDao;
	private List<Preferential> preferentialList;
	@Autowired
	private IPreferentialService normalPreferentialServiceImpl;

	@Override
	public void calculateGoods(List<GoodsSalesModel> models) {
		if (CollectionUtils.isEmpty(models)) {
			// LOGGER.info("无交易信息!");
			return;
		}
		StringBuffer sb = new StringBuffer();
		for (Iterator iterator = models.iterator(); iterator.hasNext();) {
			GoodsSalesModel goodsSalesModel = (GoodsSalesModel) iterator.next();
			// 通过条形码,获取具体优惠处理类
			sb.append(calculate(goodsSalesModel)).append("\n");
		}
		System.out.println(sb);
	}

	public String calculate(GoodsSalesModel model) {

		List<Preferential> pList = preferentialDao.getPreferentialsByBarcode(model.getBarcode());
		Iterator<Preferential> iterator = pList.iterator();
		while (iterator.hasNext()) {
			Preferential preferential = iterator.next();
			IPreferentialService preferentialService = (IPreferentialService) SpringApplicationUtil
					.getBean(preferential.getCode());
			String result = preferentialService.calculate(model);
			if (StringUtils.isNotBlank(result)) {
				return result;
			}
		}
		return normalPreferentialServiceImpl.calculate(model);
	}
}
