package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.exchange.rates.ExchangeRatesSuccessRecord;
import com.zfinance.orm.exchange.rates.ExchangeRatesSuccess;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExchangeRatesSuccessMapper {

	ExchangeRatesSuccessMapper INSTANCE = Mappers.getMapper(ExchangeRatesSuccessMapper.class);

	public ExchangeRatesSuccessRecord mapExchangeRatesSuccess(ExchangeRatesSuccess exchangeRatesSuccess);

	public ExchangeRatesSuccess mapExchangeRatesSuccessRecord(ExchangeRatesSuccessRecord exchangeRatesSuccessRecord);

	public default Page<ExchangeRatesSuccessRecord> mapExchangeRatesSuccesses(
			Page<ExchangeRatesSuccess> exchangeRatesSuccesses) {
		return exchangeRatesSuccesses.map(this::mapExchangeRatesSuccess);
	}

	public List<ExchangeRatesSuccessRecord> mapExchangeRatesSuccesses(
			List<ExchangeRatesSuccess> exchangeRatesSuccesses);

}
