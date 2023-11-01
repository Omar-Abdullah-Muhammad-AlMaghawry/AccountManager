package com.zfinance.services.contract.currency.exchange;

import java.util.List;

import com.zfinance.dto.request.contract.currrency.exchange.ContractCurrencyExchangeFilter;
import com.zfinance.orm.contract.currency.exchange.ContractCurrencyExchange;

public interface ContractCurrencyExchangeService {

	public List<ContractCurrencyExchange> searchContractCurrencyExchanges(
			ContractCurrencyExchangeFilter contractCurrencyExchangeFilter);

	public List<ContractCurrencyExchange> getAllContractCurrencyExchanges();

	public ContractCurrencyExchange getContractCurrencyExchangeById(String id);

	public ContractCurrencyExchange saveContractCurrencyExchange(ContractCurrencyExchange contractCurrencyExchange);

	public void deleteContractCurrencyExchangeById(String id);

}
