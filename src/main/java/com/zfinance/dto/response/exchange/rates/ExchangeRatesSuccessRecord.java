package com.zfinance.dto.response.exchange.rates;

import java.util.List;

import com.zfinance.orm.userdefinedtypes.transaction.ClientCoin;
import com.zfinance.orm.userdefinedtypes.transaction.TransactionsTransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ExchangeRatesSuccessRecord {
	private String id;
	private String createdAt;
	private String updatedAt;
	private String type;
	private String status;
	private Integer requestIdentifier;
	private String requestStatus;
	private Double incomingAmount;
	private Double outgoingAmount;
	private ClientCoin destClientCoin;
	private ClientCoin destExchangerCoin;
	private ClientCoin srcClientCoin;
	private ClientCoin srcExchangerCoin;
	private List<TransactionsTransaction> transactions;
	private List<TransactionsTransaction> children;
	private String errorMessage;
}
