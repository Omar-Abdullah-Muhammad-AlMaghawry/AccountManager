package com.zfinance.dto.response.coin;

import java.util.List;

import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;
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
	private Double amount;
	private Double availableAmount;
	private Double futureAmount;
	private Double heldAmount;
	private Double creditLimit;
	private Issuer issuer;
	private List<SmartCardShort> smartCards;
	private Boolean active;
	private String type;
	private Boolean main;
	private String image;
}
