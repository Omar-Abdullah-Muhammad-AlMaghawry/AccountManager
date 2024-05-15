package com.zfinance.services.transaction;

import java.util.List;

import com.zfinance.dto.request.transaction.TransactionsFilter;
import com.zfinance.dto.request.transaction.TransactionsSort;
import com.zfinance.orm.payment.Payment;
import com.zfinance.orm.transaction.Transaction;

public interface TransactionService {

	public List<Transaction> getRecords(TransactionsFilter transactionsFilter, TransactionsSort transactionsSort);

	public Transaction getRecord(String transactionId);

	public Transaction save(Transaction transaction);
	
	public Transaction createTransaction(Payment payment);


}
