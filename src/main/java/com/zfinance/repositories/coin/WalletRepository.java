package com.zfinance.repositories.coin;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.zfinance.orm.coin.Wallet;

public interface WalletRepository extends CassandraRepository<Wallet, String> {

}
