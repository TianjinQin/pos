package com.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

/**
 * 数据访问基类 提供JdbcTemplate
 *
 * @author qintj
 *
 */
public class BaseDao {

	private JdbcTemplate posJT;

	public JdbcTemplate getPosJT() {
		return posJT;
	}

	public void setPosJT(JdbcTemplate posJT) {
		this.posJT = posJT;
	}

	public int execute(JdbcTemplate template, final String sql, final Object... args) {
		return template.update(new PreparedStatementCreator() {
			@Override
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
