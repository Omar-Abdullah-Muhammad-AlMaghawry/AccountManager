package com.zfinance.orm.contract;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_limit")
public class Limit {

	@Id
	@Field("id")
	private String id;

	@Field("active")
	private Boolean active;

	@Field("gate_profile_id")
	private String gateProfileId;

	@Field("qualifier")
	private String qualifier;

	@Field("time_unit")
	private String timeUnit;

	@Field("tx_type")
	private String txType;

	@Field("value")
	private Double value;

	@Field("contract_id")
	private String contractId;

	@Field("profile_id")
	private String profileId;

	@Transient
	public static final String SEQUENCE_NAME = "limit_sequence";

}
