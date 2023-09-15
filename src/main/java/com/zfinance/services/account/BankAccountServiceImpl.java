package com.zfinance.services.account;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.account.BankAccountsFilter;
import com.zfinance.dto.request.account.BankAccountsSort;
import com.zfinance.dto.request.account.MyBankAccountsFilter;
import com.zfinance.enums.StatusEnum;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.account.BankAccount;
import com.zfinance.repositories.bankaccount.BankAccountRepository;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Override
	public List<BankAccount> viewMyBankAccounts(MyBankAccountsFilter myBankAccountsFilter,
			BankAccountsSort bankAccountsSort) {
		// TODO: CREATE THE CQL FUNCATION TO USE THE FILTER
		return bankAccountRepository.findAll();
	}

	@Override
	public List<BankAccount> viewBankAccounts(BankAccountsFilter bankAccountsFilter,
			BankAccountsSort bankAccountsSort) {
		// TODO: CREATE THE CQL FUNCATION TO USE THE FILTER
		return bankAccountRepository.findAll();

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

}
