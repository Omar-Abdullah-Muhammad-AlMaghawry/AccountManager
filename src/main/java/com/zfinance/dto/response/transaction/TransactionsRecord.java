package com.zfinance.dto.response.transaction;

import java.util.List;

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
public class TransactionsRecord {

	private Double netAmount;
	private List<TransactionType> children;
	private String createdAt;
	private String updatedAt;
	private String description;
	private String id;
	private Target to;
	private Target from;
	private ClientCoin clientCoin;
	private Integer requestIdentifier;
	private String requestStatus;
	private String status;
	private String posCompanyName;
	private String categoryImageLink;
	private String categoryName;
	private SenderRecipient sender;
	private List<TransactionsTransaction> transactions;
	private String type;
	private Double amount;
	private Double invoiceAmount;
	private Double cashAmount;
	private Double commission;
	private TransactionCoin coin;
	private Double incomingAmount;
	private Double outgoingAmount;
	private OperationAmountDetails operationAmountDetails;
	private SenderRecipient recipient;
	private String merchantCoinSerial;
	private String invoiceIdentifier;
}
