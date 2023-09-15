package com.zfinance.enums;

public enum CoinTypeEnum {
	APPROVED("APPROVED"), DECLINED("DECLINED"), PENDING("PENDING");

	private String code;

	private CoinTypeEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
