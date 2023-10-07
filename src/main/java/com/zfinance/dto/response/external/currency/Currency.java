package com.zfinance.dto.response.external.currency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Currency {

	private String id;
	private String code;
	private String digitalCode;
	private String symbol;
	private String name;
	private String description;

}
