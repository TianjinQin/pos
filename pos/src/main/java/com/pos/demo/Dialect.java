package com.pos.demo;

public interface Dialect {

	boolean isMatchType(String type);

	String getLimitString(String sql, int skipResults, int maxResults);

}
