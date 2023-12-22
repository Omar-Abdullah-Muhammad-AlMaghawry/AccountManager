package com.zfinance.services.account;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.config.filters.TokenAuthorizationFilter;
import com.zfinance.dto.request.account.BankAccountBody;
import com.zfinance.dto.request.account.BankAccountsFilter;
import com.zfinance.dto.request.account.BankAccountsSort;
import com.zfinance.dto.request.account.MyBankAccountsFilter;
import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.enums.StatusEnum;
import com.zfinance.exceptions.BusinessException;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.account.BankAccount;
import com.zfinance.orm.userdefinedtypes.account.BankAccountDetails;
import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;
import com.zfinance.repositories.bankaccount.BankAccountRepository;
import com.zfinance.services.database.sequence.SequenceGeneratorService;
import com.zfinance.services.external.AuthManagerService;
import com.zfinance.services.external.IssuerService;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private IssuerService issuerService;

	@Autowired
	private AuthManagerService authManagerService;

	@Autowired
	private TokenAuthorizationFilter tokenAuthorizationFilter;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public List<BankAccount> viewMyBankAccounts(MyBankAccountsFilter myBankAccountsFilter,
			BankAccountsSort bankAccountsSort) {
		Criteria criteria = new Criteria();

		if (myBankAccountsFilter != null) {
			if (myBankAccountsFilter.getBankAccountNumber() != null) {
				criteria.and("details.bankAccountNumber").is(myBankAccountsFilter.getBankAccountNumber());
			}
			if (myBankAccountsFilter.getBankName() != null) {
				criteria.and("details.name").is(myBankAccountsFilter.getBankName());
			}
			if (myBankAccountsFilter.getBic() != null) {
				criteria.and("details.bic").is(myBankAccountsFilter.getBic());
			}
			if (myBankAccountsFilter.getIban() != null) {
				criteria.and("details.iban").is(myBankAccountsFilter.getIban());
			}
			if (myBankAccountsFilter.getStatuses() != null && !myBankAccountsFilter.getStatuses().isEmpty()) {
				criteria.and("status").in(myBankAccountsFilter.getStatuses());
			}
			if (myBankAccountsFilter.getSwift() != null) {
				criteria.and("details.swift").is(myBankAccountsFilter.getSwift());
			}
			if (myBankAccountsFilter.getCoinSerial() != null) {
				criteria.and("details.coinSerial").is(myBankAccountsFilter.getCoinSerial());
			}
			if (myBankAccountsFilter.getOrganization() != null) {
				if (myBankAccountsFilter.getOrganization().getId() != null) {
					criteria.and("details.organization.id").is(myBankAccountsFilter.getOrganization().getId());
				}
				if (myBankAccountsFilter.getOrganization().getName() != null) {
					criteria.and("details.organization.name").is(myBankAccountsFilter.getOrganization().getName());
				}
				if (myBankAccountsFilter.getOrganization().getType() != null) {
					criteria.and("details.organization.type").is(myBankAccountsFilter.getOrganization().getType());
				}
				// Add more organization-related criteria here...
			}
		}

		Query query = new Query(criteria);

		// Apply sorting
		if (bankAccountsSort != null) {
			if (bankAccountsSort.getBankName() != null) {
				if (bankAccountsSort.getBankName().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("details.name")));
				} else if (bankAccountsSort.getBankName().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("details.name")));
				}
			}
			if (bankAccountsSort.getStatus() != null) {
				if (bankAccountsSort.getStatus().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("status")));
				} else if (bankAccountsSort.getStatus().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("status")));
				}
			}
		}

		return mongoTemplate.find(query, BankAccount.class);
	}

	@Override
	public List<BankAccount> viewBankAccounts(BankAccountsFilter bankAccountsFilter,
			BankAccountsSort bankAccountsSort) {
		Criteria criteria = new Criteria();

		// Add bankAccountsFilter criteria based on the bankAccountsFilter object
		if (bankAccountsFilter != null) {
			if (bankAccountsFilter.getBankAccountNumber() != null) {
				criteria.and("details.bankAccountNumber").is(bankAccountsFilter.getBankAccountNumber());
			}
			if (bankAccountsFilter.getBankName() != null) {
				criteria.and("details.name").is(bankAccountsFilter.getBankName());
			}
			if (bankAccountsFilter.getBic() != null) {
				criteria.and("details.bic").is(bankAccountsFilter.getBic());
			}
			if (bankAccountsFilter.getIban() != null) {
				criteria.and("details.iban").is(bankAccountsFilter.getIban());
			}
			if (bankAccountsFilter.getStatuses() != null && !bankAccountsFilter.getStatuses().isEmpty()) {
				criteria.and("status").in(bankAccountsFilter.getStatuses());
			}
			if (bankAccountsFilter.getSwift() != null) {
				criteria.and("details.swift").is(bankAccountsFilter.getSwift());
			}
			if (bankAccountsFilter.getUserIds() != null && !bankAccountsFilter.getUserIds().isEmpty()) {
				criteria.and("userId").in(bankAccountsFilter.getUserIds());
			}
			// Add other bankAccountsFilter criteria as needed...
		}

		Query query = new Query(criteria);

		// Apply sorting
		if (bankAccountsSort != null) {
			if (bankAccountsSort.getBankName() != null) {
				query.with(Sort.by(Sort.Order.asc("details.name")));
			}
			if (bankAccountsSort.getStatus() != null) {
				query.with(Sort.by(Sort.Order.asc("status")));
			}
			// Add other sorting criteria as needed...
		}

		return mongoTemplate.find(query, BankAccount.class);
	}

	@Override
	public void approveBankAccount(String bankAccountId) {
		Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(bankAccountId);

		if (bankAccountOptional.isPresent()) {
			BankAccount bankAccount = bankAccountOptional.get();
			bankAccount.setStatus(StatusEnum.APPROVED.getCode());
			bankAccountRepository.save(bankAccount);
		} else {
			throw new DataNotFoundException(BankAccount.class, bankAccountId);
		}
	}

	@Override
	public void rejectBankAccount(String bankAccountId) {
		Optional<BankAccount> bankAccountOptional = bankAccountRepository.findById(bankAccountId);

		if (bankAccountOptional.isPresent()) {
			BankAccount bankAccount = bankAccountOptional.get();
			bankAccount.setStatus(StatusEnum.DECLINED.getCode());
			bankAccountRepository.save(bankAccount);
		} else {
			throw new DataNotFoundException(BankAccount.class, bankAccountId);
		}
	}

	@Override
	public BankAccount save(BankAccount bankAccount) {
		if (bankAccount.getId() == null)
			bankAccount.setId(sequenceGeneratorService.generateSequence(BankAccount.SEQUENCE_NAME));

		return bankAccountRepository.save(bankAccount);
	}

	@Override
	public BankAccount createBankAccount(BankAccountBody bankAccountBody) {
		String token = tokenAuthorizationFilter.getToken();
		UserRecord user = authManagerService.getUserFromToken(token);

		Issuer issuerBody = issuerService.getIssuerById(bankAccountBody.getIssuerId());
		BankAccountsFilter bankAccountsFilter = new BankAccountsFilter();
		bankAccountsFilter.setUserIds(Arrays.asList(user.getId()));
		List<BankAccount> userBankAccounts = viewBankAccounts(bankAccountsFilter, null);

		if (!userBankAccounts.isEmpty() && userBankAccounts.size() > 3)
			throw new BusinessException("error_maxNoBankAccountExcceed");
		BankAccount bankAccount = new BankAccount();

		bankAccount.setCreatedAt((new Date()).toString());
		bankAccount.setUserId(user.getId());
		bankAccount.setStatus("active");

		BankAccountDetails bankAccountDetails = new BankAccountDetails();
		bankAccountDetails.setBankAccountNumber(bankAccountBody.getAccountNumber());
		bankAccountDetails.setBic(bankAccountBody.getBic());
		bankAccountDetails.setIban(bankAccountBody.getIbanNumber());
		bankAccountDetails.setName(bankAccountBody.getBankName());// TODO: need to be checked
		bankAccountDetails.setFullName(bankAccountBody.getHolderName());
		bankAccountDetails.setSwift(bankAccountBody.getSwift());
		bankAccountDetails.setIssuer(issuerBody);

		bankAccount.setDetails(bankAccountDetails);
		return save(bankAccount);
	}

	@Override
	public List<BankAccount> viewSignedInBankAccounts() {
		String token = tokenAuthorizationFilter.getToken();
		UserRecord user = authManagerService.getUserFromToken(token);
		BankAccountsFilter bankAccountsFilter = new BankAccountsFilter();
		bankAccountsFilter.setUserIds(Arrays.asList(user.getId()));
		return viewBankAccounts(bankAccountsFilter, null);
	}

	@Override
	public List<BankAccount> viewBankAccountsByUserId(String UserId) {
		BankAccountsFilter bankAccountsFilter = new BankAccountsFilter();
		bankAccountsFilter.setUserIds(Arrays.asList(UserId));
		return viewBankAccounts(bankAccountsFilter, null);
	}

}
