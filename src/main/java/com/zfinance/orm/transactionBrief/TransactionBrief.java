package com.zfinance.orm.transactionBrief;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_transaction_brief")
public class TransactionBrief {
	
	@Id
	@Field("id")
	private String id;
	
	@Field("from_user_id")
	private String fromUserId;
	
	@Field("to_user_id")
	private String toUserId;
	
	@Field("date")
	private Date date;
	
	@Field("balance_from")
	private Double balanceFrom;
	
	@Field("balance_to")
	private Double balanceTo;
	
	@Field("type")
	private String type;
	
	@Field("amount")
	private Double amount;
	
	@Field("transaction_id")
	private String TransactionId;

}
