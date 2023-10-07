package com.zfinance.dto.request.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ContractsSort {

	private String id;
	private String organizationType;
	private String personType;
	private String name;
	private String description;
	private Boolean active;
	private Boolean global;
}
