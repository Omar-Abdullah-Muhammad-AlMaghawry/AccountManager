package com.zfinance.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Component;

import com.zfinance.dto.response.payment.PaymentRecord;

@Component
public class ExcelParser {

	public List<PaymentRecord> parse(InputStream inputStream) throws IOException, EncryptedDocumentException {
		try (Workbook workbook = WorkbookFactory.create(inputStream)) {
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rowIterator = sheet.iterator();
			List<PaymentRecord> paymentRecords = new ArrayList<>();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				PaymentRecord paymentRecord = new PaymentRecord();
				
//                Cell idCell = row.getCell(0);
//                if (idCell != null) {
//                    paymentRecord.setId(idCell.getStringCellValue());
//                }

                Cell payeeIdCell = row.getCell(1);
                if (payeeIdCell != null) {
                    paymentRecord.setPayeeId(payeeIdCell.getStringCellValue());
                }

                Cell amountCell = row.getCell(2);
                if (amountCell != null) {
                    paymentRecord.setAmount(amountCell.getNumericCellValue());
                }
                
                Cell currencyCodeCell = row.getCell(3);
                if (currencyCodeCell != null) {
                    paymentRecord.setCurrencyCode(currencyCodeCell.getStringCellValue());
                }
                
                Cell paymentIdCell = row.getCell(4);
                if (paymentIdCell != null) {
                    paymentRecord.setPaymentId(paymentIdCell.getStringCellValue());
                }
                
                Cell descriptionCell = row.getCell(5);
                if (descriptionCell != null) {
                    paymentRecord.setDescription(descriptionCell.getStringCellValue());
                }
                
                Cell dateCell = row.getCell(6);
                if (dateCell != null) {
                    paymentRecord.setDate(dateCell.getStringCellValue());
                }
                
                Cell groupIdCell = row.getCell(7);
                if (groupIdCell != null) {
                    paymentRecord.setGroupId(groupIdCell.getStringCellValue());
                }

                Cell payoneerPaymentIdCell = row.getCell(8);
                if (payoneerPaymentIdCell != null) {
                    paymentRecord.setPayoneerPaymentId(payoneerPaymentIdCell.getStringCellValue());
                }

                Cell payeeNameCell = row.getCell(9);
                if (payeeNameCell != null) {
                    paymentRecord.setPayeeName(payeeNameCell.getStringCellValue());
                }

                Cell companyNameCell = row.getCell(10);
                if (companyNameCell != null) {
                    paymentRecord.setCompanyName(companyNameCell.getStringCellValue());
                }

                Cell payoutMethodCell = row.getCell(11);
                if (payoutMethodCell != null) {
                    paymentRecord.setPayoutMethod(payoutMethodCell.getStringCellValue());
                }

                paymentRecords.add(paymentRecord);
			}

			return paymentRecords;
		}

	}
}
