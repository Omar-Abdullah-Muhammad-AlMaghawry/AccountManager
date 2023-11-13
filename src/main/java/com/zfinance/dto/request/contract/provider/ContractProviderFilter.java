package com.zfinance.dto.request.contract.provider;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractProviderFilter {

	private String name;
	private String startDate;
	private String endDate;
	private String status;

}
