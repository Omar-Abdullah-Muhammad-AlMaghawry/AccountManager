package com.zfinance.orm.payment;

import java.util.Date;

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
@Document("zfin_payment")
public class Payment {

	@Id
	@Field("id")
	private String id;

	@Field("payee_id")
	private String payeeId;

	@Field("merchant_id")
	private String merchantId;

	@Field("payment_id")
	private String paymentId;

	@Field("payoneer_payment_id")
	private String payoneerPaymentId;

	@Field("payee_name")
	private String payeeName;

	@Field("company_name")
	private String companyName;

	@Field("date")
	private Date date;

	@Field("amount")
	private Double amount;

	@Field("status")
	private String status;

	@Field("Description")
	private String description;

	@Field("group_id")
	private String groupId;

	@Field("payout_method")
	private String payoutMethod;

	@Field("currency_code")
	private String currencyCode;

	@Field("partner_id")
	private String partnerId;

	@Field("is_integration")
	private Boolean isIntegration;

	@Transient
	public static final String SEQUENCE_NAME = "payment_sequence";
}
