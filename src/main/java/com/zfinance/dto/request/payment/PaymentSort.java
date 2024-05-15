package com.zfinance.dto.request.payment;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentSort {

	private String id;
	private String payeeId;
	private String merchantId;
	private String paymentId;
	private String payoneerPaymentId;
	private String payeeName;
	private String companyName;
	private String date;
	private String amount;
	private String status;
	private String description;
	private String groupId;
	private String payoutMethod;
	private String currencyCode;
}
