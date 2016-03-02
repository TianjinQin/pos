package com.pos.dao;

public class SqlConstant {

	public static final String GET_GOODS_BY_BARCODE = "select * from goods where barcode=?";
	public static final String GET_PREFERENTIAL_BY_BARCODE = "SELECT preferential.* from goods_preferential join preferential on goods_preferential.`code`=preferential.`code` and goods_preferential.barcode=? ORDER BY preferential.priority desc";

	public static final String GET_PREFERENTIAL_ALL = "SELECT * from preferential ORDER BY preferential.priority desc";
}
