package com.zfinance.orm.userdefinedtypes.coin;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: maybe table also

@Setter
@Getter
@NoArgsConstructor
public class WalletOwner {

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
