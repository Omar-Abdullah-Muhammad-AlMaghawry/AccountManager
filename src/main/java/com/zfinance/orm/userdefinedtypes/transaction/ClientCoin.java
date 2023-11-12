package com.zfinance.orm.userdefinedtypes.transaction;

import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ClientCoin {

	@Field("serial")
	private String serial;

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

	@Field("issuer")
	private Issuer issuer;
}
