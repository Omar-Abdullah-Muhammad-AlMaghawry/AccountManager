package com.zfinance.orm.coin;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;
import com.zfinance.orm.userdefinedtypes.smartcards.SmartCardShort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_wallet")
public class Wallet {

	@Id
	@Field("serial")
	private String serial;

	@Field("name")
	private String name;

	@Field("amount")
	private Double amount;

	@Field("available_amount")
	private Double availableAmount;

	@Field("future_amount")
	private Double futureAmount;

	@Field("held_amount")
	private Double heldAmount;

	@Field("credit_limit")
	private Double creditLimit;

	@Field("issuer")
	private Issuer issuer;

	@Field("smart_cards")
	private List<SmartCardShort> smartCards;

	@Field("active")
	private Boolean active;

	@Field("type")
	private String type;

	@Field("main")
	private Boolean main;

	@Field("image")
	private String image;

	@Field("organization_id")
	private String organizationId;

	@Field("user_id")
	private String userId;

	@Transient
	public static final String SEQUENCE_NAME = "wallet_sequence";

}
