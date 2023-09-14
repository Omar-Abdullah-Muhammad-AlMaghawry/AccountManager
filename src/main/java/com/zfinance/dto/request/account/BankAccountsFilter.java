package com.zfinance.dto.request.account;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BankAccountsFilter {

	private String bankAccountNumber;
	private String bankName;
	private String bic;
	private String iban;
	private List<String> statuses;
	private String swift;
	private List<String> userIds;

}
