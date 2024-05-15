package com.zfinance.repositories.transactionBrief;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.transactionBrief.TransactionBrief;

public interface TransactionBriefRepository extends MongoRepository<TransactionBrief, String> {

}
