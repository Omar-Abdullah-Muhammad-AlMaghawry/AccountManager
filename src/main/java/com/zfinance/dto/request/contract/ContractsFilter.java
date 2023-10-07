package com.zfinance.dto.request.contract;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ContractsFilter {

	private String id;
	private List<String> types;
	private Boolean global;
	private List<String> personTypes;
	private String dateFrom;
	private String dateTo;
	private String description;

}
