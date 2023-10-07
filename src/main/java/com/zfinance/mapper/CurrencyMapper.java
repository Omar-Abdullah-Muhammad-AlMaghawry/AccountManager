package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.zfinance.dto.response.external.currency.Currency;
import com.zfinance.orm.userdefinedtypes.contract.ProviderCurrency;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper {

	CurrencyMapper INSTANCE = Mappers.getMapper(CurrencyMapper.class);

	public ProviderCurrency mapCurrency(Currency currency);

	public Currency mapProviderCurrency(ProviderCurrency providerCurrency);

	public List<ProviderCurrency> mapCurrencies(List<Currency> currencies);

}
