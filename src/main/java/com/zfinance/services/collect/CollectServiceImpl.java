package com.zfinance.services.collect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.collect.CollectBody;
import com.zfinance.orm.transaction.Transaction;
import com.zfinance.services.transaction.TransactionService;

@Service
public class CollectServiceImpl implements CollectService {

	@Autowired
	private TransactionService transactionService;

	@Override
	public Transaction create(CollectBody collectBody) {
		// TODO Auto-generated method stub
		return null;
	}

}
