package com.zfinance.orm.userdefinedtypes.transaction;

import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.exchangerates.CoinIssuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: IT COULD BE TABLE

@Setter
@Getter
@NoArgsConstructor
public class TransactionsTransaction {

	@Field("id")
	private String id;

	@Field("amount")
	private Double amount;

	@Field("to")
	private Target to;

	@Field("from")
	private Target from;

	@Field("issuer")
	private CoinIssuer issuer;

	@Field("performed_at")
	private String performedAt;

	@Field("type")
	private String type;

	@Field("updated_at")
	private String updatedAt;
}
