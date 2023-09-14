package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.account.BankAccountRecord;
import com.zfinance.orm.account.BankAccount;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BankAccountMapper {

	BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

	public BankAccountRecord mapBankAccount(BankAccount bankAccount);

	public BankAccount mapBankAccountRecord(BankAccountRecord bankAccountRecord);

	public default Page<BankAccountRecord> mapBankAccounts(Page<BankAccount> bankAccounts) {
		return bankAccounts.map(this::mapBankAccount);
	}

	public List<BankAccountRecord> mapBankAccounts(List<BankAccount> bankAccounts);

}
