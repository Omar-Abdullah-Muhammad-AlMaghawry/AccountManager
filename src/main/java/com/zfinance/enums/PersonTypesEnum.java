package com.zfinance.enums;

public enum PersonTypesEnum {

	BASE("base"), STANDART("standart"), GOLD("gold"), VIP("vip");

	private String code;

	private PersonTypesEnum(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

}
