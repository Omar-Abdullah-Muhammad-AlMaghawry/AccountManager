package com.zfinance.repositories.coin;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.coin.Wallet;

public interface WalletRepository extends MongoRepository<Wallet, String> {

	List<Wallet> findAllByOrganizationId(String organizationId);

	List<Wallet> findAllByUserId(String userId);

}
