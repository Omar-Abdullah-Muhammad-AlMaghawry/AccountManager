package com.zfinance.dto.response.transfer;

import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransferCalculateResponse {
	private String status;
	private String message;
	private Double transactionAmount;
	private Double senderAmountPush;
	private PaymentToolDetails paymentToolDetails;
	private Double recipientAmountPush;
	private Double commissionAmountPush;
	private Issuer issuer;
}
