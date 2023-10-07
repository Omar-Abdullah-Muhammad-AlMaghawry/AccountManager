package com.zfinance.dto.request.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SystemLimitBody {

	private String id;
	private String qualifier;
	private String timeUnit;
	private Double value;
	private Boolean active;
}
