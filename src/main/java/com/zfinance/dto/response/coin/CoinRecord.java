package com.zfinance.dto.response.coin;

import java.util.List;

import com.zfinance.orm.userdefinedtypes.exchangerates.CoinIssuer;
import com.zfinance.orm.userdefinedtypes.smartcards.SmartCardShort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CoinRecord {

	private String serial;
	private String name;
	private double amount;
	private double availableAmount;
	private double futureAmount;
	private double heldAmount;
	private double creditLimit;
	private CoinIssuer issuer;
	private List<SmartCardShort> smartCards;
	private boolean active;
	private String type;
	private boolean main;
	private String image;
}
