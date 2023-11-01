package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.contract.currency.exchange.ContractCurrencyExchangeRecord;
import com.zfinance.orm.contract.currency.exchange.ContractCurrencyExchange;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContractCurrencyExchangeMapper {

	ContractCurrencyExchangeMapper INSTANCE = Mappers.getMapper(ContractCurrencyExchangeMapper.class);

	public ContractCurrencyExchangeRecord mapContractCurrencyExchange(
			ContractCurrencyExchange contractCurrencyExchange);

	public ContractCurrencyExchange mapContractCurrencyExchangeRecord(
			ContractCurrencyExchangeRecord contractCurrencyExchangeRecord);

	public default Page<ContractCurrencyExchangeRecord> mapContractCurrencyExchanges(
			Page<ContractCurrencyExchange> contractCurrencyExchanges) {
		return contractCurrencyExchanges.map(this::mapContractCurrencyExchange);
	}

	public List<ContractCurrencyExchangeRecord> mapContractCurrencyExchanges(
			List<ContractCurrencyExchange> contractCurrencyExchanges);

}
