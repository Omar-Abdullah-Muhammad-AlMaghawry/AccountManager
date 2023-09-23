package com.zfinance.dto.request.collect;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CollectBody {

	private String serial;
	private Double amount;
	private String fullName;
}
