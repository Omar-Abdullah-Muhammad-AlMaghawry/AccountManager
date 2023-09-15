package com.zfinance.dto.request.coin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UpdateWalletBody {

	private String name;
	private Boolean active;
}
