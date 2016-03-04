package com.pos.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class PrintInfo {

	private BigDecimal totalRealPrice = BigDecimal.ZERO;
	private BigDecimal totalBalancePrice = BigDecimal.ZERO;

	private List<String> alternative = new ArrayList<>();
	private List<String> normal = new ArrayList<>();

	public BigDecimal getTotalRealPrice() {
		return totalRealPrice;
	}

	public void setTotalRealPrice(BigDecimal totalRealPrice) {
		this.totalRealPrice = totalRealPrice;
	}

	public BigDecimal getTotalBalancePrice() {
		return totalBalancePrice;
	}

	public void setTotalBalancePrice(BigDecimal totalBalancePrice) {
		this.totalBalancePrice = totalBalancePrice;
	}

	public List<String> getAlternative() {
		return Collections.unmodifiableList(alternative);
	}

	public void addAlternative(String alternativeStr) {
		alternative.add(alternativeStr);
	}

	public void addTotalRealPrice(BigDecimal totalRealPrice) {

		this.totalRealPrice = this.totalRealPrice.add(totalRealPrice);
	}

	public void addTotalBalancePrice(BigDecimal totalBalancePrice) {
		this.totalBalancePrice = this.totalBalancePrice.add(totalBalancePrice);
	}

	public List<String> getNormal() {
		return Collections.unmodifiableList(normal);
	}

	public void addNormal(String normalStr) {
		normal.add(normalStr);
	}

	public void print() {
		print("***<没钱赚商店>购物清单***");
		printNormal();
		printAlternative();
		printToal();
		print("**********************");

	}

	private void print(String str) {
		System.out.println(str);
	}

	private void printAlternative() {
		if (alternative.size() <= 0)
			return;
		print("买二赠一商品：");
		for (Iterator iterator = alternative.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			print(string);
		}
		print("----------------------");
	}

	private void printNormal() {
		for (Iterator iterator = normal.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			print(string);
		}
		print("----------------------");
	}

	private void printToal() {
		print("总计:" + totalRealPrice + "(元)");
		if (totalBalancePrice.compareTo(BigDecimal.ZERO) > 0) {
			print("节省:" + totalBalancePrice + "(元)");
		}
	}

}
