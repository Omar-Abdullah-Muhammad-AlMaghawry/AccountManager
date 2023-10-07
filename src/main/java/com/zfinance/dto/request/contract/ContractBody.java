package com.zfinance.dto.request.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ContractBody {

	private String contractId;
	private String userId;
	private String organizationId;

}
