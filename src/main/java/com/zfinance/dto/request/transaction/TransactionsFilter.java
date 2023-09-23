package com.zfinance.dto.request.transaction;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransactionsFilter {

	private List<String> ids;
	private List<String> types;
	private List<String> statuses;
	private String createdAtFrom;
	private String createdAtTo;
	private List<String> coinSerials;
	private List<String> senderCoinNames;
	private List<String> recipientCoinNames;
	private List<String> orgIds;
	private List<String> issuerIds;
	private List<String> currencyCodes;
	private List<Integer> requestIdentifiers;
	private List<String> fromEmails;
	private List<String> toEmails;
	private List<String> fromPhoneNumbers;
	private List<String> toPhoneNumbers;

}
