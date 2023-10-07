package com.zfinance.dto.request.contract;

import com.zfinance.orm.userdefinedtypes.contract.CommissionValue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionBody {

	private String txType;
	private CommissionValue providerCommission;
	private CommissionValue totalCommission;
	private Boolean active;
}
