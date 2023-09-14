package com.zfinance.services.account;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zfinance.dto.request.account.BankAccountsFilter;
import com.zfinance.dto.request.account.BankAccountsSort;
import com.zfinance.dto.request.account.MyBankAccountsFilter;
import com.zfinance.orm.account.BankAccount;

@Service
public class BankAccountServiceImpl implements BankAccountService {

	@Override
	public List<BankAccount> viewMyBankAccounts(MyBankAccountsFilter myBankAccountsFilter,
			BankAccountsSort bankAccountsSort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> viewBankAccounts(BankAccountsFilter bankAccountsFilter,
			BankAccountsSort bankAccountsSort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void approveBankAccount(String bankAccountId) {
		// TODO Auto-generated method stub

	}

	@Override
	public void rejectBankAccount(String bankAccountId) {
		// TODO Auto-generated method stub

	}

}
