package com.pos.service.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pos.dao.IPreferentialDao;
import com.pos.entity.Preferential;
import com.pos.model.GoodsSalesModel;
import com.pos.model.PrintInfo;
import com.pos.schema.People;
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

	public CalculateServiceImpl() {
		BeanAction.register("x", this);
	}

	@Override
	public void calculateGoods(List<GoodsSalesModel> models) {
		// if (CollectionUtils.isEmpty(models)) {
		// // LOGGER.info("无交易信息!");
		// return;
		// }
		//
		// PrintInfo info = new PrintInfo();
		// for (Iterator iterator = models.iterator(); iterator.hasNext();) {
		// GoodsSalesModel goodsSalesModel = (GoodsSalesModel) iterator.next();
		// calculate(goodsSalesModel, info);
		// }
		// info.print();
		List<Preferential> pList =preferentialDao.getPreferentialsByBarcode("ITEM000005");
		System.out.println("ok");
		People people = (People) SpringApplicationUtil.getBean("jim");
		System.out.println(people.getName());

	}

	public String calculate(GoodsSalesModel model, PrintInfo info) {

		List<Preferential> pList = preferentialDao.getPreferentialsByBarcode(model.getBarcode());
		Iterator<Preferential> iterator = pList.iterator();

		while (iterator.hasNext()) {
			Preferential preferential = iterator.next();
			IPreferentialService preferentialService = (IPreferentialService) SpringApplicationUtil
					.getBean(preferential.getCode());
			String result = preferentialService.calculate(model, info);
			if (StringUtils.isNotBlank(result)) {
				return result;
			}
		}
		return normalPreferentialServiceImpl.calculate(model, info);
	}
}
