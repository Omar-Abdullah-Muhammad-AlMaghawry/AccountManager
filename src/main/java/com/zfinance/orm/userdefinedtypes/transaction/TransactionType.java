package com.zfinance.orm.userdefinedtypes.transaction;

import java.util.List;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@UserDefinedType("transaction_type")
public class TransactionType {

	@Column("id")
	private String id;

	@Column("net_amount")
	private Double netAmount;

	// TODO : to be asked for the children
//	@Column("children")
//	private List<TransactionType> children;

	@Column("created_at")
	private String createdAt;

	@Column("updated_at")
	private String updatedAt;

	@Column("description")
	private String description;

	@Column("to")
	private Target to;

	@Column("from")
	private Target from;

	@Column("client_coin")
	private ClientCoin clientCoin;

	@Column("request_identifier")
	private Integer requestIdentifier;

	@Column("request_status")
	private String requestStatus;

	@Column("status")
	private String status;

	@Column("pos_company_name")
	private String posCompanyName;

	@Column("category_image_link")
	private String categoryImageLink;

	@Column("category_name")
	private String categoryName;

	@Column("sender")
	private SenderRecipient sender;

	@Column("transactions")
	private List<TransactionsTransaction> transactions;

	@Column("type")
	private String type;

	@Column("amount")
	private Double amount;

	@Column("invoice_amount")
	private Double invoiceAmount;

	@Column("cash_amount")
	private Double cashAmount;

	@Column("commission")
	private Double commission;

	@Column("coin")
	private TransactionCoin coin;

	@Column("incoming_amount")
	private Double incomingAmount;

	@Column("outgoing_amount")
	private Double outgoingAmount;

	@Column("operation_amount_details")
	private OperationAmountDetails operationAmountDetails;

	@Column("recipient")
	private SenderRecipient recipient;

	@Column("merchant_coin_serial")
	private String merchantCoinSerial;

	@Column("invoice_identifier")
	private String invoiceIdentifier;
}
