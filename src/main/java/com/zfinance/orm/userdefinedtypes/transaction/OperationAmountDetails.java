package com.zfinance.orm.userdefinedtypes.transaction;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@UserDefinedType("operation_amount_details_type")
public class OperationAmountDetails {

	@Column("sender_gross_amount")
	private Double senderGrossAmount;

	@Column("sender_net_amount")
	private Double senderNetAmount;

	@Column("sender_fee")
	private Double senderFee;

	@Column("recipient_gross_amount")
	private Double recipientGrossAmount;

	@Column("recipient_net_amount")
	private Double recipientNetAmount;

	@Column("recipient_fee")
	private Double recipientFee;
}
