package com.zfinance.dto.request.account;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BankAccountBody {
	private String holderName;
	private String bankName;
	private String issuerId;
	private String accountCountry;
	private String ibanNumber;
	private String accountNumber;
	private String swift;
	private String bic;
}
