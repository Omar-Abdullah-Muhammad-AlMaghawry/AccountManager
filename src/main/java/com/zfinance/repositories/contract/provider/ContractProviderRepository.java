package com.zfinance.repositories.contract.provider;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.contract.provider.ContractProvider;

public interface ContractProviderRepository extends MongoRepository<ContractProvider, String> {

}
