package com.pos.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.pos.service.IPrivilegeService;

public class GoodsSalesModel {

	private String barcode;
	private Integer number;
	private Set<IPrivilegeService> privilegeService = new HashSet<IPrivilegeService>();

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

	public void add(IPrivilegeService privilegeService) {
		this.privilegeService.add(privilegeService);
	}

	public Set<IPrivilegeService> getPrivilegeService() {
		return Collections.unmodifiableSet(privilegeService);
	}

}
