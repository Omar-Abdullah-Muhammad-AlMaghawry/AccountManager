package com.zfinance.dto.request.transfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaymentTool {
	private String type;
	private String srcValue;
	private String destValue;
}
