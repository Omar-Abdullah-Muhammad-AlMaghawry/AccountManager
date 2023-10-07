package com.zfinance.repositories.contract;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.contract.Commission;

public interface CommissionRepository extends MongoRepository<Commission, String> {

	List<Commission> findByContractId(String contractId);

	Commission findByIdAndContractId(String profileId, String contractId);

}
