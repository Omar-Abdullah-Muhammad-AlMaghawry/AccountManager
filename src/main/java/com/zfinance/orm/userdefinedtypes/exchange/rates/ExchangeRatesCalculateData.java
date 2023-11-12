package com.zfinance.orm.userdefinedtypes.exchange.rates;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExchangeRatesCalculateData {

	@Field("transaction_amount")
	private Double transactionAmount;

	@Field("sender_amount_push")
	private Double senderAmountPush;

	@Field("recipient_amount_push")
	private Double recipientAmountPush;

	@Field("commission_amount_push")
	private Double commissionAmountPush;

	@Field("issuer")
	private Issuer issuer;
}
