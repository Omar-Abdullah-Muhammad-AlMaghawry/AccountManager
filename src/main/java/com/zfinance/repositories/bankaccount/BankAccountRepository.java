package com.zfinance.repositories.bankaccount;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.account.BankAccount;

public interface BankAccountRepository extends MongoRepository<BankAccount, String> {

}
