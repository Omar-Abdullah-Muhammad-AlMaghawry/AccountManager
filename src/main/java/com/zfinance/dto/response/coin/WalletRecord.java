package com.zfinance.dto.response.coin;

import com.zfinance.orm.userdefinedtypes.exchangerates.Issuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WalletRecord {

	private String serial;
	private String name;
	private double amount;
	private double availableAmount;
	private double futureAmount;
	private double heldAmount;
	private double creditLimit;
	private Issuer issuer;
	private boolean active;
	private String type;
	private boolean main;
}
