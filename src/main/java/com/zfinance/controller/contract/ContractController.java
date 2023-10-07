package com.zfinance.controller.contract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.request.PaginationRequestOptions;
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
import com.zfinance.dto.response.PaginationResponse;
import com.zfinance.dto.response.contract.CommissionGateProfileResponse;
import com.zfinance.dto.response.contract.CommissionProfileResponse;
import com.zfinance.dto.response.contract.CommissionProfilesResponse;
import com.zfinance.dto.response.contract.CommissionRecord;
import com.zfinance.dto.response.contract.CommissionRuleListResponse;
import com.zfinance.dto.response.contract.CommissionSettingRecord;
import com.zfinance.dto.response.contract.CommissionSettingsResponse;
import com.zfinance.dto.response.contract.ContractRecord;
import com.zfinance.dto.response.contract.LimitsResponse;
import com.zfinance.dto.response.contract.ProvidersResponse;
import com.zfinance.mapper.CommissionMapper;
import com.zfinance.mapper.CommissionRuleMapper;
import com.zfinance.mapper.CommissionSettingMapper;
import com.zfinance.mapper.ContractMapper;
import com.zfinance.mapper.LimitMapper;
import com.zfinance.mapper.ProviderMapper;
import com.zfinance.orm.contract.Contract;
import com.zfinance.orm.userdefinedtypes.contract.CommissionRuleCondition;
import com.zfinance.services.contract.ContractService;

@RestController
@RequestMapping("/contracts")
@CrossOrigin("*")
public class ContractController {

	@Autowired
	private ContractService contractService;

	@PostMapping("/view")
	public PaginationResponse<ContractRecord> getRecords(
			@RequestBody PaginationRequestOptions<ContractsFilter, ContractsSort> data) {

		List<Contract> contracts = contractService.searchContracts(data.getFilter(), data.getSort());

		PaginationResponse<ContractRecord> paginationResponse = new PaginationResponse<>();
		paginationResponse.setRecords(ContractMapper.INSTANCE.mapContracts(contracts));
		paginationResponse.setTotalRecords(contracts.size());

		// TODO: to be edit
		paginationResponse.setPageSize(data.getPageSize() != null ? Integer.valueOf(data.getPageSize()) : null);
		paginationResponse.setPageNumber(data.getPageNumber() != null ? Integer.valueOf(data.getPageNumber()) : null);

		return paginationResponse;
	}

	@PostMapping("/{contractId}/copy")
	public ContractRecord createCustomContract(@PathVariable String contractId, @RequestBody CustomContractBody data) {
		return ContractMapper.INSTANCE.mapContract(contractService.createCustomContract(contractId, data));
	}

	@GetMapping("/{contractId}/gate-commission-profiles")
	public ProvidersResponse getGateCommissionProfiles(@PathVariable String contractId) {

		return new ProvidersResponse(ProviderMapper.INSTANCE.mapProviders(contractService.getGateCommissionProfiles(
				contractId)));
	}

	@GetMapping("/{contractId}/gate-commission-profiles/{profileId}")
	public CommissionGateProfileResponse getGateCommissionProfile(@PathVariable String contractId,
			@PathVariable String profileId) {

		return new CommissionGateProfileResponse(ProviderMapper.INSTANCE.mapProvider(contractService
				.getGateCommissionProfile(contractId, profileId)));
	}

	@GetMapping("/{contractId}/gate-commission-profiles/{profileId}/commission-settings-records")
	public CommissionSettingsResponse getGateCommissionSettings(@PathVariable String contractId,
			@PathVariable String profileId) {

		return new CommissionSettingsResponse(CommissionSettingMapper.INSTANCE.mapCommissionSettings(contractService
				.getGateCommissionSettings(contractId, profileId)));
	}

	@GetMapping("/{contractId}/gate-commission-profiles/{profileId}/limit-profiles")
	public LimitsResponse getGateCommissionLimits(@PathVariable String contractId, @PathVariable String profileId) {

		return new LimitsResponse(LimitMapper.INSTANCE.mapLimits(contractService.getGateCommissionLimits(contractId,
				profileId)));

	}

	@PostMapping("/{contractId}/gate-commission-profiles/{profileId}/limit-profiles")
	public LimitsResponse createGateCommissionLimits(@PathVariable String contractId, @PathVariable String profileId,
			@RequestBody LimitBody limitBody) {

		return new LimitsResponse(LimitMapper.INSTANCE.mapLimits(contractService.createGateCommissionLimits(contractId,
				profileId, limitBody)));
	}

	@PatchMapping("/{contractId}/gate-commission-profiles/{profileId}/limit-profiles/{limitId}")
	public LimitsResponse updateGateCommissionLimits(@PathVariable String contractId, @PathVariable String profileId,
			@PathVariable String limitId, @RequestBody LimitBodyUpdate limitBodyUpdate) {

		return new LimitsResponse(LimitMapper.INSTANCE.mapLimits(contractService.updateGateCommissionLimits(contractId,
				profileId, limitId, limitBodyUpdate)));
	}

	@PostMapping("/{contractId}/gate-commission-profiles")
	public ProvidersResponse createGateCommissionProfiles(@PathVariable String contractId,
			@RequestBody ProviderCreateBody providerCreateBody) {

		return new ProvidersResponse(ProviderMapper.INSTANCE.mapProviders(contractService.createGateCommissionProfiles(
				contractId, providerCreateBody)));
	}

	// CommissionSetting or Provider
	@PostMapping("/{contractId}/gate-commission-profiles/{profileId}/commission-settings-records/set-up-commission-settings")
	public List<CommissionSettingRecord> createGateCommission(@PathVariable String contractId,
			@PathVariable String profileId, @RequestBody CommissionBody commissionBody) {

		return CommissionSettingMapper.INSTANCE.mapCommissionSettings(contractService.createGateCommission(contractId,
				profileId, commissionBody));
	}

	@GetMapping("/{contractId}/commission-profiles")
	public CommissionProfilesResponse getCommissionProfiles(@PathVariable String contractId) {
		return new CommissionProfilesResponse(CommissionMapper.INSTANCE.mapCommissions(contractService
				.getCommissionProfiles(contractId)));

	}

	@GetMapping("/{contractId}/commission-profiles/{profileId}")
	public CommissionProfileResponse getCommission(@PathVariable String contractId, @PathVariable String profileId) {
		return new CommissionProfileResponse(CommissionMapper.INSTANCE.mapCommission(contractService.getCommission(
				contractId, profileId)));
	}

	@PostMapping("/{contractId}/commission-profiles/{profileId}/commission-rule/view")
	public CommissionRuleListResponse getCommissionRules(@PathVariable String contractId,
			@PathVariable String profileId, @RequestBody CommissionRuleReq commissionRuleReq) {
		return new CommissionRuleListResponse(CommissionRuleMapper.INSTANCE.mapCommissionRules(contractService
				.getCommissionRules(contractId, profileId, commissionRuleReq)));
	}

	@PostMapping("/{contractId}/commission-profiles/{profileId}/commission-rule")
	public CommissionRuleListResponse createCommissionRule(@PathVariable String contractId,
			@PathVariable String profileId, @RequestBody CommissionRulePayload commissionRulePayload) {
		return new CommissionRuleListResponse(CommissionRuleMapper.INSTANCE.mapCommissionRules(contractService
				.createCommissionRule(contractId, profileId, commissionRulePayload)));

	}

	@PatchMapping("/{contractId}/commission-profiles/{profileId}/commission-rule/{ruleId}")
	public CommissionRuleListResponse updateCommissionRule(@PathVariable String contractId,
			@PathVariable String profileId, @RequestBody String ruleId, CommissionRulePayload commissionRulePayload) {
		return new CommissionRuleListResponse(CommissionRuleMapper.INSTANCE.mapCommissionRules(contractService
				.updateCommissionRule(contractId, profileId, ruleId, commissionRulePayload)));

	}

	@DeleteMapping("/{contractId}/commission-profiles/{profileId}/commission-rule/{ruleId}")
	public CommissionRuleListResponse deleteCommissionRule(@PathVariable String contractId,
			@PathVariable String profileId, @PathVariable String ruleId) {
		return new CommissionRuleListResponse(CommissionRuleMapper.INSTANCE.mapCommissionRules(contractService
				.deleteCommissionRule(contractId, profileId, ruleId)));

	}

	// does we all create condition in database
	@PostMapping("/{contractId}/commission-profiles/{profileId}/commission-rule/{ruleId}/condition")
	public List<CommissionRuleCondition> createCondition(@PathVariable String contractId,
			@PathVariable String profileId, @PathVariable String ruleId,
			@RequestBody CommissionRuleConditionPayload commissionRuleConditionPayload) {
		return contractService.createCondition(contractId, profileId, ruleId, commissionRuleConditionPayload);
	}

	@PutMapping("/{contractId}/commission-profiles/{profileId}/commission-rule/{ruleId}/condition/{conditionId}")
	public List<CommissionRuleCondition> updateCondition(@PathVariable String contractId,
			@PathVariable String profileId, @PathVariable String ruleId, @PathVariable String conditionId,
			@RequestBody CommissionRuleConditionPayload commissionRuleConditionPayload) {
		return contractService.updateCondition(contractId, profileId, ruleId, conditionId,
				commissionRuleConditionPayload);
	}

	@DeleteMapping("/{contractId}/commission-profiles/{profileId}/commission-rule/{ruleId}/condition/{conditionId}")
	public List<CommissionRuleCondition> deleteCondition(@PathVariable String contractId,
			@PathVariable String profileId, @PathVariable String ruleId, @PathVariable String conditionId) {
		return contractService.deleteCondition(contractId, profileId, ruleId, conditionId);

	}

	@PostMapping("/{contractId}/commission-profiles")
	public CommissionProfilesResponse createCommission(@PathVariable String contractId,
			@RequestBody CreateSystemCommission createSystemCommission) {
		return new CommissionProfilesResponse(CommissionMapper.INSTANCE.mapCommissions(contractService.createCommission(
				contractId, createSystemCommission)));
	}

	@PostMapping("/{contractId}/commission-profiles/multi-currency")
	public CommissionProfilesResponse createCommissionMultiCurrency(@PathVariable String contractId,
			@RequestBody CreateMultiCurrencySystemCommission createMultiCurrencySystemCommission) {
		return new CommissionProfilesResponse(CommissionMapper.INSTANCE.mapCommissions(contractService
				.createCommissionMultiCurrency(contractId, createMultiCurrencySystemCommission)));
	}

	@PatchMapping("/{contractId}/commission-profiles/{profileId}")
	public CommissionProfilesResponse updateCommission(@PathVariable String contractId, @PathVariable String profileId,
			@RequestBody SystemCommissionBodyUpdate systemCommissionBodyUpdate) {
		return new CommissionProfilesResponse(CommissionMapper.INSTANCE.mapCommissions(contractService.updateCommission(
				contractId, profileId, systemCommissionBodyUpdate)));
	}

	// Repeated
	@GetMapping("/{contractId}/commission-profiles/{profileId}/limit-profiles")
	public LimitsResponse getCommissionLimits(@PathVariable String contractId, @PathVariable String profileId) {

		return new LimitsResponse(LimitMapper.INSTANCE.mapLimits(contractService.getCommissionLimits(contractId,
				profileId)));
	}

	// Repeated but different body
	@PostMapping("/{contractId}/commission-profiles/{profileId}/limit-profiles")
	public LimitsResponse createCommissionLimits(@PathVariable String contractId, @PathVariable String profileId,
			@RequestBody SystemLimitBody systemLimitBody) {
		return new LimitsResponse(LimitMapper.INSTANCE.mapLimits(contractService.createCommissionLimits(contractId,
				profileId, systemLimitBody)));
	}

	// Repeated
	@PatchMapping("/{contractId}/commission-profiles/{profileId}/limit-profiles/{limitId}")
	public LimitsResponse updateCommissionLimits(@PathVariable String contractId, @PathVariable String profileId,
			@PathVariable String limitId, @RequestBody LimitBodyUpdate limitBodyUpdate) {
		return new LimitsResponse(LimitMapper.INSTANCE.mapLimits(contractService.updateCommissionLimits(contractId,
				profileId, limitId, limitBodyUpdate)));

	}

	@PatchMapping
	public void changeUserContract(@RequestBody ContractBody contractBody) {
		contractService.changeUserContract(contractBody);
	}

	@PostMapping("/saveContract")
	public ContractRecord saveContract(@RequestBody ContractRecord contractRecord) {
		return ContractMapper.INSTANCE.mapContract(contractService.saveContract(ContractMapper.INSTANCE
				.mapContractRecord(contractRecord)));
	}

	@PostMapping("/{contractId}/saveCommission")
	public CommissionRecord saveCommission(@PathVariable String contractId,
			@RequestBody CommissionRecord commissionRecord) {
		return CommissionMapper.INSTANCE.mapCommission(contractService.saveCommission(contractId,
				CommissionMapper.INSTANCE.mapCommissionRecord(commissionRecord)));
	}

}
