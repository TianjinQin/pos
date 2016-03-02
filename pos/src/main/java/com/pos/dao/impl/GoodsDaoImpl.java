package com.pos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.pos.dao.BaseDao;
import com.pos.dao.IGoodsDao;
import com.pos.dao.SqlConstant;
import com.pos.entity.Goods;
import com.pos.util.BeanMapper;

@Repository
public class GoodsDaoImpl extends BaseDao implements IGoodsDao {

	@Override
	public Goods getGoodsByBarcode(String barcode) {
		List<Goods> list = getPosJT().query(SqlConstant.GET_GOODS_BY_BARCODE, new Object[] { barcode },
				new BeanMapper(Goods.class));
		return list != null && list.size() > 0 ? list.get(0) : null;
	}
}
