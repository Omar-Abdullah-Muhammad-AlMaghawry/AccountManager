package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.contract.CommissionRuleRecord;
import com.zfinance.orm.contract.CommissionRule;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommissionRuleMapper {

	CommissionRuleMapper INSTANCE = Mappers.getMapper(CommissionRuleMapper.class);

	public CommissionRuleRecord mapCommissionRule(CommissionRule commissionRule);

	public CommissionRule mapCommissionRuleRecord(CommissionRuleRecord commissionRuleRecord);

	public default Page<CommissionRuleRecord> mapCommissionRules(Page<CommissionRule> commissionRules) {
		return commissionRules.map(this::mapCommissionRule);
	}

	public List<CommissionRuleRecord> mapCommissionRules(List<CommissionRule> commissionRules);

}
