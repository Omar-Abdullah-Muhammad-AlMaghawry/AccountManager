package com.zfinance.repositories.contract;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.contract.Limit;

public interface LimitsRepository extends MongoRepository<Limit, String> {

	List<Limit> findByProfileId(String profileId);

	List<Limit> findByContractIdAndProfileId(String contractId, String profileId);

	Limit findByIdAndContractIdAndProfileId(String id, String contractId, String profileId);

}
