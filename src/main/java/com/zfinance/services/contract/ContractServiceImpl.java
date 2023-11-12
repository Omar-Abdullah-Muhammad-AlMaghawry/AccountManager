package com.zfinance.services.contract;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

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
import com.zfinance.dto.request.extenrnal.UsersFilter;
import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.enums.CollectorTypeEnum;
import com.zfinance.exceptions.BusinessException;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.mapper.CurrencyMapper;
import com.zfinance.orm.contract.Commission;
import com.zfinance.orm.contract.CommissionRule;
import com.zfinance.orm.contract.CommissionSetting;
import com.zfinance.orm.contract.Contract;
import com.zfinance.orm.contract.Limit;
import com.zfinance.orm.contract.Provider;
import com.zfinance.orm.userdefinedtypes.contract.CommissionIssuer;
import com.zfinance.orm.userdefinedtypes.contract.CommissionRuleCondition;
import com.zfinance.orm.userdefinedtypes.contract.OperationFlow;
import com.zfinance.orm.userdefinedtypes.contract.ProviderCurrency;
import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;
import com.zfinance.orm.userdefinedtypes.user.UserContractInfo;
import com.zfinance.orm.userdefinedtypes.user.UserMemberRecord;
import com.zfinance.repositories.contract.CommissionRepository;
import com.zfinance.repositories.contract.CommissionRuleRepository;
import com.zfinance.repositories.contract.CommissionSettingRepository;
import com.zfinance.repositories.contract.ContractRepository;
import com.zfinance.repositories.contract.LimitsRepository;
import com.zfinance.repositories.contract.ProviderRepository;
import com.zfinance.services.database.sequence.SequenceGeneratorService;
import com.zfinance.services.external.CurrencyService;
import com.zfinance.services.external.IssuerService;
import com.zfinance.services.external.OperationFlowService;
import com.zfinance.services.external.UserService;

@Service
public class ContractServiceImpl implements ContractService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private CommissionRepository commissionRepository;

	@Autowired
	private CommissionRuleRepository commissionRuleRepository;

	@Autowired
	private CommissionSettingRepository commissionSettingRepository;

	@Autowired
	private LimitsRepository limitsRepository;

	@Autowired
	private ProviderRepository providerRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private CurrencyService currencyService;

	@Autowired
	private IssuerService issuerService;

	@Autowired
	private OperationFlowService operationFlowService;

	@Autowired
	private UserService userService;

	@Override
	public List<Contract> searchContracts(ContractsFilter contractsFilter, ContractsSort contractsSort) {
		Criteria criteria = new Criteria();

		// Add contractsFilter criteria based on the contractsFilter object
		if (contractsFilter != null) {
			if (contractsFilter.getId() != null) {
				criteria.and("id").is(contractsFilter.getId());
			}
			if (contractsFilter.getTypes() != null && !contractsFilter.getTypes().isEmpty()) {
				criteria.and("organizationType").in(contractsFilter.getTypes());
			}
			if (contractsFilter.getGlobal() != null) {
				criteria.and("global").is(contractsFilter.getGlobal());
			}
			if (contractsFilter.getPersonTypes() != null && !contractsFilter.getPersonTypes().isEmpty()) {
				criteria.and("personType").in(contractsFilter.getPersonTypes());
			}
			// TODO: need to be handle
			if (contractsFilter.getDateFrom() != null) {
				// Add date range criteria if needed
				// criteria.and("dateField").gte(contractsFilter.getDateFrom());
			}
			// TODO: need to be handle
			if (contractsFilter.getDateTo() != null) {
				// Add date range criteria if needed
				// criteria.and("dateField").lte(contractsFilter.getDateTo());
			}
			if (contractsFilter.getDescription() != null) {
				criteria.and("description").is(contractsFilter.getDescription());
			}
			// Add other contractsFilter criteria as needed...
		}

		Query query = new Query(criteria);

		// Apply sorting
		if (contractsSort != null) {
			if (contractsSort.getId() != null) {
				if (contractsSort.getId().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("id")));
				} else if (contractsSort.getId().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("id")));
				}
			}
			if (contractsSort.getOrganizationType() != null) {
				if (contractsSort.getOrganizationType().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("organizationType")));
				} else if (contractsSort.getOrganizationType().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("organizationType")));
				}
			}
			if (contractsSort.getPersonType() != null) {
				if (contractsSort.getPersonType().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("personType")));
				} else if (contractsSort.getPersonType().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("personType")));
				}
			}
			if (contractsSort.getName() != null) {
				if (contractsSort.getName().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("name")));
				} else if (contractsSort.getName().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("name")));
				}
			}
			if (contractsSort.getDescription() != null) {
				if (contractsSort.getDescription().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("description")));
				} else if (contractsSort.getDescription().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("description")));
				}
			}
			if (contractsSort.getActive() != null) {

				// TODO: need to be handle
				if (contractsSort.getActive().equals(true)) {
					query.with(Sort.by(Sort.Order.asc("active")));
				} else if (contractsSort.getActive().equals(false)) {
					query.with(Sort.by(Sort.Order.desc("active")));
				}
			}
			if (contractsSort.getGlobal() != null) {

				// TODO: need to be handle
				if (contractsSort.getGlobal().equals(true)) {
					query.with(Sort.by(Sort.Order.asc("global")));
				} else if (contractsSort.getGlobal().equals(false)) {
					query.with(Sort.by(Sort.Order.desc("global")));
				}
			}
			// Add other sorting criteria as needed...
		}

		return mongoTemplate.find(query, Contract.class);
	}

	// TODO: need to know what copyLimit inside customContractBody
	@Override
	public Contract createCustomContract(String contractId, CustomContractBody customContractBody) {
		Optional<Contract> optionanlContract = contractRepository.findById(contractId);

		if (optionanlContract.isPresent()) {
			Contract contract = optionanlContract.get();

			contract.setName(customContractBody.getName());

			// TODO : NEED TO BE CHECKED
			contract.setId(sequenceGeneratorService.generateSequence(Contract.SEQUENCE_NAME));

			return contractRepository.save(contract);
		} else {
			throw new DataNotFoundException(Contract.class, contractId);
		}
	}

	@Override
	public Contract saveContract(Contract contract) {
		return contractRepository.save(contract);
	}

	@Override
	public List<Provider> getGateCommissionProfiles(String contractId) {
		return providerRepository.findByContractId(contractId);
	}

	@Override
	public Provider getGateCommissionProfile(String contractId, String profileId) {
		return providerRepository.findByIdAndContractId(profileId, contractId);
	}

	@Override
	public List<CommissionSetting> getGateCommissionSettings(String contractId, String profileId) {
		return commissionSettingRepository.findByContractIdAndProfileId(contractId, profileId);
	}

	@Override
	public List<Limit> getGateCommissionLimits(String contractId, String profileId) {
		return limitsRepository.findByContractIdAndProfileId(contractId, profileId);
	}

	@Override
	public List<Limit> createGateCommissionLimits(String contractId, String profileId, LimitBody limitBody) {
		Limit limit = new Limit();
		limit.setProfileId(profileId);
		limit.setContractId(contractId);

		// TODO: need to be asked? limitBody.getProductId or profileId
		limit.setGateProfileId(limitBody.getProductId());
		limit.setActive(limitBody.getActive());
		limit.setQualifier(limitBody.getQualifier());
		limit.setTimeUnit(limitBody.getTimeUnit());
		limit.setTxType(limitBody.getTxType());
		limit.setValue(limitBody.getValue());

		limit.setId(sequenceGeneratorService.generateSequence(Limit.SEQUENCE_NAME));

		limitsRepository.save(limit);

		return limitsRepository.findByContractIdAndProfileId(contractId, profileId);
	}

	@Override
	public List<Limit> updateGateCommissionLimits(String contractId, String profileId, String limitId,
			LimitBodyUpdate limitBodyUpdate) {
		Limit limit = limitsRepository.findByIdAndContractIdAndProfileId(limitId, contractId, profileId);
		limit.setValue(limitBodyUpdate.getValue());
		limit.setActive(limitBodyUpdate.getActive());
		limitsRepository.save(limit);
		return limitsRepository.findByContractIdAndProfileId(contractId, profileId);
	}

	@Override
	public List<Provider> createGateCommissionProfiles(String contractId, ProviderCreateBody providerCreateBody) {
		Provider provider = new Provider();
		provider.setContractId(contractId);

		ProviderCurrency providerCurrency = CurrencyMapper.INSTANCE.mapCurrency(currencyService.getCurrencyByCode(
				providerCreateBody.getCurrencyCode()));
		provider.setProviderCurrency(providerCurrency);

		// TODO: what's gate provider
		provider.setGateProviderId(providerCreateBody.getGateProviderId());

		provider.setCreatedAt((new Date()).toString());
		provider.setId(sequenceGeneratorService.generateSequence(Provider.SEQUENCE_NAME));

		providerRepository.save(provider);
		return providerRepository.findByContractId(contractId);
	}

	@Override
	public List<CommissionSetting> createGateCommission(String contractId, String profileId,
			CommissionBody commissionBody) {
		CommissionSetting commissionSetting = new CommissionSetting();

		commissionSetting.setContractId(contractId);
		commissionSetting.setProfileId(profileId);

		commissionSetting.setActive(commissionBody.getActive());
		commissionSetting.setTxType(commissionBody.getTxType());

		// TODO: need to be know
//		commissionSetting.setCollector();
//		commissionSetting.setCommissionDirection();
//		commissionSetting.setDirection();;

//		commissionBody.getProviderCommission();
		if (commissionBody.getTotalCommission() != null && commissionBody.getTotalCommission().getType() != null) {
			commissionSetting.setValue(commissionBody.getTotalCommission());
			commissionSetting.setCollector(CollectorTypeEnum.TOTAL.getCode());
		}
		if (commissionBody.getProviderCommission() != null && commissionBody.getProviderCommission()
				.getType() != null) {
			commissionSetting.setValue(commissionBody.getProviderCommission());
			commissionSetting.setCollector(CollectorTypeEnum.PROVIDER.getCode());
		}

		commissionSetting.setId(sequenceGeneratorService.generateSequence(CommissionSetting.SEQUENCE_NAME));

		commissionSettingRepository.save(commissionSetting);

		return commissionSettingRepository.findByContractIdAndProfileId(contractId, profileId);
	}

	@Override
	public List<Commission> getCommissionProfiles(String contractId) {
		return commissionRepository.findByContractId(contractId);
	}

	@Override
	public Commission getCommission(String contractId, String profileId) {
		return commissionRepository.findByIdAndContractId(profileId, contractId);
	}

	@Override
	public List<CommissionRule> getCommissionRules(String contractId, String profileId,
			CommissionRuleReq commissionRuleReq) {
		return commissionRuleRepository.findByContractIdAndProfileIdAndBeginDateGreaterThanEqual(contractId, profileId,
				commissionRuleReq.getDate());
	}

	@Override
	public List<CommissionRule> createCommissionRule(String contractId, String profileId,
			CommissionRulePayload commissionRulePayload) {

		CommissionRule commissionRule = new CommissionRule();

		commissionRule.setContractId(contractId);
		commissionRule.setProfileId(profileId);

		commissionRule.setActive(commissionRulePayload.getActive());
		commissionRule.setBeginDate(commissionRulePayload.getBeginDate());
		commissionRule.setEndDate(commissionRulePayload.getEndDate());
		commissionRule.setFeeDirection(commissionRulePayload.getDirection());
		commissionRule.setConditions(new ArrayList<CommissionRuleCondition>());

		commissionRule.setId(sequenceGeneratorService.generateSequence(CommissionRule.SEQUENCE_NAME));

		commissionRuleRepository.save(commissionRule);

		return commissionRuleRepository.findByContractIdAndProfileId(contractId, profileId);
	}

	@Override
	public List<CommissionRule> updateCommissionRule(String contractId, String profileId, String ruleId,
			CommissionRulePayload commissionRulePayload) {
		CommissionRule commissionRule = commissionRuleRepository.findByIdAndContractIdAndProfileId(ruleId, contractId,
				profileId);

		commissionRule.setContractId(contractId);
		commissionRule.setProfileId(profileId);

		commissionRule.setActive(commissionRulePayload.getActive());
		commissionRule.setBeginDate(commissionRulePayload.getBeginDate());
		commissionRule.setEndDate(commissionRulePayload.getEndDate());
		commissionRule.setFeeDirection(commissionRulePayload.getDirection());

		commissionRuleRepository.save(commissionRule);
		return commissionRuleRepository.findByContractIdAndProfileId(contractId, profileId);

	}

	@Override
	public List<CommissionRule> deleteCommissionRule(String contractId, String profileId, String ruleId) {
		commissionRuleRepository.deleteById(ruleId);
		return commissionRuleRepository.findByContractIdAndProfileId(contractId, profileId);
	}

	@Override
	public List<CommissionRuleCondition> createCondition(String contractId, String profileId, String ruleId,
			CommissionRuleConditionPayload commissionRuleConditionPayload) {

		CommissionRule commissionRule = commissionRuleRepository.findByIdAndContractIdAndProfileId(ruleId, contractId,
				profileId);

		CommissionRuleCondition commissionRuleCondition = new CommissionRuleCondition();
		commissionRuleCondition.setId(UUID.randomUUID().toString());
		commissionRuleCondition.setBeginAmount(commissionRuleConditionPayload.getBeginAmount());
		commissionRuleCondition.setEndAmount(commissionRuleConditionPayload.getEndAmount());
		commissionRuleCondition.setValue(commissionRuleConditionPayload.getCommission());

		List<CommissionRuleCondition> commissionRuleConditions = new ArrayList<>();
		if (commissionRule.getConditions() != null && !commissionRule.getConditions().isEmpty()) {
			commissionRuleConditions = commissionRule.getConditions();
		}
		commissionRuleConditions.add(commissionRuleCondition);
		commissionRule.setConditions(commissionRuleConditions);

		commissionRuleRepository.save(commissionRule);
		return commissionRuleConditions;

	}

	@Override
	public List<CommissionRuleCondition> updateCondition(String contractId, String profileId, String ruleId,
			String conditionId, CommissionRuleConditionPayload commissionRuleConditionPayload) {
		CommissionRule commissionRule = commissionRuleRepository.findByIdAndContractIdAndProfileId(ruleId, contractId,
				profileId);

		if (commissionRule.getConditions() != null && !commissionRule.getConditions().isEmpty()) {
			List<CommissionRuleCondition> commissionRuleConditions = commissionRule.getConditions();
			commissionRuleConditions.forEach(commissionRuleCondition -> {
				if (commissionRuleCondition.getId().equals(conditionId)) {
					commissionRuleCondition.setId(UUID.randomUUID().toString());
					commissionRuleCondition.setBeginAmount(commissionRuleConditionPayload.getBeginAmount());
					commissionRuleCondition.setEndAmount(commissionRuleConditionPayload.getEndAmount());
					commissionRuleCondition.setValue(commissionRuleConditionPayload.getCommission());

				}
			});
			commissionRule.setConditions(commissionRuleConditions);
			commissionRuleRepository.save(commissionRule);
			return commissionRuleConditions;
		} else {
			throw new DataNotFoundException(CommissionRuleCondition.class, conditionId);
		}

	}

	@Override
	public List<CommissionRuleCondition> deleteCondition(String contractId, String profileId, String ruleId,
			String conditionId) {
		CommissionRule commissionRule = commissionRuleRepository.findByIdAndContractIdAndProfileId(ruleId, contractId,
				profileId);

		if (commissionRule.getConditions() != null && !commissionRule.getConditions().isEmpty()) {
			List<CommissionRuleCondition> commissionRuleConditions = commissionRule.getConditions();

			commissionRuleConditions.removeIf(commissionRuleCondition -> commissionRuleCondition.getId().equals(
					conditionId));

			return commissionRuleConditions;
		} else {
			throw new DataNotFoundException(CommissionRuleCondition.class, conditionId);
		}
	}

	@Override
	public List<Commission> createCommission(String contractId, CreateSystemCommission createSystemCommission) {

		Commission commission = new Commission();

		commission.setContractId(contractId);
		commission.setCreatedAt((new Date()).toString());

		commission.setSrcParticipantSpecification(createSystemCommission.getSrcParticipantSpecification());
		commission.setDestParticipantSpecification(createSystemCommission.getDestParticipantSpecification());

		Issuer issuer = issuerService.getIssuerById(createSystemCommission.getIssuerId());
		if (issuer != null) {
			CommissionIssuer commissionIssuer = new CommissionIssuer();
			commissionIssuer.setId(issuer.getId());
			commissionIssuer.setCurrency(issuer.getCurrency());
			commissionIssuer.setSn(issuer.getSn());
			commission.setIssuer(commissionIssuer);
		}

		// TODO: after making Catalog Request, make the same for flow after making a
		// table for it (OperationFlowRecord)

		OperationFlow flow = operationFlowService.getOperationFlowById(createSystemCommission.getOperationFlowId());
		commission.setFlow(flow);

		commission.setId(sequenceGeneratorService.generateSequence(Commission.SEQUENCE_NAME));

		commissionRepository.save(commission);

		return commissionRepository.findByContractId(contractId);
	}

	@Override
	public List<Commission> createCommissionMultiCurrency(String contractId,
			CreateMultiCurrencySystemCommission createMultiCurrencySystemCommission) {
		Commission commission = new Commission();

		commission.setContractId(contractId);
		commission.setCreatedAt((new Date()).toString());

		commission.setSrcParticipantSpecification(createMultiCurrencySystemCommission.getSrcParticipantSpecification());
		commission.setDestParticipantSpecification(createMultiCurrencySystemCommission
				.getDestParticipantSpecification());

		// TODO: which one we will use destinationIssuerId or sourceIssuerId
		Issuer issuer = issuerService.getIssuerById(createMultiCurrencySystemCommission.getSourceIssuerId());
		if (issuer != null) {
			CommissionIssuer commissionIssuer = new CommissionIssuer();
			commissionIssuer.setId(issuer.getId());
			commissionIssuer.setCurrency(issuer.getCurrency());
			commissionIssuer.setSn(issuer.getSn());
			commission.setIssuer(commissionIssuer);
		}

		// TODO: after making Catalog Request, make the same for flow after making a
		// table for it (OperationFlowRecord)

		OperationFlow flow = operationFlowService.getOperationFlowById(createMultiCurrencySystemCommission
				.getOperationFlowId());
		commission.setFlow(flow);

		commission.setId(sequenceGeneratorService.generateSequence(Commission.SEQUENCE_NAME));

		commissionRepository.save(commission);

		return commissionRepository.findByContractId(contractId);
	}

	@Override
	public List<Commission> updateCommission(String contractId, String profileId,
			SystemCommissionBodyUpdate systemCommissionBodyUpdate) {
		Commission commission = commissionRepository.findByIdAndContractId(profileId, contractId);

		commission.setActive(systemCommissionBodyUpdate.getActive());
		commission.setDirection(systemCommissionBodyUpdate.getDirection());
		commission.setValue(systemCommissionBodyUpdate.getValue());

		commission.setUpdatedAt((new Date()).toString());

		commissionRepository.save(commission);

		return commissionRepository.findByContractId(contractId);
	}

	// Repeated
	@Override
	public List<Limit> getCommissionLimits(String contractId, String profileId) {
		return limitsRepository.findByContractIdAndProfileId(contractId, profileId);

	}

	@Override
	public List<Limit> createCommissionLimits(String contractId, String profileId, SystemLimitBody systemLimitBody) {
		Limit limit = new Limit();
		limit.setProfileId(profileId);
		limit.setContractId(contractId);

		// TODO: need to be asked? limitBody.getProductId or profileId
		limit.setActive(systemLimitBody.getActive());
		limit.setQualifier(systemLimitBody.getQualifier());
		limit.setTimeUnit(systemLimitBody.getTimeUnit());
		limit.setValue(systemLimitBody.getValue());

		limit.setId(sequenceGeneratorService.generateSequence(Limit.SEQUENCE_NAME));

		limitsRepository.save(limit);

		return limitsRepository.findByContractIdAndProfileId(contractId, profileId);

	}

	// Repeated
	@Override
	public List<Limit> updateCommissionLimits(String contractId, String profileId, String limitId,
			LimitBodyUpdate limitBodyUpdate) {
		Limit limit = limitsRepository.findByIdAndContractIdAndProfileId(limitId, contractId, profileId);
		limit.setValue(limitBodyUpdate.getValue());
		limit.setActive(limitBodyUpdate.getActive());
		limitsRepository.save(limit);
		return limitsRepository.findByContractIdAndProfileId(contractId, profileId);
	}

	@Override
	public void changeUserContract(ContractBody contractBody) {
		Optional<Contract> optionalContract = contractRepository.findById(contractBody.getContractId());

		UsersFilter usersFilter = new UsersFilter();
		usersFilter.setIds(new ArrayList<>(Collections.singletonList(contractBody.getUserId())));
		usersFilter.setOrganizationIds(new ArrayList<>(Collections.singletonList(contractBody.getOrganizationId())));
		List<UserRecord> users = userService.searchUsers(usersFilter);

		if (optionalContract.isPresent() && users != null && !users.isEmpty()) {
			Contract contract = optionalContract.get();

			UserRecord userRecord = users.get(0);
			List<UserMemberRecord> members = userRecord.getMembers();
			members.forEach(member -> {
				UserContractInfo contractInfo = new UserContractInfo();
				contractInfo.setId(contract.getId());
				contractInfo.setName(contract.getName());
				contractInfo.setPersonType(contract.getPersonType());
				member.setContractInfo(contractInfo);
			});

			userRecord.setMembers(members);
			userService.saveUser(userRecord);
		} else {
			throw new BusinessException("error_changeUserContract");
		}
	}

	@Override
	public Commission saveCommission(String contractId, Commission commission) {
		commission.setContractId(contractId);
		return commissionRepository.save(commission);
	}

}
