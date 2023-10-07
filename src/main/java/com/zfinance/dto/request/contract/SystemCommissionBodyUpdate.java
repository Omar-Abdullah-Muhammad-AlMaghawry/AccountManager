package com.zfinance.dto.request.contract;

import com.zfinance.orm.userdefinedtypes.contract.CommissionValue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SystemCommissionBodyUpdate {

	private String direction;
	private Boolean active;
	private CommissionValue value;
}
