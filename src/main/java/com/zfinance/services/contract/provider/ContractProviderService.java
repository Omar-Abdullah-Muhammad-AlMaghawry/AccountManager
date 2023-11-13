package com.zfinance.services.contract.provider;

import java.util.List;

import com.zfinance.dto.request.contract.provider.ContractProviderFilter;
import com.zfinance.orm.contract.provider.ContractProvider;

public interface ContractProviderService {

	public List<ContractProvider> searchContractProviders(ContractProviderFilter contractProviderFilter);

	public List<ContractProvider> getAllContractProviders();

	public ContractProvider getContractProviderById(String id);

	public ContractProvider saveContractProvider(ContractProvider contractProvider);

	public void deleteContractProviderById(String id);

}
