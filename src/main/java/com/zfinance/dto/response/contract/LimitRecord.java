package com.zfinance.dto.response.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LimitRecord {

	private String id;
	private Boolean active;
	private String gateProfileId;
	private String qualifier;
	private String timeUnit;
	private String txType;
	private Double value;
}
