package com.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pos.model.GoodsSalesModel;
import com.pos.schema.People;
import com.pos.service.ICalculateService;
import com.pos.util.SpringApplicationUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author qintj
 *
 */
@Controller
@RequestMapping("/goodsSales")
public class GoodsSalesController {

	@Autowired
	private ICalculateService calculateServiceImpl;

	@RequestMapping("/sales")
	public void goodsSales(String data) {

		People people=(People) SpringApplicationUtil.getBean("jim");
		
		
		String originalStr = "['ITEM000001', 'ITEM000001', 'ITEM000001','ITEM000001','ITEM000001', 'ITEM000003-2','ITEM000005','ITEM000005', 'ITEM000005']";

		data = str2JsonStr(originalStr);

		JSONArray jsonArray = JSONArray.fromObject(data);
		List<GoodsSalesModel> models = new ArrayList<GoodsSalesModel>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);
			GoodsSalesModel model = new GoodsSalesModel();
			model.setBarcode(String.valueOf(jsonObject.get("barcode")));
			model.setNumber(jsonObject.getInt("number"));
			models.add(model);
		}
		calculateServiceImpl.calculateGoods(models);

	}

	public static void main(String[] args) {
		String originalStr = "['ITEM000001', 'ITEM000001', 'ITEM000001','ITEM000001','ITEM000001', 'ITEM000003-2','ITEM000005','ITEM000005', 'ITEM000005']";
		GoodsSalesController controller = new GoodsSalesController();
		controller.goodsSales(controller.str2JsonStr(originalStr));
	}

	/**
	 * 字符串转化成json字符串
	 *
	 * @param originalStr
	 * @return
	 */
	public String str2JsonStr(String originalStr) {
		originalStr = originalStr.substring(1, originalStr.length() - 1);
		originalStr = originalStr.replaceAll("'", "").replaceAll(" +", "");
		String[] arryStr = originalStr.split(",");
		Map<String, GoodsSalesModel> goodsSalesMap = new HashMap<String, GoodsSalesModel>();
		for (String s : arryStr) {
			String barcode = null;
			Integer number = 1;
			if (s.contains("-")) {
				barcode = s.substring(0, s.indexOf("-"));
				number = Integer.parseInt(s.substring(s.indexOf("-") + 1));
			} else {
				barcode = s;
			}
			GoodsSalesModel goodsSalesModel = goodsSalesMap.get(barcode);
			if (null == goodsSalesModel) {
				goodsSalesModel = new GoodsSalesModel(barcode, number);
				goodsSalesMap.put(barcode, goodsSalesModel);
			} else {
				goodsSalesModel.setNumber(goodsSalesModel.getNumber() + number);
			}
		}
		JSONArray jsonArray = new JSONArray();
		for (Map.Entry entry : goodsSalesMap.entrySet()) {
			GoodsSalesModel model = (GoodsSalesModel) entry.getValue();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("barcode", model.getBarcode());
			jsonObject.put("number", model.getNumber());
			jsonArray.add(jsonObject);
		}

		return jsonArray.toString();
	}
}
