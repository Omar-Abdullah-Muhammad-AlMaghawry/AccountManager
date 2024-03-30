package com.zfinance.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.zfinance.dto.response.payment.PaymentRecord;
import com.zfinance.orm.payment.Payment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMapper {

	PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

	public PaymentRecord mapPayment(Payment payment);

	public Payment mapPaymentRecord(PaymentRecord paymentRecord);

	public default Page<PaymentRecord> mapPayments(Page<Payment> payments) {
		return payments.map(this::mapPayment);
	}

	public List<PaymentRecord> mapPayments(List<Payment> payments);

	public List<Payment> mapPaymentRecords(List<PaymentRecord> paymentRecords);

}
