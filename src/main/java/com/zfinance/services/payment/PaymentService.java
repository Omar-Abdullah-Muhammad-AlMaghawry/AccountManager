package com.zfinance.services.payment;

import java.util.List;

import com.zfinance.dto.request.payment.PaymentFilter;
import com.zfinance.dto.request.payment.PaymentSort;
import com.zfinance.orm.payment.Payment;

public interface PaymentService {

	public List<Payment> searchPayments(PaymentFilter paymentFilter, PaymentSort paymentSort);

	public Payment savePayment(Payment payment);

	public List<Payment> savePayments(List<Payment> payments);

	public Payment cancelPayment(String paymentId);

	public List<Payment> getPayments();

	public Payment getPaymentById(String id);

}
