package com.zfinance.repositories.contract;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.contract.CommissionRule;

public interface CommissionRuleRepository extends MongoRepository<CommissionRule, String> {

	List<CommissionRule> findByProfileId(String profileId);

	List<CommissionRule> findByContractIdAndProfileId(String contractId, String profileId);

	CommissionRule findByIdAndContractIdAndProfileId(String id, String contractId, String profileId);

	List<CommissionRule> findByContractIdAndProfileIdAndBeginDateGreaterThanEqual(String contractId, String profileId,
			String fromDate);

}
