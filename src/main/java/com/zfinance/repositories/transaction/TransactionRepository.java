package com.zfinance.repositories.transaction;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.transaction.Transaction;

public interface TransactionRepository extends MongoRepository<Transaction, String> {

}
