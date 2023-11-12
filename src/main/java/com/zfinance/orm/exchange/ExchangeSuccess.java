package com.zfinance.orm.exchange;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.exchanges.ExchangeSuccessChildren;
import com.zfinance.orm.userdefinedtypes.transaction.TransactionsTransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_exchange_success")
public class ExchangeSuccess {

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

	@Field("transactions")
	private List<TransactionsTransaction> transactions;

	@Field("children")
	private List<ExchangeSuccessChildren> children;

	@Field("error_mssage")
	private String errorMessage;

	@Transient
	public static final String SEQUENCE_NAME = "exchange_success_sequence";

}
