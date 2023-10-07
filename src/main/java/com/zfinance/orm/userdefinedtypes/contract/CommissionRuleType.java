package com.zfinance.orm.userdefinedtypes.contract;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionRuleType {

	@Field("type")
	private String type;

	@Field("value_fixed")
	private String valueFixed;

	@Field("value_percent")
	private String valuePercent;
}
