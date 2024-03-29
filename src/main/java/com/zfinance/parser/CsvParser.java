package com.zfinance.parser;

import java.util.ArrayList;
import java.util.List;

import com.zfinance.dto.request.payment.PaymentRecord;

public class CsvParser {
	public List<PaymentRecord> parseCsvRows(List<String[]> rows) {
        List<PaymentRecord> paymentRecords = new ArrayList<>();
        for (String[] row : rows) {
            PaymentRecord paymentRecord = new PaymentRecord();
            paymentRecord.setId(row[0]);
            // add the rest of the rows
            paymentRecords.add(paymentRecord);
        }
        return paymentRecords;
    }
}
