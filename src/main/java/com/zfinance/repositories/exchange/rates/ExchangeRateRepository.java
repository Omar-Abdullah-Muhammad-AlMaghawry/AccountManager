package com.zfinance.repositories.exchange.rates;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.exchange.rates.ExchangeRate;

public interface ExchangeRateRepository extends MongoRepository<ExchangeRate, String> {

}
