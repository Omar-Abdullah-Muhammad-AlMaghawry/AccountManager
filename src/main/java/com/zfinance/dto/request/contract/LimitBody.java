package com.zfinance.dto.request.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LimitBody {

	private String id;
	private String txType;
	private String productId;
	private String qualifier;
	private String timeUnit;
	private Double value;
	private Boolean active;
}
