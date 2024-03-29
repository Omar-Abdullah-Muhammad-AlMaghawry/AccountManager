package com.zfinance.parser;

import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Component;

import com.zfinance.dto.request.payment.PaymentRecord;

@Component
public class XmlParser {
	public List<PaymentRecord> parse(InputStream inputStream) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(PaymentRecordsWrapper.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		PaymentRecordsWrapper entitiesWrapper = (PaymentRecordsWrapper) unmarshaller.unmarshal(inputStream);
		return entitiesWrapper.getPaymentRecords();
	}
}
