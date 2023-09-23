package com.zfinance.controller.collect;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.request.collect.CollectBody;
import com.zfinance.dto.response.transaction.TransactionResponse;
import com.zfinance.dto.response.transaction.TransactionsRecord;
import com.zfinance.mapper.TransactionMapper;
import com.zfinance.services.collect.CollectService;

@RestController
@RequestMapping("/collects")
@CrossOrigin("*")
public class CollectController {

	@Autowired
	private CollectService collectService;

	@PostMapping
	public TransactionResponse create(@RequestBody CollectBody data) {
		TransactionsRecord transactionsRecord = TransactionMapper.INSTANCE.mapTransaction(collectService.create(data));
		TransactionResponse transactionResponse = new TransactionResponse();
		transactionResponse.setProcess(transactionsRecord);
		return transactionResponse;
	}

}
