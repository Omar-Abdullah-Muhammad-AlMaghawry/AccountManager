package com.zfinance.orm.userdefinedtypes.exchanges;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.transaction.ClientCoin;
import com.zfinance.orm.userdefinedtypes.transaction.TransactionsTransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ExchangeSuccessChildren {

	@Field("id")
	private String id;

	@Field("children")
	private List<Object> children;

	@Field("coin")
	private ClientCoin coin;

	@Field("created_at")
	private String createdAt;

	@Field("updated_at")
	private String updatedAt;

	@Field("outgoing_amount")
	private Double outgoingAmount;

	@Field("incoming_amount")
	private Double incomingAmount;

	@Field("status")
	private String status;

	@Field("transactions")
	private List<TransactionsTransaction> transactions;

	@Field("type")
	private String type;

}
