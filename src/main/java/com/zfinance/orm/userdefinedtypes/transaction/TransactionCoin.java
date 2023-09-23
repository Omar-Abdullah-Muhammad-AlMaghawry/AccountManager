package com.zfinance.orm.userdefinedtypes.transaction;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.zfinance.orm.userdefinedtypes.exchangerates.CoinIssuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: IT COULD BE TABLE

@Setter
@Getter
@NoArgsConstructor
@UserDefinedType("transaction_coin_type")
public class TransactionCoin {

	@Column("serial")
	private String serial;

	@Column("issuer")
	private CoinIssuer issuer;

	@Column("name")
	private String name;

	@Column("organization_id")
	private String organizationId;

	@Column("organization_name")
	private String organizationName;

	@Column("technical")
	private Boolean technical;

	@Column("type")
	private String type;

}
