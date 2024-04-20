package com.zfinance.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.payment.PaymentRecord;
import com.zfinance.orm.payment.Payment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

	PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    @Mapping(target = "date", source = "date", dateFormat = "yyyy-MM-dd'T'HH:mm:ss")
	public PaymentRecord mapPayment(Payment payment);

    default String mapDate(LocalDate date) {
        return date != null ? date.toString() : null;
    }

    default LocalDate mapDate(String date) {
        return date != null ? LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")) : null;
    }
    
	public Payment mapPaymentRecord(PaymentRecord paymentRecord);

	public default Page<PaymentRecord> mapPayments(Page<Payment> payments) {
		return payments.map(this::mapPayment);
	}

	public List<PaymentRecord> mapPayments(List<Payment> payments);

	public List<Payment> mapPaymentRecords(List<PaymentRecord> paymentRecords);

}
