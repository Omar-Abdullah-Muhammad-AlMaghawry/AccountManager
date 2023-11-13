package com.zfinance.services.contract.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.contract.provider.ContractProviderFilter;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.contract.provider.ContractProvider;
import com.zfinance.repositories.contract.provider.ContractProviderRepository;
import com.zfinance.services.database.sequence.SequenceGeneratorService;

@Service
public class ContractProviderServiceImpl implements ContractProviderService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private ContractProviderRepository contractProviderRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Override
	public List<ContractProvider> searchContractProviders(ContractProviderFilter contractProviderFilter) {

		Criteria criteria = new Criteria();
		Criteria regex = null;
		// Add contractProviderFilter criteria based on the
		// contractProviderFilter object
		if (contractProviderFilter != null) {
			if (contractProviderFilter.getName() != null) {
				regex = Criteria.where("name").regex(contractProviderFilter.getName(), "i");
//				criteria.and("type").is(contractProviderFilter.getType());
			}
			if (contractProviderFilter.getStartDate() != null) {
				// Add date range criteria if needed
				criteria.and("start_date").gte(contractProviderFilter.getStartDate());
			}
			if (contractProviderFilter.getEndDate() != null) {
				// Add date range criteria if needed
				criteria.and("end_date").lte(contractProviderFilter.getEndDate());
			}
			if (contractProviderFilter.getStatus() != null) {
				criteria.and("status").is(contractProviderFilter.getStatus());
			}
			// Add other contractProviderFilter criteria as needed...
		}

		Query query = new Query(criteria);

		if (regex != null)
			query.addCriteria(regex);

		// Apply sorting if needed
		// query.with(Sort.by(Sort.Order.asc("fieldName")));

		return mongoTemplate.find(query, ContractProvider.class);

	}

	@Override
	public List<ContractProvider> getAllContractProviders() {
		return contractProviderRepository.findAll();
	}

	@Override
	public ContractProvider getContractProviderById(String id) {
		return contractProviderRepository.findById(id).orElseThrow(() -> new DataNotFoundException(Wallet.class, id));
	}

	@Override
	public ContractProvider saveContractProvider(ContractProvider contractProvider) {
		if (contractProvider != null && contractProvider.getId() == null)
			contractProvider.setId(sequenceGeneratorService.generateSequence(ContractProvider.SEQUENCE_NAME));

		return contractProviderRepository.save(contractProvider);
	}

	@Override
	public void deleteContractProviderById(String id) {
		contractProviderRepository.deleteById(id);
	}

}
