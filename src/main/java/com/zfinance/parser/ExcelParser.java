package com.zfinance.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.zfinance.dto.request.payment.PaymentRecord;

public class ExcelParser {

	public List<PaymentRecord> parse(InputStream inputStream) throws IOException, EncryptedDocumentException{
        try (Workbook workbook = WorkbookFactory.create(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            List<PaymentRecord> paymentRecords = new ArrayList<>();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                PaymentRecord paymentRecord = new PaymentRecord();
                // Assuming the Excel structure matches the fields of PaymentRecord
                paymentRecord.setId(row.getCell(0).getStringCellValue());
                // Add more fields as needed
                paymentRecords.add(paymentRecord);
            }
            
            return paymentRecords;
        }
	
	}
}
