package com.zfinance.repositories.contract;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.contract.Provider;

public interface ProviderRepository extends MongoRepository<Provider, String> {

	List<Provider> findByContractId(String contractId);

	Provider findByIdAndContractId(String profileId, String contractId);

}
