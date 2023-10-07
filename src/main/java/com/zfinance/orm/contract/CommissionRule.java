package com.zfinance.orm.contract;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.contract.CommissionRuleCondition;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_commission_rule")
public class CommissionRule {

	@Id
	@Field("id")
	private String id;

	@Field("begin_date")
	private String beginDate;

	@Field("end_date")
	private String endDate;

	@Field("fee_direction")
	private String feeDirection;

	@Field("active")
	private Boolean active;

	@Field("conditions")
	private List<CommissionRuleCondition> conditions;

	@Field("contract_id")
	private String contractId;

	@Field("profile_id")
	private String profileId;

	@Transient
	public static final String SEQUENCE_NAME = "commission_rule_sequence";

}
