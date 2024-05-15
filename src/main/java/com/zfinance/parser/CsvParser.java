package com.zfinance.parser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.zfinance.dto.response.payment.PaymentRecord;

@Component
public class CsvParser {
	public List<PaymentRecord> parseCsvRows(List<String[]> rows) {
		List<PaymentRecord> paymentRecords = new ArrayList<>();
		for (String[] row : rows) {
			PaymentRecord paymentRecord = new PaymentRecord();
			paymentRecord.setId(row[0]);
            paymentRecord.setPayeeId(row[1]);
            paymentRecord.setPaymentId(row[2]);
            paymentRecord.setPayoneerPaymentId(row[3]);
            paymentRecord.setPayeeName(row[4]);
            paymentRecord.setCompanyName(row[5]);
            paymentRecord.setDate(row[6]);
            paymentRecord.setAmount(Double.parseDouble(row[7]));
            paymentRecord.setStatus(row[8]);
            paymentRecord.setDescription(row[9]);
            paymentRecord.setGroupId(row[10]);
            paymentRecord.setPayoutMethod(row[11]);
            paymentRecord.setCurrencyCode(row[12]);
            paymentRecords.add(paymentRecord);
		}
		return paymentRecords;
	}
}
