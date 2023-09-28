package com.zfinance.controller.transaction;

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
import com.zfinance.dto.request.transaction.TransactionsFilter;
import com.zfinance.dto.request.transaction.TransactionsSort;
import com.zfinance.dto.response.PaginationResponse;
import com.zfinance.dto.response.transaction.TransactionResponse;
import com.zfinance.dto.response.transaction.TransactionsRecord;
import com.zfinance.mapper.TransactionMapper;
import com.zfinance.orm.transaction.Transaction;
import com.zfinance.services.transaction.TransactionService;

@RestController
@RequestMapping("/transactions")
@CrossOrigin("*")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/view")
	public PaginationResponse<TransactionsRecord> getRecords(
			@RequestBody PaginationRequestOptions<TransactionsFilter, TransactionsSort> options) {
		List<Transaction> transactions = transactionService.getRecords(options.getFilter(), options.getSort());

		PaginationResponse<TransactionsRecord> paginationResponse = new PaginationResponse<>();
		paginationResponse.setRecords(TransactionMapper.INSTANCE.mapTransactions(transactions));
		paginationResponse.setTotalRecords(transactions.size());

		// TODO: to be edit
		paginationResponse.setPageSize(options.getPageSize() != null ? Integer.valueOf(options.getPageSize()) : null);
		paginationResponse.setPageNumber(options.getPageNumber() != null ? Integer.valueOf(options.getPageNumber())
				: null);
		return paginationResponse;
	}

	@GetMapping("/{transactionId}")
	public TransactionResponse getRecord(@PathVariable String transactionId) {
		TransactionsRecord transactionsRecord = TransactionMapper.INSTANCE.mapTransaction(transactionService.getRecord(
				transactionId));
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setProcess(transactionsRecord);
		return transactionResponse;
	}

	@PostMapping("/save")
	public TransactionsRecord saveTransactions(@RequestBody TransactionsRecord transactionsRecord) {
		return TransactionMapper.INSTANCE.mapTransaction(transactionService.save(TransactionMapper.INSTANCE
				.mapTransactionsRecord(transactionsRecord)));
	}

}
