package com.zfinance.parser;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.zfinance.dto.response.payment.PaymentRecord;

@XmlRootElement(name = "paymentRecords")
public class PaymentRecordsWrapper {

	private List<PaymentRecord> paymentRecords;

	@XmlElement(name = "paymentRecord")
	public List<PaymentRecord> getPaymentRecords() {
		return paymentRecords;
	}

	public void setPaymentRecords(List<PaymentRecord> paymentRecords) {
		this.paymentRecords = paymentRecords;
	}
}
