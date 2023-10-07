package com.zfinance.dto.response.contract;

import com.zfinance.orm.userdefinedtypes.contract.ProviderCurrency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProviderRecord { // is the same as commission profile

	private String id;
	private String gateProviderId;
	private ProviderCurrency providerCurrency;
	private String createdAt;
	private String updatedAt;

}
