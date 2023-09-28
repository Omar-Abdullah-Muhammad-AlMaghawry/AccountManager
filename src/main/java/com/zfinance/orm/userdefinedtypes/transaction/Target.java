package com.zfinance.orm.userdefinedtypes.transaction;

import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.exchangerates.CoinIssuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: IT COULD BE TABLE

@Setter
@Getter
@NoArgsConstructor
public class Target {

	@Field("serial")
	private String serial;

	@Field("issuer")
	private CoinIssuer issuer;

	@Field("name")
	private String name;

	@Field("organization_id")
	private String organizationId;

	@Field("organization_name")
	private String organizationName;

	@Field("technical")
	private Boolean technical;

	@Field("type")
	private String type;

}
