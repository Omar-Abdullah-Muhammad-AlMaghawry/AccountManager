package com.zfinance.orm.contract;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.contract.ProviderCurrency;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_provider")
public class Provider { // is the same as commission profile

	@Id
	@Field("id")
	private String id;

	@Field("gate_provider_id")
	private String gateProviderId;

	@Field("provider_currency")
	private ProviderCurrency providerCurrency;

	@Field("created_at")
	private String createdAt;

	@Field("updated_at")
	private String updatedAt;

	@Field("contract_id")
	private String contractId;

	@Transient
	public static final String SEQUENCE_NAME = "provider_sequence";

}
