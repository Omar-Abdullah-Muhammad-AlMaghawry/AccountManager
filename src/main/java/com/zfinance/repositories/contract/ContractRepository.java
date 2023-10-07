package com.zfinance.repositories.contract;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.contract.Contract;

public interface ContractRepository extends MongoRepository<Contract, String> {

}
