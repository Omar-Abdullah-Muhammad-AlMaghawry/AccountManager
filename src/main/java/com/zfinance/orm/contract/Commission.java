package com.zfinance.orm.contract;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.contract.CommissionDestParticipantSpecification;
import com.zfinance.orm.userdefinedtypes.contract.CommissionIssuer;
import com.zfinance.orm.userdefinedtypes.contract.CommissionSrcParticipantSpecification;
import com.zfinance.orm.userdefinedtypes.contract.CommissionValue;
import com.zfinance.orm.userdefinedtypes.contract.OperationFlow;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_commission")
public class Commission {

	@Id
	@Field("id")
	private String id;

	@Field("active")
	private Boolean active;

	@Field("created_at")
	private String createdAt;

	@Field("updated_at")
	private String updatedAt;

	@Field("dest_participant_specification")
	private CommissionDestParticipantSpecification destParticipantSpecification;

	@Field("direction")
	private String direction;

	@Field("flow")
	private OperationFlow flow;

	@Field("issuer")
	private CommissionIssuer issuer;

	@Field("src_participant_specification")
	private CommissionSrcParticipantSpecification srcParticipantSpecification;

	@Field("value")
	private CommissionValue value;

	@Field("contract_id")
	private String contractId;

	@Transient
	public static final String SEQUENCE_NAME = "commission_sequence";

}
