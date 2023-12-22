package com.zfinance.dto.response.transfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaymentToolDetails {
	private String currency;
	private String destId;
	private String destValue;
	private String recipientFullName;
	private String srcId;
	private String srcValue;
	private String symbol;
	private String type;
}
