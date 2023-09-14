package com.zfinance.orm.userdefinedtypes.coin;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: maybe table also

@Setter
@Getter
@NoArgsConstructor
@UserDefinedType("wallet_owner_type")
public class WalletOwner {

	@Column("id")
	private String id;

	@Column("status")
	private String status;

	@Column("message")
	private String message;

	@Column("type")
	private String type;

	@Column("name")
	private String name;

	@Column("identification_status")
	private String identificationStatus;

	@Column("contract_info")
	private ContractInfo contractInfo;
}
