package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.contract.LimitRecord;
import com.zfinance.orm.contract.Limit;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LimitMapper {

	LimitMapper INSTANCE = Mappers.getMapper(LimitMapper.class);

	public LimitRecord mapLimit(Limit limits);

	public Limit mapLimitRecord(LimitRecord limitsRecord);

	public default Page<LimitRecord> mapLimits(Page<Limit> limits) {
		return limits.map(this::mapLimit);
	}

	public List<LimitRecord> mapLimits(List<Limit> limits);

}
