package com.zfinance.services.external;

import com.zfinance.dto.response.external.currency.Currency;

public interface CurrencyService {

	public Currency getCurrencyByCode(String code);

}
