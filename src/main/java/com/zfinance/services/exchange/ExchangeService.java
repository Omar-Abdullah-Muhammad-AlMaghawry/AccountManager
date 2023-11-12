package com.zfinance.services.exchange;

import com.zfinance.dto.request.exchange.ExchangePayload;
import com.zfinance.dto.response.exchange.ExchangeCalculateResponse;
import com.zfinance.orm.exchange.ExchangeSuccess;

public interface ExchangeService {

	public ExchangeCalculateResponse exchangeCalculate(ExchangePayload exchangePayload);

	public ExchangeSuccess exchanges(ExchangePayload exchangePayload);

}
