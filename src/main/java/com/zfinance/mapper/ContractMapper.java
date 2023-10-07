package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.contract.ContractRecord;
import com.zfinance.orm.contract.Contract;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractMapper {

	ContractMapper INSTANCE = Mappers.getMapper(ContractMapper.class);

	public ContractRecord mapContract(Contract contract);

	public Contract mapContractRecord(ContractRecord contractRecord);

	public default Page<ContractRecord> mapContracts(Page<Contract> contracts) {
		return contracts.map(this::mapContract);
	}

	public List<ContractRecord> mapContracts(List<Contract> contracts);

}
