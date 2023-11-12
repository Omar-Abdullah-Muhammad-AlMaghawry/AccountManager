package com.zfinance.orm.userdefinedtypes.exchange.rates;

import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.coin.ContractInfo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExchangeRatesExchanger {

	@Field("id")
	private String id;

	@Field("status")
	private String status;

	@Field("message")
	private String message;

	@Field("type")
	private String type;

	@Field("name")
	private String name;

	@Field("identification_status")
	private String identificationStatus;

	@Field("contract_info")
	private ContractInfo contractInfo;

}
