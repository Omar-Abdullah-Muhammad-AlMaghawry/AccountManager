package com.zfinance.orm.transaction;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.transaction.ClientCoin;
import com.zfinance.orm.userdefinedtypes.transaction.OperationAmountDetails;
import com.zfinance.orm.userdefinedtypes.transaction.SenderRecipient;
import com.zfinance.orm.userdefinedtypes.transaction.Target;
import com.zfinance.orm.userdefinedtypes.transaction.TransactionCoin;
import com.zfinance.orm.userdefinedtypes.transaction.TransactionType;
import com.zfinance.orm.userdefinedtypes.transaction.TransactionsTransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_transaction")
public class Transaction {

	@Id
	@Field("id")
	private String id;

	@Field("net_amount")
	private Double netAmount;

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

	@Transient
	public static final String SEQUENCE_NAME = "transaction_sequence";

}
