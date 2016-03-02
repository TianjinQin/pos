package com.pos.entity;

import java.math.BigDecimal;

/**
 * 商品信息
 * 
 * @author qintj
 *
 */
public class Goods {

	private Long id;
	private String name;
	private String unit;
	private BigDecimal price;
	private String barcode;
	private String category;

	public Goods() {
		// TODO Auto-generated constructor stub
	}

	public Goods(String name, String unit, BigDecimal price, String barcode, String category) {
		this.name = name;
		this.unit = unit;
		this.price = price;
		this.barcode = barcode;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
