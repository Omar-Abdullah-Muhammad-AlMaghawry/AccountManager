package com.zfinance.enums;

public enum ExpirationStatusEnum {

	ACTIVE("ACTIVE"), EXPIRED("EXPIRED");

	private String code;

	private ExpirationStatusEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
