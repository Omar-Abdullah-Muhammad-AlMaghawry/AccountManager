package com.zfinance.repositories.transactionBrief;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.zfinance.orm.transactionBrief.TransactionBrief;

public interface TransactionBriefRepository extends MongoRepository<TransactionBrief, String> {

	List<TransactionBrief> findAllByFromUserId(String id, Sort sort);

	List<TransactionBrief> findAllByToUserId(String id, Sort sort);

	@Query("{ 'date': { $gte: ?0 }, 'from_user_id': ?1 }")
	List<TransactionBrief> findByGteDateAndFromUserId(Date date, String fromUserId);

	@Query("{ 'date': { $gte: ?0 }, 'to_user_id': ?1 }")
	List<TransactionBrief> findByGteDateAndToUserId(Date date, String toUserId);

}
