package com.zfinance.orm.coin;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.zfinance.orm.userdefinedtypes.exchangerates.CoinIssuer;
import com.zfinance.orm.userdefinedtypes.smartcards.SmartCardShort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table("zfin_wallet")
public class Wallet {

	@PrimaryKey
	@Column("serial")
	private String serial;

	@Column("name")
	private String name;

	@Column("amount")
	private Double amount;

	@Column("available_amount")
	private Double availableAmount;

	@Column("future_amount")
	private Double futureAmount;

	@Column("held_amount")
	private Double heldAmount;

	@Column("credit_limit")
	private Double creditLimit;

	@Column("issuer")
	private CoinIssuer issuer;

	@Column("smart_cards")
	private List<SmartCardShort> smartCards;

	@Column("active")
	private Boolean active;

	@Column("type")
	private String type;

	@Column("main")
	private Boolean main;

	@Column("image")
	private String image;

	@Column("organization_id")
	private String organizationId;
}
