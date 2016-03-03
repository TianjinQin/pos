package com.pos.dao.impl;

import java.util.List;

import com.pos.dao.BaseDao;
import com.pos.dao.IPreferentialDao;
import com.pos.dao.SqlConstant;
import com.pos.entity.Preferential;
import com.pos.util.BeanMapper;

public class PreferentialDaoImpl extends BaseDao implements IPreferentialDao {

	@Override
	public List<Preferential> getPreferentialsByBarcode(String barcode) {

		return getPosJT().query(SqlConstant.GET_PREFERENTIAL_BY_BARCODE, new Object[] { barcode },
				new BeanMapper(Preferential.class));
	}

	@Override
	public List<Preferential> getAllPreferentials() {
		return getPosJT().query(SqlConstant.GET_PREFERENTIAL_ALL, new BeanMapper(Preferential.class));
	}

}
