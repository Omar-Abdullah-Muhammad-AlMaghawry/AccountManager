package com.zfinance.services.exchange.rates;

import java.util.List;

import com.zfinance.dto.request.exchange.rates.ExchangeRatesCreateBody;
import com.zfinance.dto.request.exchange.rates.ExchangeRatesViewBody;
import com.zfinance.dto.request.exchange.rates.SetExchangeRatePayload;
import com.zfinance.dto.response.exchange.rates.ExchangeRatesCalculateResponse;
import com.zfinance.orm.exchange.rates.ExchangeRate;
import com.zfinance.orm.exchange.rates.ExchangeRatesSuccess;

public interface ExchangeRatesService {

	public List<ExchangeRate> getRatesByInAndOutIssuerIds(ExchangeRatesViewBody exchangeRatesViewBody);

	public ExchangeRatesCalculateResponse exchangeCalculate(String rateId,
			ExchangeRatesCreateBody exchangeRatesCreateBody);

	public ExchangeRatesSuccess exchanges(String rateId, ExchangeRatesCreateBody exchangeRatesCreateBody);

	public ExchangeRate setExchangeRate(SetExchangeRatePayload setExchangeRatePayload);

	public ExchangeRate getExchangeRateById(String id);

}
