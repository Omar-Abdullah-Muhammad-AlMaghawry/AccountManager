package com.zfinance.repositories.systemOperation;

import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.systemOperation.SystemOperation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SystemOperationRepository extends MongoRepository<SystemOperation, String> {
}
