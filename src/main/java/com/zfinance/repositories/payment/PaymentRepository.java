package com.zfinance.repositories.payment;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.zfinance.orm.payment.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

	Payment findByPaymentId(String paymentId);

	boolean existsByPaymentId(String paymentId);


}
