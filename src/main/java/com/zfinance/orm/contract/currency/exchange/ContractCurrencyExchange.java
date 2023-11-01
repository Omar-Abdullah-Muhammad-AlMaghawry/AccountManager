package com.zfinance.orm.contract.currency.exchange;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_contract_currency_exchange")
public class ContractCurrencyExchange {

	@Id
	@Field("id")
	private String id;

	@Field("type")
	private String type;

	@Field("percent")
	private Double percent;

	@Field("earned")
	private Double earned;

	@Field("median_earning")
	private Double medianEarning;

	@Field("median_transactions")
	private Double medianTransactions;

	@Field("start_date")
	private String startDate;

	@Field("end_date")
	private String endDate;

	@Field("status")
	private String status;

	@Transient
	public static final String SEQUENCE_NAME = "contract_currency_exchange_sequence";

}
