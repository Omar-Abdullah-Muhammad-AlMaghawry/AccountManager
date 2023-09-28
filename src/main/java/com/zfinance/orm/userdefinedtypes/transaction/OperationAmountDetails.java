package com.zfinance.orm.userdefinedtypes.transaction;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class OperationAmountDetails {

	@Field("sender_gross_amount")
	private Double senderGrossAmount;

	@Field("sender_net_amount")
	private Double senderNetAmount;

	@Field("sender_fee")
	private Double senderFee;

	@Field("recipient_gross_amount")
	private Double recipientGrossAmount;

	@Field("recipient_net_amount")
	private Double recipientNetAmount;

	@Field("recipient_fee")
	private Double recipientFee;
}
