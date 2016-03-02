package com.pos.model;


public class GoodsSalesModel {

	private String barcode;
	private Integer number;

	public GoodsSalesModel(String barcode, Integer number) {
		this.barcode = barcode;
		this.number = number;
	}

	public GoodsSalesModel() {

	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

}
