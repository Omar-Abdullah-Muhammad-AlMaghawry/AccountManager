package com.zfinance.enums;

public enum PaymentStatusEnum {

	PENDING("PEDNING"), TRANSFERRED("TRANSFERRED"), CANCELLED("CANCELLED"), PENDING_PAYEE("PENDING_PAYEE");

	private String code;

	private PaymentStatusEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}
