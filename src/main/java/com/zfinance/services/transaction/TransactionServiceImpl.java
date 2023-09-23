package com.zfinance.services.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.transaction.TransactionsFilter;
import com.zfinance.dto.request.transaction.TransactionsSort;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.transaction.Transaction;
import com.zfinance.repositories.transaction.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> getRecords(TransactionsFilter transactionsFilter, TransactionsSort transactionsSort) {
		if (transactionsFilter != null)
			return transactionRepository.findAllByFilter(transactionsFilter.getIds(), transactionsFilter.getTypes(),
					transactionsFilter.getStatuses(), transactionsFilter.getCreatedAtFrom(), transactionsFilter
							.getCreatedAtTo(), transactionsFilter.getCoinSerials(), transactionsFilter
									.getSenderCoinNames(), transactionsFilter.getRecipientCoinNames(),
					transactionsFilter.getOrgIds(), transactionsFilter.getIssuerIds(), transactionsFilter
							.getCurrencyCodes(), transactionsFilter.getRequestIdentifiers(), transactionsFilter
									.getFromEmails(), transactionsFilter.getToEmails(), transactionsFilter
											.getFromPhoneNumbers(), transactionsFilter.getToPhoneNumbers());
		else
			return transactionRepository.findAll();
	}

	@Override
	public Transaction getRecord(String transactionId) {
		Optional<Transaction> optional = transactionRepository.findById(transactionId);
		if (optional.isPresent())
			return optional.get();
		else
			throw new DataNotFoundException(Transaction.class, transactionId);
	}

	@Override
	public Transaction save(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

}
