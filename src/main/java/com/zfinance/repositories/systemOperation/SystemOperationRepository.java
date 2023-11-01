package com.zfinance.repositories.systemOperation;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.zfinance.orm.systemOperation.SystemOperation;

@Repository
public interface SystemOperationRepository extends MongoRepository<SystemOperation, String> {
}
