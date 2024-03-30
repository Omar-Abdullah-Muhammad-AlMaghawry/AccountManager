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
	private List<String> payeeId;
	private String paymentId;
	private String payoneerPaymentId;
	private List<String> payeeName;
	private List<String> companyName;
	private String dateFrom;
	private String dateTo;
	private Double amountFrom;
	private Double amountTo;
	private List<String> status;
	private String description;
	private String groupId;
	private List<String> payoutMethod;
	private List<String> currencyCode;
}
