package com.zfinance.enums;

public enum CommissionTypeEnum {
	ZERO("zero"), FIXED("fixed"), PERCENT("percent");

	private String code;

	private CommissionTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
