package com.zfinance.dto.response.coin;

import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WalletRecord {

	private String serial;
	private String name;
	private Double amount;
	private Double availableAmount;
	private Double futureAmount;
	private Double heldAmount;
	private Double creditLimit;
	private Issuer issuer;
	private Boolean active;
	private String type;
	private Boolean main;
}
