package com.zfinance.dto.response.account;

import com.zfinance.orm.userdefinedtypes.account.BankAccountDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BankAccountRecord {

	private String id;
	private String createdAt;
	private BankAccountDetails details;
	private String status;
	private String updatedAt;
	private String userId;

}
