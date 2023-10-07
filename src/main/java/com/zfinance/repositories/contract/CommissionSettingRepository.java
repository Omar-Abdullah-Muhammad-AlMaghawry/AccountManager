package com.zfinance.repositories.contract;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.contract.CommissionSetting;

public interface CommissionSettingRepository extends MongoRepository<CommissionSetting, String> {

	List<CommissionSetting> findByProfileId(String profileId);

	List<CommissionSetting> findByContractIdAndProfileId(String contractId, String profileId);

	CommissionSetting findByIdAndContractIdAndProfileId(String id, String contractId, String profileId);

}
