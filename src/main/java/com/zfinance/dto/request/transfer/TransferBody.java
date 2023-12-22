package com.zfinance.dto.request.transfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransferBody {
	private PaymentTool paymentTool;
	private Double amount;
	private String description;
}
