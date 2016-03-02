package com.pos.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.pos.dao.BaseDao;
import com.pos.dao.IPreferentialDao;
import com.pos.dao.SqlConstant;
import com.pos.entity.Preferential;

@Repository
public class PreferentialDaoImpl extends BaseDao implements IPreferentialDao {

	@Override
	public List<Preferential> getPreferentialsByBarcode(String barcode) {

		return getPosJT().query(SqlConstant.GET_PREFERENTIAL_BY_BARCODE, new Object[] { barcode },
				new PreferentialMapper());
	}

	@Override
	public List<Preferential> getAllPreferentials() {
		return getPosJT().query(SqlConstant.GET_PREFERENTIAL_ALL, new PreferentialMapper());
	}

	private static class PreferentialMapper implements RowMapper<Preferential> {

		@Override
		public Preferential mapRow(ResultSet rs, int rowNum) throws SQLException {
			Preferential preferential = new Preferential();
			preferential.setCode(rs.getString("code"));
			preferential.setName(rs.getString("name"));
			return preferential;
		}

	}

}
