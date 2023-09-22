package com.zfinance.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.request.PaginationRequestOptions;
import com.zfinance.dto.request.account.BankAccountsFilter;
import com.zfinance.dto.request.account.BankAccountsSort;
import com.zfinance.dto.response.PaginationResponse;
import com.zfinance.dto.response.account.BankAccountRecord;
import com.zfinance.mapper.BankAccountMapper;
import com.zfinance.orm.account.BankAccount;
import com.zfinance.services.account.BankAccountService;

@RestController
@RequestMapping("/bank-accounts")
@CrossOrigin("*")
public class BankAccountController {

	@Autowired
	private BankAccountService bankAccountService;

	@PostMapping("/view")
	// TODO: make sure from the response
	public PaginationResponse<BankAccountRecord> viewBankAccounts(
			@RequestBody PaginationRequestOptions<BankAccountsFilter, BankAccountsSort> options) {

		PaginationResponse<BankAccountRecord> paginationResponse = new PaginationResponse<>();
		List<BankAccount> bankAccounts = bankAccountService.viewBankAccounts(options.getFilter(), options.getSort());

		paginationResponse.setRecords(BankAccountMapper.INSTANCE.mapBankAccounts(bankAccounts));
		paginationResponse.setTotalRecords(bankAccounts.size());

		// TODO: to be edit
		paginationResponse.setPageNumber(options.getPageNumber() != null ? Integer.valueOf(options.getPageNumber())
				: null);
		paginationResponse.setPageSize(options.getPageNumber() != null ? Integer.valueOf(options.getPageSize()) : null);

		return paginationResponse;
	}

	@PostMapping("/{bankAccountId}/approve")
	public void approveBankAccount(@PathVariable String bankAccountId) {
		bankAccountService.approveBankAccount(bankAccountId);
	}

	@PostMapping("/{bankAccountId}/reject")
	public void rejectBankAccount(@PathVariable String bankAccountId) {
		bankAccountService.rejectBankAccount(bankAccountId);
	}
}
