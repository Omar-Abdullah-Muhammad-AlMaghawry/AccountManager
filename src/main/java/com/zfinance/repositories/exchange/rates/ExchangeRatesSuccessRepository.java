package com.zfinance.repositories.exchange.rates;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.exchange.rates.ExchangeRatesSuccess;

public interface ExchangeRatesSuccessRepository extends MongoRepository<ExchangeRatesSuccess, String> {

}
