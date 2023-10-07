package com.zfinance.orm.contract;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.contract.CommissionValue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_commission_setting")
public class CommissionSetting {

	@Id
	@Field("id")
	private String id;

	@Field("active")
	private Boolean active;

	@Field("collector")
	private String collector;

	@Field("direction")
	private String direction;

	@Field("tx_type")
	private String txType;

	@Field("commission_direction")
	private String commissionDirection;

	@Field("value")
	private CommissionValue value;

	@Field("contract_id")
	private String contractId;

	@Field("profile_id")
	private String profileId;

	@Transient
	public static final String SEQUENCE_NAME = "commission_setting_sequence";

}
