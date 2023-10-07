package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.contract.CommissionRecord;
import com.zfinance.orm.contract.Commission;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommissionMapper {

	CommissionMapper INSTANCE = Mappers.getMapper(CommissionMapper.class);

	public CommissionRecord mapCommission(Commission commission);

	public Commission mapCommissionRecord(CommissionRecord commissionRecord);

	public default Page<CommissionRecord> mapCommissions(Page<Commission> commissions) {
		return commissions.map(this::mapCommission);
	}

	public List<CommissionRecord> mapCommissions(List<Commission> commissions);

}
