package com.zfinance.dto.response.contract;

import com.zfinance.orm.userdefinedtypes.contract.CommissionValue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommissionSettingRecord {

	private String id;
	private Boolean active;
	private String collector;
	private String direction;
	private String txType;
	private String commissionDirection;
	private CommissionValue value;
}
