package com.zfinance.services.account;

import java.util.List;

import com.zfinance.dto.request.account.BankAccountsFilter;
import com.zfinance.dto.request.account.BankAccountsSort;
import com.zfinance.dto.request.account.MyBankAccountsFilter;
import com.zfinance.orm.account.BankAccount;

public interface BankAccountService {

	public List<BankAccount> viewMyBankAccounts(MyBankAccountsFilter myBankAccountsFilter,
			BankAccountsSort bankAccountsSort);

	public List<BankAccount> viewBankAccounts(BankAccountsFilter bankAccountsFilter, BankAccountsSort bankAccountsSort);

	public void approveBankAccount(String bankAccountId);

	public void rejectBankAccount(String bankAccountId);

}