package com.zfinance.dto.response.payment;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@XmlRootElement(name = "paymentRecord")
public class PaymentRecord {

	@XmlElement
	private String id;

	@XmlElement
	private String payeeId;

	@XmlElement
	private String merchantId;

	@XmlElement
	private String paymentId;

	@XmlElement
	private String payoneerPaymentId;

	@XmlElement
	private String payeeName;

	@XmlElement
	private String companyName;

	@XmlElement
	private String date;

	@XmlElement
	private Double amount;

	@XmlElement
	private String status;

	@XmlElement
	private String description;

	@XmlElement
	private String groupId;

	@XmlElement
	private String payoutMethod;

	@XmlElement
	private String currencyCode;

	@XmlElement
	private String partnerId;

	@XmlElement
	private Boolean isIntegration;
}
