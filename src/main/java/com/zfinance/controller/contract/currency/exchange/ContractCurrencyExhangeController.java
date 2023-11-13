package com.zfinance.controller.contract.currency.exchange;

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
import com.zfinance.dto.request.contract.currrency.exchange.ContractCurrencyExchangeFilter;
import com.zfinance.dto.response.PaginationResponse;
import com.zfinance.dto.response.contract.currency.exchange.ContractCurrencyExchangeRecord;
import com.zfinance.mapper.ContractCurrencyExchangeMapper;
import com.zfinance.orm.contract.currency.exchange.ContractCurrencyExchange;
import com.zfinance.services.contract.currency.exchange.ContractCurrencyExchangeService;

@RestController
@RequestMapping("/contract-currency-exchange")
@CrossOrigin("*")
public class ContractCurrencyExhangeController {

	@Autowired
	private ContractCurrencyExchangeService contractCurrencyExchangeService;

	@PostMapping("/view")
	public PaginationResponse<ContractCurrencyExchangeRecord> searchRecords(
			@RequestBody PaginationRequestOptions<ContractCurrencyExchangeFilter, ?> data) {

		List<ContractCurrencyExchange> contractCurrencyExchanges = contractCurrencyExchangeService
				.searchContractCurrencyExchanges(data.getFilter());

		PaginationResponse<ContractCurrencyExchangeRecord> paginationResponse = new PaginationResponse<>();
		paginationResponse.setRecords(ContractCurrencyExchangeMapper.INSTANCE.mapContractCurrencyExchanges(
				contractCurrencyExchanges));
		paginationResponse.setTotalRecords(contractCurrencyExchanges.size());

		// TODO: to be edit
		paginationResponse.setPageSize(data.getPageSize() != null ? Integer.valueOf(data.getPageSize()) : null);
		paginationResponse.setPageNumber(data.getPageNumber() != null ? Integer.valueOf(data.getPageNumber()) : null);

		return paginationResponse;
	}

	@GetMapping("/getAll")
	public List<ContractCurrencyExchangeRecord> getAllContractCurrencyExchange() {

		return ContractCurrencyExchangeMapper.INSTANCE.mapContractCurrencyExchanges(contractCurrencyExchangeService
				.getAllContractCurrencyExchanges());
	}

	@GetMapping("/{id}")
	public ContractCurrencyExchangeRecord getContractCurrencyExchangeById(@PathVariable String id) {

		return ContractCurrencyExchangeMapper.INSTANCE.mapContractCurrencyExchange(contractCurrencyExchangeService
				.getContractCurrencyExchangeById(id));
	}

	@PostMapping
	public ContractCurrencyExchangeRecord saveContractCurrencyExchange(
			@RequestBody ContractCurrencyExchangeRecord contractCurrencyExchangeRecord) {
		return ContractCurrencyExchangeMapper.INSTANCE.mapContractCurrencyExchange(contractCurrencyExchangeService
				.saveContractCurrencyExchange(ContractCurrencyExchangeMapper.INSTANCE.mapContractCurrencyExchangeRecord(
						contractCurrencyExchangeRecord)));
	}

	@DeleteMapping("/{id}")
	public void deleteContractCurrencyExchangeById(@PathVariable String id) {
		contractCurrencyExchangeService.deleteContractCurrencyExchangeById(id);

	}

}
