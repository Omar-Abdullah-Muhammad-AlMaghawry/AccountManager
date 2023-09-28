package com.zfinance.orm.userdefinedtypes.transaction;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TransactionType {

	@Field("id")
	private String id;

	@Field("net_amount")
	private Double netAmount;

	// TODO : to be asked for the children
	@Field("children")
	private List<TransactionType> children;

	@Field("created_at")
	private String createdAt;

	@Field("updated_at")
	private String updatedAt;

	@Field("description")
	private String description;

	@Field("to")
	private Target to;

	@Field("from")
	private Target from;

	@Field("client_coin")
	private ClientCoin clientCoin;

	@Field("request_identifier")
	private Integer requestIdentifier;

	@Field("request_status")
	private String requestStatus;

	@Field("status")
	private String status;

	@Field("pos_company_name")
	private String posCompanyName;

	@Field("category_image_link")
	private String categoryImageLink;

	@Field("category_name")
	private String categoryName;

	@Field("sender")
	private SenderRecipient sender;

	@Field("transactions")
	private List<TransactionsTransaction> transactions;

	@Field("type")
	private String type;

	@Field("amount")
	private Double amount;

	@Field("invoice_amount")
	private Double invoiceAmount;

	@Field("cash_amount")
	private Double cashAmount;

	@Field("commission")
	private Double commission;

	@Field("coin")
	private TransactionCoin coin;

	@Field("incoming_amount")
	private Double incomingAmount;

	@Field("outgoing_amount")
	private Double outgoingAmount;

	@Field("operation_amount_details")
	private OperationAmountDetails operationAmountDetails;

	@Field("recipient")
	private SenderRecipient recipient;

	@Field("merchant_coin_serial")
	private String merchantCoinSerial;

	@Field("invoice_identifier")
	private String invoiceIdentifier;
}
