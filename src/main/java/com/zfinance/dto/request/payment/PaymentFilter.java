package com.zfinance.dto.request.payment;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentFilter {

	private String id;
	private String payeeId;
	private String paymentId;
	private String payoneerPaymentId;
	private String payeeName;
	private String companyName;
	private String dateFrom;
	private String dateTo;
	private Double amountFrom;
	private Double amountTo;
	private List<String> status;
	private String description;
	private String groupId;
	private String payoutMethod;
	private List<String> currencyCode;
}
