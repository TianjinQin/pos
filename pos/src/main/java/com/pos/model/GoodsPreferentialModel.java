package com.pos.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.pos.service.IPreferentialService;

public class GoodsPreferentialModel {

	private String barcode;
	private Set<IPreferentialService> Preferential = new HashSet<IPreferentialService>();

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Set<IPreferentialService> getPreferential() {
		return Collections.unmodifiableSet(Preferential);
	}

	public void addPreferential(IPreferentialService Preferential) {
		this.Preferential.add(Preferential);
	}

	public void deletePreferential(String code) {

	}

}
