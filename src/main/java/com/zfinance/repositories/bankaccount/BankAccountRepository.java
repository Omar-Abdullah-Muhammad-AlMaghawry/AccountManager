package com.zfinance.repositories.bankaccount;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.zfinance.orm.account.BankAccount;

public interface BankAccountRepository extends CassandraRepository<BankAccount, String> {

}
