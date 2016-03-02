package com.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

/**
 * 数据访问基类 提供JdbcTemplate
 * 
 * @author qintj
 *
 */
@Repository
public class BaseDao {

	@Autowired
	private JdbcTemplate posJT;

	public JdbcTemplate getPosJT() {
		return posJT;
	}

	public void setPosJT(JdbcTemplate posJT) {
		this.posJT = posJT;
	}

	public int execute(JdbcTemplate template, final String sql, final Object... args) {
		return template.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				for (int i = 0; i < args.length; i++) {
					ps.setObject(i + 1, args[i]);
				}
				return ps;
			}
		});
	}
}
