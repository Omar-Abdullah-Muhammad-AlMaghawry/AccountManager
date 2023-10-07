package com.zfinance.dto.response.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ContractRecord {

	private String id;
	private String organizationType;
	private String personType;
	private String name;
	private String description;
	private Boolean active;
	private Boolean global;

}
