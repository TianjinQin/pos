package com.pos.model;

public class GoodsSaleModel {

	private String barcode;
	private int total;

	public GoodsSaleModel(String barcode, int total) {
		this.barcode = barcode;
		this.total = total;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

}
