package com.zfinance.orm.userdefinedtypes.account;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@UserDefinedType("bank_account_type")
public class BankAccountDetails {

	@Column("bank_account_number")
	private String bankAccountNumber;

	@Column("address")
	private String address;

	@Column("bic")
	private String bic;

	@Column("full_name")
	private String fullName;

	@Column("iban")
	private String iban;

	@Column("name")
	private String name;

	@Column("swift")
	private String swift;
}
