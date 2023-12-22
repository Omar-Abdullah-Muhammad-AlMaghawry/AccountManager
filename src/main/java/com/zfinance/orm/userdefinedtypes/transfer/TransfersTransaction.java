package com.zfinance.orm.userdefinedtypes.transfer;

import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;
import com.zfinance.orm.userdefinedtypes.transaction.Target;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransfersTransaction {

	@Field("id")
	private String id;

	@Field("amount")
	private Double amount;

	@Field("from")
	private Target from;

	@Field("issuer")
	private Issuer issuer;

	@Field("performed_at")
	private String performedAt;

	@Field("to")
	private Target to;

	@Field("type")
	private String type;

	@Field("updated_at")
	private String updatedAt;

}
