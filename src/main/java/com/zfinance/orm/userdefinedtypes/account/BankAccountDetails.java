package com.zfinance.orm.userdefinedtypes.account;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BankAccountDetails {

	@Field("bank_account_number")
	private String bankAccountNumber;

	@Field("address")
	private String address;

	@Field("bic")
	private String bic;

	@Field("full_name")
	private String fullName;

	@Field("iban")
	private String iban;

	@Field("name")
	private String name;

	@Field("swift")
	private String swift;
}
