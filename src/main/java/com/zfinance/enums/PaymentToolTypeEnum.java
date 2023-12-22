package com.zfinance.enums;

public enum PaymentToolTypeEnum {
	COIN("COIN"), SMART_CARD("SMART_CARD");

	private String code;

	private PaymentToolTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
