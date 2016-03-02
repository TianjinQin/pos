package com.pos.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.pos.service.IPrivilegeService;

public class GoodsPrivilegeModel {

	private String barcode;
	private Set<IPrivilegeService> privilege = new HashSet<IPrivilegeService>();

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Set<IPrivilegeService> getPrivilege() {
		return Collections.unmodifiableSet(privilege);
	}

	public void addPrivilege(IPrivilegeService privilege) {
		this.privilege.add(privilege);
	}

	public void deletePrivilege(String code) {

	}

}
