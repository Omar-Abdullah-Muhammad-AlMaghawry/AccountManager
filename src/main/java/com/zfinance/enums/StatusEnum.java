package com.zfinance.enums;

public enum StatusEnum {
	APPROVED("APPROVED"), DECLINED("DECLINED"), PENDING("PENDING");

	private String code;

	private StatusEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
