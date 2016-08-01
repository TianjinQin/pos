package com.pos.demo;

public class OracleDialect implements Dialect {

	@Override
	public String getLimitString(String sql, int skipResults, int maxResults) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isMatchType(String type) {
		// TODO Auto-generated method stub
		return "oracle".equalsIgnoreCase(type);
	}

}
