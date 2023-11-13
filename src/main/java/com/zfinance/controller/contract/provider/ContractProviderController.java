package com.zfinance.controller.contract.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.request.PaginationRequestOptions;
import com.zfinance.dto.request.contract.provider.ContractProviderFilter;
import com.zfinance.dto.response.PaginationResponse;
import com.zfinance.dto.response.contract.provider.ContractProviderRecord;
import com.zfinance.mapper.ContractProviderMapper;
import com.zfinance.orm.contract.provider.ContractProvider;
import com.zfinance.services.contract.provider.ContractProviderService;

@RestController
@RequestMapping("/contract-provider")
@CrossOrigin("*")
public class ContractProviderController {

	@Autowired
	private ContractProviderService contractProviderService;

	@PostMapping("/view")
	public PaginationResponse<ContractProviderRecord> searchRecords(
			@RequestBody PaginationRequestOptions<ContractProviderFilter, ?> data) {

		List<ContractProvider> contractProviders = contractProviderService.searchContractProviders(data.getFilter());

		PaginationResponse<ContractProviderRecord> paginationResponse = new PaginationResponse<>();
		paginationResponse.setRecords(ContractProviderMapper.INSTANCE.mapContractProviders(contractProviders));
		paginationResponse.setTotalRecords(contractProviders.size());

		// TODO: to be edit
		paginationResponse.setPageSize(data.getPageSize() != null ? Integer.valueOf(data.getPageSize()) : null);
		paginationResponse.setPageNumber(data.getPageNumber() != null ? Integer.valueOf(data.getPageNumber()) : null);

		return paginationResponse;
	}

	@GetMapping("/getAll")
	public List<ContractProviderRecord> getAllContractProvider() {

		return ContractProviderMapper.INSTANCE.mapContractProviders(contractProviderService.getAllContractProviders());
	}

	@GetMapping("/{id}")
	public ContractProviderRecord getContractProviderById(@PathVariable String id) {

		return ContractProviderMapper.INSTANCE.mapContractProvider(contractProviderService.getContractProviderById(id));
	}

	@PostMapping
	public ContractProviderRecord saveContractProvider(@RequestBody ContractProviderRecord contractProviderRecord) {
		return ContractProviderMapper.INSTANCE.mapContractProvider(contractProviderService.saveContractProvider(
				ContractProviderMapper.INSTANCE.mapContractProviderRecord(contractProviderRecord)));
	}

	@DeleteMapping("/{id}")
	public void deleteContractProviderById(@PathVariable String id) {
		contractProviderService.deleteContractProviderById(id);

	}

}
