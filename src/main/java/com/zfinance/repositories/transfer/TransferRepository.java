package com.zfinance.repositories.transfer;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.transfer.Transfer;

public interface TransferRepository extends MongoRepository<Transfer, String> {

}
