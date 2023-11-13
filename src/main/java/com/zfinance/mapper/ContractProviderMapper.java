package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.contract.provider.ContractProviderRecord;
import com.zfinance.orm.contract.provider.ContractProvider;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractProviderMapper {

	ContractProviderMapper INSTANCE = Mappers.getMapper(ContractProviderMapper.class);

	public ContractProviderRecord mapContractProvider(ContractProvider contractProvider);

	public ContractProvider mapContractProviderRecord(ContractProviderRecord contractProviderRecord);

	public default Page<ContractProviderRecord> mapContractProviders(Page<ContractProvider> contractProviders) {
		return contractProviders.map(this::mapContractProvider);
	}

	public List<ContractProviderRecord> mapContractProviders(List<ContractProvider> contractProviders);

}
