package com.zfinance.dto.request.contract;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LimitBodyUpdate {

	private Double value;
	private Boolean active;

}
