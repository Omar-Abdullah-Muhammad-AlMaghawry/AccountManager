package com.zfinance.dto.request.coin;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class WalletBody {

	private String name;
	private String issuerId;
	private String type;

}
