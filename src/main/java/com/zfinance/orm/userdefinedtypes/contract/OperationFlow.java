package com.zfinance.orm.userdefinedtypes.contract;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OperationFlow {

	@Field("id")
	private String id;

	@Field("code")
	private String code;

	@Field("transaction_type")
	private String transactionType;

	@Field("process_type")
	private String processType;

	@Field("src_coin_type")
	private String srcCoinType;

	@Field("dest_coin_type")
	private String destCoinType;

}
