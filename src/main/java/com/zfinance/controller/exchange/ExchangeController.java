package com.zfinance.controller.exchange;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.request.exchange.ExchangePayload;
import com.zfinance.dto.response.exchange.ExchangeCalculateResponse;
import com.zfinance.dto.response.exchange.ExchangeSuccessResponse;
import com.zfinance.mapper.ExchangeSuccessMapper;
import com.zfinance.services.exchange.ExchangeService;

@RestController
@RequestMapping("/exchange")
@CrossOrigin("*")
public class ExchangeController {

	@Autowired
	private ExchangeService exchangeService;

	@PostMapping("/calculator")
	public ExchangeCalculateResponse exchangeCalculate(@RequestBody ExchangePayload data) {
		return exchangeService.exchangeCalculate(data);

	}

	@PostMapping
	public ExchangeSuccessResponse exchanges(@RequestBody ExchangePayload data) {
		return new ExchangeSuccessResponse(ExchangeSuccessMapper.INSTANCE.mapExchangeSuccess(exchangeService.exchanges(
				data)));
	}

}
