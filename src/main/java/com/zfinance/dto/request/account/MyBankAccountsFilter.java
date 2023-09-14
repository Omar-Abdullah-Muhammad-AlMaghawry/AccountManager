package com.zfinance.dto.request.account;

import java.util.List;

import com.zfinance.orm.userdefinedtypes.coin.WalletOwner;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class MyBankAccountsFilter {

	private String bankAccountNumber;
	private String bankName;
	private String bic;
	private String iban;
	private List<String> statuses;
	private String swift;
	private String coinSerial;
	private WalletOwner organization;

}
