package com.zfinance.dto.request.contract;

import com.zfinance.orm.userdefinedtypes.contract.CommissionDestParticipantSpecification;
import com.zfinance.orm.userdefinedtypes.contract.CommissionSrcParticipantSpecification;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateSystemCommission {

	private String issuerId;
	private String operationFlowId;
	private CommissionSrcParticipantSpecification srcParticipantSpecification;
	private CommissionDestParticipantSpecification destParticipantSpecification;

}
