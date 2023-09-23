package com.zfinance.repositories.transaction;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;

import com.zfinance.orm.transaction.Transaction;

public interface TransactionRepository extends CassandraRepository<Transaction, String> {

	@Query("SELECT * FROM zfin_transaction "
//	+ " WHERE organization_id = :p_organization_id " 
			+ " ALLOW FILTERING ")
	List<Transaction> findAllByFilter(List<String> ids, List<String> types, List<String> statuses, String createdAtFrom,
			String createdAtTo, List<String> coinSerials, List<String> senderCoinNames, List<String> recipientCoinNames,
			List<String> orgIds, List<String> issuerIds, List<String> currencyCodes, List<Integer> requestIdentifiers,
			List<String> fromEmails, List<String> toEmails, List<String> fromPhoneNumbers, List<String> toPhoneNumbers);

}
