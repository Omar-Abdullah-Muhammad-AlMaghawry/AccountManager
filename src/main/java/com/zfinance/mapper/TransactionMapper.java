package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.transaction.TransactionsRecord;
import com.zfinance.orm.transaction.Transaction;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {

	TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

	public TransactionsRecord mapTransaction(Transaction transaction);

	public Transaction mapTransactionsRecord(TransactionsRecord walletRecord);

	public default Page<TransactionsRecord> mapTransactions(Page<Transaction> transactions) {
		return transactions.map(this::mapTransaction);
	}

	public List<TransactionsRecord> mapTransactions(List<Transaction> transactions);

}
