package com.zfinance.services.contract;

import java.util.List;

import com.zfinance.dto.request.contract.CommissionBody;
import com.zfinance.dto.request.contract.CommissionRuleConditionPayload;
import com.zfinance.dto.request.contract.CommissionRulePayload;
import com.zfinance.dto.request.contract.CommissionRuleReq;
import com.zfinance.dto.request.contract.ContractBody;
import com.zfinance.dto.request.contract.ContractsFilter;
import com.zfinance.dto.request.contract.ContractsSort;
import com.zfinance.dto.request.contract.CreateMultiCurrencySystemCommission;
import com.zfinance.dto.request.contract.CreateSystemCommission;
import com.zfinance.dto.request.contract.CustomContractBody;
import com.zfinance.dto.request.contract.LimitBody;
import com.zfinance.dto.request.contract.LimitBodyUpdate;
import com.zfinance.dto.request.contract.ProviderCreateBody;
import com.zfinance.dto.request.contract.SystemCommissionBodyUpdate;
import com.zfinance.dto.request.contract.SystemLimitBody;
import com.zfinance.orm.contract.Commission;
import com.zfinance.orm.contract.CommissionRule;
import com.zfinance.orm.contract.CommissionSetting;
import com.zfinance.orm.contract.Contract;
import com.zfinance.orm.contract.Limit;
import com.zfinance.orm.contract.Provider;
import com.zfinance.orm.userdefinedtypes.contract.CommissionRuleCondition;

public interface ContractService {

	public List<Contract> searchContracts(ContractsFilter contractsFilter, ContractsSort contractsSort);

	public Contract createCustomContract(String contractId, CustomContractBody customContractBody);

	public List<Provider> getGateCommissionProfiles(String contractId);

	public Provider getGateCommissionProfile(String contractId, String profileId);

	public List<CommissionSetting> getGateCommissionSettings(String contractId, String profileId);

	public List<Limit> getGateCommissionLimits(String contractId, String profileId);

	public List<Limit> createGateCommissionLimits(String contractId, String profileId, LimitBody limitBody);

	public List<Limit> updateGateCommissionLimits(String contractId, String profileId, String limitId,
			LimitBodyUpdate limitBodyUpdate);

	public List<Provider> createGateCommissionProfiles(String contractId, ProviderCreateBody providerCreateBody);

	// CommissionSetting or Provider
	public List<CommissionSetting> createGateCommission(String contractId, String profileId,
			CommissionBody commissionBody);

	public List<Commission> getCommissionProfiles(String contractId);

	public Commission getCommission(String contractId, String profileId);

	public List<CommissionRule> getCommissionRules(String contractId, String profileId,
			CommissionRuleReq commissionRuleReq);

	public List<CommissionRule> createCommissionRule(String contractId, String profileId,
			CommissionRulePayload commissionRulePayload);

	public List<CommissionRule> updateCommissionRule(String contractId, String profileId, String ruleId,
			CommissionRulePayload commissionRulePayload);

	public List<CommissionRule> deleteCommissionRule(String contractId, String profileId, String ruleId);

	// does we all create condition in database
	public List<CommissionRuleCondition> createCondition(String contractId, String profileId, String ruleId,
			CommissionRuleConditionPayload commissionRuleConditionPayload);

	public List<CommissionRuleCondition> updateCondition(String contractId, String profileId, String ruleId,
			String conditionId, CommissionRuleConditionPayload commissionRuleConditionPayload);

	public List<CommissionRuleCondition> deleteCondition(String contractId, String profileId, String ruleId,
			String conditionId);

	public List<Commission> createCommission(String contractId, CreateSystemCommission createSystemCommission);

	public List<Commission> createCommissionMultiCurrency(String contractId,
			CreateMultiCurrencySystemCommission createMultiCurrencySystemCommission);

	public List<Commission> updateCommission(String contractId, String profileId,
			SystemCommissionBodyUpdate systemCommissionBodyUpdate);

	// Repeated
	public List<Limit> getCommissionLimits(String contractId, String profileId);

	// Repeated but different body
	public List<Limit> createCommissionLimits(String contractId, String profileId, SystemLimitBody systemLimitBody);

	// Repeated
	public List<Limit> updateCommissionLimits(String contractId, String profileId, String limitId,
			LimitBodyUpdate limitBodyUpdate);

	public void changeUserContract(ContractBody contractBody);

	public Contract saveContract(Contract contract);

	public Commission saveCommission(String contractId, Commission commission);

}
