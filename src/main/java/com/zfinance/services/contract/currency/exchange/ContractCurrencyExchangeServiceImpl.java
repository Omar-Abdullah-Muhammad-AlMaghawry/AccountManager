package com.zfinance.services.contract.currency.exchange;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.contract.currrency.exchange.ContractCurrencyExchangeFilter;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.contract.currency.exchange.ContractCurrencyExchange;
import com.zfinance.repositories.contract.currency.exchange.ContractCurrencyExchangeRepository;

@Service
public class ContractCurrencyExchangeServiceImpl implements ContractCurrencyExchangeService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ContractCurrencyExchangeRepository contractCurrencyExchangeRepository;

	@Override
	public List<ContractCurrencyExchange> searchContractCurrencyExchanges(
			ContractCurrencyExchangeFilter contractCurrencyExchangeFilter) {

		Criteria criteria = new Criteria();

		// Add contractCurrencyExchangeFilter criteria based on the
		// contractCurrencyExchangeFilter object
		if (contractCurrencyExchangeFilter != null) {
			if (contractCurrencyExchangeFilter.getType() != null) {
				criteria.and("type").is(contractCurrencyExchangeFilter.getType());
			}
			if (contractCurrencyExchangeFilter.getStartDate() != null) {
				// Add date range criteria if needed
				criteria.and("start_date").gte(contractCurrencyExchangeFilter.getStartDate());
			}
			if (contractCurrencyExchangeFilter.getEndDate() != null) {
				// Add date range criteria if needed
				criteria.and("end_date").lte(contractCurrencyExchangeFilter.getEndDate());
			}
			if (contractCurrencyExchangeFilter.getStatus() != null) {
				criteria.and("status").is(contractCurrencyExchangeFilter.getStatus());
			}
			// Add other contractCurrencyExchangeFilter criteria as needed...
		}

		Query query = new Query(criteria);

		// Apply sorting if needed
		// query.with(Sort.by(Sort.Order.asc("fieldName")));

		return mongoTemplate.find(query, ContractCurrencyExchange.class);

	}

	@Override
	public List<ContractCurrencyExchange> getAllContractCurrencyExchanges() {
		return contractCurrencyExchangeRepository.findAll();
	}

	@Override
	public ContractCurrencyExchange getContractCurrencyExchangeById(String id) {
		return contractCurrencyExchangeRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Wallet.class,
				id));
	}

	@Override
	public ContractCurrencyExchange saveContractCurrencyExchange(ContractCurrencyExchange contractCurrencyExchange) {
		return contractCurrencyExchangeRepository.save(contractCurrencyExchange);
	}

	@Override
	public void deleteContractCurrencyExchangeById(String id) {
		contractCurrencyExchangeRepository.deleteById(id);
	}

}
