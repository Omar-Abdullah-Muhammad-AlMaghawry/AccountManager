package com.zfinance.repositories.contract.currency.exchange;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.contract.currency.exchange.ContractCurrencyExchange;

public interface ContractCurrencyExchangeRepository extends MongoRepository<ContractCurrencyExchange, String> {

}
