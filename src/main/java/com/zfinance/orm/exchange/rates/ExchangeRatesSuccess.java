package com.zfinance.orm.exchange.rates;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.transaction.ClientCoin;
import com.zfinance.orm.userdefinedtypes.transaction.TransactionsTransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_exchange_rates_success")
public class ExchangeRatesSuccess {

	@Id
	@Field("id")
	private String id;

	@Field("created_at")
	private String createdAt;

	@Field("updated_at")
	private String updatedAt;

	@Field("type")
	private String type;

	@Field("status")
	private String status;

	@Field("request_identifier")
	private Integer requestIdentifier;

	@Field("request_status")
	private String requestStatus;

	@Field("incoming_amount")
	private Double incomingAmount;

	@Field("outgoing_amount")
	private Double outgoingAmount;

	@Field("dest_client_coin")
	private ClientCoin destClientCoin;

	@Field("dest_exchanger_coin")
	private ClientCoin destExchangerCoin;

	@Field("src_client_coin")
	private ClientCoin srcClientCoin;

	@Field("src_exchanger_coin")
	private ClientCoin srcExchangerCoin;

	@Field("transactions")
	private List<TransactionsTransaction> transactions;

	@Field("children")
	private List<TransactionsTransaction> children;

	@Field("error_message")
	private String errorMessage;

	@Transient
	public static final String SEQUENCE_NAME = "exchange_rates_success_sequence";

}
