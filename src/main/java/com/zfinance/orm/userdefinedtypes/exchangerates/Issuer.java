package com.zfinance.orm.userdefinedtypes.exchangerates;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: maybe table also

@Setter
@Getter
@NoArgsConstructor
@UserDefinedType("issuer_type")
public class Issuer {

	@Column("id")
	private String id;

	@Column("sn")
	private String sn;

	@Column("symbol")
	private String symbol;

	@Column("currency")
	private String currency;

}
