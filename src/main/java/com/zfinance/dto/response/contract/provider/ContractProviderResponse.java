package com.zfinance.dto.response.contract.provider;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractProviderResponse {
	private List<ContractProviderRecord> records;
}
