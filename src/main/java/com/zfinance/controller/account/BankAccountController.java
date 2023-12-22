package com.zfinance.controller.account;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.request.PaginationRequestOptions;
import com.zfinance.dto.request.account.BankAccountBody;
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
		paginationResponse.setPageSize(options.getPageSize() != null ? Integer.valueOf(options.getPageSize()) : null);

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

	@PostMapping("/save")
	public BankAccountRecord saveBankAccount(@RequestBody BankAccountRecord bankAccountRecord) {
		return BankAccountMapper.INSTANCE.mapBankAccount(bankAccountService.save(BankAccountMapper.INSTANCE
				.mapBankAccountRecord(bankAccountRecord)));
	}

	@PostMapping("/create")
	public BankAccountRecord createBankAccount(@RequestBody BankAccountBody bankAccountBody) {
		return BankAccountMapper.INSTANCE.mapBankAccount(bankAccountService.createBankAccount(bankAccountBody));
	}

	@GetMapping("/view-by-user-id/{userId}")
	public List<BankAccountRecord> viewBankAccountsByUserId(@PathVariable String userId) {

		List<BankAccount> bankAccounts = bankAccountService.viewBankAccountsByUserId(userId);

		return BankAccountMapper.INSTANCE.mapBankAccounts(bankAccounts);
	}
}
