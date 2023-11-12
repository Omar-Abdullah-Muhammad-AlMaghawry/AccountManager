package com.zfinance.dto.response.exchange;

import java.util.List;

import com.zfinance.orm.userdefinedtypes.exchanges.ExchangeSuccessChildren;
import com.zfinance.orm.userdefinedtypes.transaction.TransactionsTransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeSuccessRecord {

	private String id;
	private String createdAt;
	private String updatedAt;
	private String type;
	private String status;
	private Integer requestIdentifier;
	private String requestStatus;
	private List<TransactionsTransaction> transactions;
	private List<ExchangeSuccessChildren> children;
	private String errorMessage;
}
