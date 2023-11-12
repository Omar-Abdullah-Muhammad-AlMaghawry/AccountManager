package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.exchange.rates.ExchangeRateRecord;
import com.zfinance.orm.exchange.rates.ExchangeRate;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExchangeRateMapper {

	ExchangeRateMapper INSTANCE = Mappers.getMapper(ExchangeRateMapper.class);

	public ExchangeRateRecord mapExchangeRate(ExchangeRate exchangeRate);

	public ExchangeRate mapExchangeRateRecord(ExchangeRateRecord exchangeRateRecord);

	public default Page<ExchangeRateRecord> mapExchangeRates(Page<ExchangeRate> exchangeRates) {
		return exchangeRates.map(this::mapExchangeRate);
	}

	public List<ExchangeRateRecord> mapExchangeRates(List<ExchangeRate> exchangeRates);

}
