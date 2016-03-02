package com.pos.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.pos.model.GoodsSalesModel;
import com.pos.service.IPrivilegeService;
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

	@RequestMapping("/sales")
	public void goodsSales(String data) {

		try {
			Class clz = Class.forName("com.pos.service.impl.NormalPrivilegeServiceImpl");
			IPrivilegeService service = (IPrivilegeService) SpringApplicationUtil.getBean(clz);
			service.calculate(null);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray jsonArray = JSONArray.fromObject(data);
		List<GoodsSalesModel> models = new ArrayList<GoodsSalesModel>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject jsonObject = (JSONObject) jsonArray.get(i);

			GoodsSalesModel model = new GoodsSalesModel();
			model.setBarcode(String.valueOf(jsonObject.get("barcode")));
			model.setNumber(jsonObject.getInt("number"));

			models.add(model);
		}
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
