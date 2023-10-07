package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.contract.ProviderRecord;
import com.zfinance.orm.contract.Provider;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProviderMapper {

	ProviderMapper INSTANCE = Mappers.getMapper(ProviderMapper.class);

	public ProviderRecord mapProvider(Provider provider);

	public Provider mapProviderRecord(ProviderRecord providerRecord);

	public default Page<ProviderRecord> mapProviders(Page<Provider> providers) {
		return providers.map(this::mapProvider);
	}

	public List<ProviderRecord> mapProviders(List<Provider> providers);

}
