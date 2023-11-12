package com.zfinance.repositories.exchange;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.exchange.ExchangeSuccess;

public interface ExchangeSuccessRepository extends MongoRepository<ExchangeSuccess, String> {

}
