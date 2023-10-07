package com.zfinance.enums;

public enum CollectorTypeEnum {

	TOTAL("TOTAL"), PROVIDER("PROVIDER");

	private String code;

	private CollectorTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
