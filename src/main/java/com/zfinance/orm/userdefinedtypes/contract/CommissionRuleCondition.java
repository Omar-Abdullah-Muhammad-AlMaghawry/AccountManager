package com.zfinance.orm.userdefinedtypes.contract;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionRuleCondition {

	@Field("id")
	private String id;

	@Field("begin_amount")
	private Double beginAmount;

	@Field("end_amount")
	private Double endAmount;

	@Field("value")
	private CommissionRuleType value;
}
