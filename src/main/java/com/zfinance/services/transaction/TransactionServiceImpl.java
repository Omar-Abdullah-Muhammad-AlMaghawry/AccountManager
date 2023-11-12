package com.zfinance.services.transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.transaction.TransactionsFilter;
import com.zfinance.dto.request.transaction.TransactionsSort;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.transaction.Transaction;
import com.zfinance.orm.userdefinedtypes.exchange.rates.Issuer;
import com.zfinance.repositories.transaction.TransactionRepository;
import com.zfinance.services.external.IssuerService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private TransactionRepository transactionRepository;

	@Autowired
	private IssuerService issuerService;

	@Override
	public List<Transaction> getRecords(TransactionsFilter transactionsFilter, TransactionsSort transactionsSort) {
		Criteria criteria = new Criteria();

		// Add transactionsFilter criteria based on the transactionsFilter object
		if (transactionsFilter != null) {
			if (transactionsFilter.getIds() != null && !transactionsFilter.getIds().isEmpty()) {
				criteria.and("id").in(transactionsFilter.getIds());
			}
			if (transactionsFilter.getTypes() != null && !transactionsFilter.getTypes().isEmpty()) {
				criteria.and("type").in(transactionsFilter.getTypes());
			}
			if (transactionsFilter.getStatuses() != null && !transactionsFilter.getStatuses().isEmpty()) {
				criteria.and("status").in(transactionsFilter.getStatuses());
			}
			if (transactionsFilter.getCreatedAtFrom() != null) {
				criteria.and("createdAt").gte(transactionsFilter.getCreatedAtFrom());
			}
			if (transactionsFilter.getCreatedAtTo() != null) {
				criteria.and("createdAt").lte(transactionsFilter.getCreatedAtTo());
			}
			if (transactionsFilter.getCoinSerials() != null && !transactionsFilter.getCoinSerials().isEmpty()) {
				criteria.and("coin.serial").in(transactionsFilter.getCoinSerials());
			}
			if (transactionsFilter.getSenderCoinNames() != null && !transactionsFilter.getSenderCoinNames().isEmpty()) {
				criteria.and("from.name").in(transactionsFilter.getSenderCoinNames());
			}
			if (transactionsFilter.getRecipientCoinNames() != null && !transactionsFilter.getRecipientCoinNames()
					.isEmpty()) {
				criteria.and("to.name").in(transactionsFilter.getRecipientCoinNames());
			}
			if (transactionsFilter.getOrgIds() != null && !transactionsFilter.getOrgIds().isEmpty()) {
				criteria.and("from.organizationId").in(transactionsFilter.getOrgIds());
			}
			if (transactionsFilter.getIssuerIds() != null && !transactionsFilter.getIssuerIds().isEmpty()) {
				criteria.and("from.issuer.serial").in(transactionsFilter.getIssuerIds());
			}
			if (transactionsFilter.getCurrencyCodes() != null && !transactionsFilter.getCurrencyCodes().isEmpty()) {
				List<String> currencySymbols = new ArrayList<>();
				for (String currencyCode : transactionsFilter.getCurrencyCodes()) {
					Issuer coinIssuer = issuerService.getIssuerByCurrencyCode(currencyCode);
					currencySymbols.add(coinIssuer.getSymbol());
				}
				criteria.and("coin.issuer.symbol").in(currencySymbols);
			}
			if (transactionsFilter.getRequestIdentifiers() != null && !transactionsFilter.getRequestIdentifiers()
					.isEmpty()) {
				criteria.and("requestIdentifier").in(transactionsFilter.getRequestIdentifiers());
			}
			if (transactionsFilter.getFromEmails() != null && !transactionsFilter.getFromEmails().isEmpty()) {
				criteria.and("from.email").in(transactionsFilter.getFromEmails());
			}
			if (transactionsFilter.getToEmails() != null && !transactionsFilter.getToEmails().isEmpty()) {
				criteria.and("to.email").in(transactionsFilter.getToEmails());
			}
			if (transactionsFilter.getFromPhoneNumbers() != null && !transactionsFilter.getFromPhoneNumbers()
					.isEmpty()) {
				criteria.and("from.phoneNumber").in(transactionsFilter.getFromPhoneNumbers());
			}
			if (transactionsFilter.getToPhoneNumbers() != null && !transactionsFilter.getToPhoneNumbers().isEmpty()) {
				criteria.and("to.phoneNumber").in(transactionsFilter.getToPhoneNumbers());
			}
			// Add other transactionsFilter criteria as needed...
		}

		Query query = new Query(criteria);

		// Apply sorting
		if (transactionsSort != null) {
			if (transactionsSort.getCreatedAt() != null) {
				if (transactionsSort.getCreatedAt().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("createdAt")));
				} else if (transactionsSort.getCreatedAt().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("createdAt")));
				}
			}
			if (transactionsSort.getStatus() != null) {
				if (transactionsSort.getStatus().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("status")));
				} else if (transactionsSort.getStatus().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("status")));
				}
			}
			if (transactionsSort.getType() != null) {
				if (transactionsSort.getType().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("type")));
				} else if (transactionsSort.getType().equalsIgnoreCase("desc")) {
					query.with(Sort.by(Sort.Order.desc("type")));
				}
			}
		}

		return mongoTemplate.find(query, Transaction.class);
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
