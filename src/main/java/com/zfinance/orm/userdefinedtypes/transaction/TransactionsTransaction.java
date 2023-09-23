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
@UserDefinedType("transactions_transaction_type")
public class TransactionsTransaction {

	@Column("id")
	private String id;

	@Column("amount")
	private Double amount;

	@Column("to")
	private Target to;

	@Column("from")
	private Target from;

	@Column("issuer")
	private CoinIssuer issuer;

	@Column("performed_at")
	private String performedAt;

	@Column("type")
	private String type;

	@Column("updated_at")
	private String updatedAt;
}
