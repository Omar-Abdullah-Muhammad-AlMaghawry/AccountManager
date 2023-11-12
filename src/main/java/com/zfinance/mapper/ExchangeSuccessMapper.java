package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.exchange.ExchangeSuccessRecord;
import com.zfinance.orm.exchange.ExchangeSuccess;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExchangeSuccessMapper {

	ExchangeSuccessMapper INSTANCE = Mappers.getMapper(ExchangeSuccessMapper.class);

	public ExchangeSuccessRecord mapExchangeSuccess(ExchangeSuccess exchangeSuccess);

	public ExchangeSuccess mapExchangeSuccessRecord(ExchangeSuccessRecord exchangeSuccessRecord);

	public default Page<ExchangeSuccessRecord> mapExchangeSuccesses(Page<ExchangeSuccess> exchangeSuccesses) {
		return exchangeSuccesses.map(this::mapExchangeSuccess);
	}

	public List<ExchangeSuccessRecord> mapExchangeSuccesses(List<ExchangeSuccess> exchangeSuccesses);

}
