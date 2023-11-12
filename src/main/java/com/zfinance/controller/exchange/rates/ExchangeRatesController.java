package com.zfinance.controller.exchange.rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.request.exchange.rates.ExchangeRatesCreateBody;
import com.zfinance.dto.request.exchange.rates.ExchangeRatesViewBody;
import com.zfinance.dto.request.exchange.rates.SetExchangeRatePayload;
import com.zfinance.dto.response.exchange.rates.ExchangeRateRecord;
import com.zfinance.dto.response.exchange.rates.ExchangeRatesCalculateResponse;
import com.zfinance.dto.response.exchange.rates.ExchangeRatesResponse;
import com.zfinance.dto.response.exchange.rates.ExchangeRatesSuccessResponse;
import com.zfinance.mapper.ExchangeRateMapper;
import com.zfinance.mapper.ExchangeRatesSuccessMapper;
import com.zfinance.services.exchange.rates.ExchangeRatesService;

@RestController
@RequestMapping("/exchange-rates")
@CrossOrigin("*")
public class ExchangeRatesController {

	@Autowired
	private ExchangeRatesService exchangeRatesService;

	@PostMapping("/view")
	public ExchangeRatesResponse getRates(@RequestBody ExchangeRatesViewBody data) {
		return new ExchangeRatesResponse(ExchangeRateMapper.INSTANCE.mapExchangeRates(exchangeRatesService
				.getRatesByInAndOutIssuerIds(data)));
	}

	@PostMapping("/{rateId}/exchanges/calculate")
	public ExchangeRatesCalculateResponse exchangeCalculate(@PathVariable String rateId,
			@RequestBody ExchangeRatesCreateBody data) {
		return exchangeRatesService.exchangeCalculate(rateId, data);

	}

	@PostMapping("/{rateId}/exchanges")
	public ExchangeRatesSuccessResponse exchanges(@PathVariable String rateId,
			@RequestBody ExchangeRatesCreateBody data) {
		return new ExchangeRatesSuccessResponse(ExchangeRatesSuccessMapper.INSTANCE.mapExchangeRatesSuccess(
				exchangeRatesService.exchanges(rateId, data)));
	}

	@PostMapping("/rate")
	public ExchangeRateRecord setExchangeRate(@RequestBody SetExchangeRatePayload data) {
		return ExchangeRateMapper.INSTANCE.mapExchangeRate(exchangeRatesService.setExchangeRate(data));
	}

}
