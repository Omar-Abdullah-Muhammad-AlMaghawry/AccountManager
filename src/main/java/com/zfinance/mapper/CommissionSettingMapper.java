package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.contract.CommissionSettingRecord;
import com.zfinance.orm.contract.CommissionSetting;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CommissionSettingMapper {

	CommissionSettingMapper INSTANCE = Mappers.getMapper(CommissionSettingMapper.class);

	public CommissionSettingRecord mapCommissionSetting(CommissionSetting contract);

	public CommissionSetting mapCommissionSettingRecord(CommissionSettingRecord commissionSettingRecord);

	public default Page<CommissionSettingRecord> mapCommissionSettings(Page<CommissionSetting> commissionSettings) {
		return commissionSettings.map(this::mapCommissionSetting);
	}

	public List<CommissionSettingRecord> mapCommissionSettings(List<CommissionSetting> commissionSettings);

}
