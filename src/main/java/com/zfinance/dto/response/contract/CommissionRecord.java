package com.zfinance.dto.response.contract;

import com.zfinance.orm.userdefinedtypes.contract.CommissionDestParticipantSpecification;
import com.zfinance.orm.userdefinedtypes.contract.CommissionFlow;
import com.zfinance.orm.userdefinedtypes.contract.CommissionIssuer;
import com.zfinance.orm.userdefinedtypes.contract.CommissionSrcParticipantSpecification;
import com.zfinance.orm.userdefinedtypes.contract.CommissionValue;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CommissionRecord {

	private String id;
	private Boolean active;
	private String createdAt;
	private String updatedAt;
	private CommissionDestParticipantSpecification destParticipantSpecification;
	private String direction;
	private CommissionFlow flow;
	private CommissionIssuer issuer;
	private CommissionSrcParticipantSpecification srcParticipantSpecification;
	private CommissionValue value;
}
