package com.zfinance.services.payment;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.extenrnal.UsersFilter;
import com.zfinance.dto.request.payment.PaymentFilter;
import com.zfinance.dto.request.payment.PaymentSort;
import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.enums.PaymentStatusEnum;
import com.zfinance.exceptions.BusinessException;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.payment.Payment;
import com.zfinance.repositories.payment.PaymentRepository;
import com.zfinance.services.external.CurrencyService;
import com.zfinance.services.external.UserService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private UserService userService;
	
	@Autowired
	private CurrencyService currencyService;

	@Override
	public List<Payment> searchPayments(PaymentFilter paymentFilter, PaymentSort paymentSort) {
		Criteria criteria = new Criteria();
		if (paymentFilter != null) {
			if (paymentFilter.getId() != null) {
				criteria.and("id").is(paymentFilter.getId());
			}
			if (paymentFilter.getPayeeId() != null) {
				criteria.and("payeeId").in(paymentFilter.getPayeeId());
			}
			if (paymentFilter.getPaymentId() != null) {
				criteria.and("paymentId").is(paymentFilter.getPaymentId());
			}
			if (paymentFilter.getPayoneerPaymentId() != null) {
				criteria.and("payoneerPaymentId").is(paymentFilter.getPayoneerPaymentId());
			}
			if (paymentFilter.getPayeeName() != null) {
				criteria.and("payeeName").in(paymentFilter.getPayeeName());
			}
			if (paymentFilter.getCompanyName() != null) {
				criteria.and("companyName").in(paymentFilter.getCompanyName());
			}
			if (paymentFilter.getDateFrom() != null) {
				// handle date
			}
			if (paymentFilter.getDateTo() != null) {
				// handle date
			}
			if (paymentFilter.getAmountFrom() != null) {
				criteria.and("amount").gte(paymentFilter.getAmountFrom());
			}
			if (paymentFilter.getAmountTo() != null) {
				criteria.and("amount").lte(paymentFilter.getAmountTo());
			}
			if (paymentFilter.getStatus() != null) {
				criteria.and("status").in(paymentFilter.getStatus());
			}
			if (paymentFilter.getCurrencyCode() != null) {
				criteria.and("currencyCode").in(paymentFilter.getCurrencyCode());
			}
			if (paymentFilter.getGroupId() != null) {
				criteria.and("groupId").is(paymentFilter.getGroupId());
			}
			if (paymentFilter.getDescription() != null) {
				criteria.and("description").is(paymentFilter.getDescription());
			}
			if (paymentFilter.getPayoutMethod() != null) {
				criteria.and("payoutMethod").in(paymentFilter.getPayoutMethod());
			}
		}

		Query query = new Query(criteria);
		if (paymentSort != null) {
			if (paymentSort.getId() != null) {
				if (paymentSort.getId().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("id")));
				} else {
					query.with(Sort.by(Sort.Order.desc("id")));
				}
			}
			if (paymentSort.getPayeeId() != null) {
				if (paymentSort.getPayeeId().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("payeeId")));
				} else {
					query.with(Sort.by(Sort.Order.desc("PayeeId")));
				}
			}
			if (paymentSort.getPaymentId() != null) {
				if (paymentSort.getPaymentId().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("paymentId")));
				} else {
					query.with(Sort.by(Sort.Order.desc("paymentId")));
				}
			}
			if (paymentSort.getPayoneerPaymentId() != null) {
				if (paymentSort.getPayoneerPaymentId().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("payoneerPaymentId")));
				} else {
					query.with(Sort.by(Sort.Order.desc("payoneerPaymentId")));
				}
			}
			if (paymentSort.getPayeeName() != null) {
				if (paymentSort.getPayeeName().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("payeeName")));
				} else {
					query.with(Sort.by(Sort.Order.desc("payeeName")));
				}
			}
			if (paymentSort.getCompanyName() != null) {
				if (paymentSort.getCompanyName().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("companyName")));
				} else {
					query.with(Sort.by(Sort.Order.desc("companyName")));
				}
			}
			if (paymentSort.getDate() != null) {
				if (paymentSort.getDate().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("date")));
				} else {
					query.with(Sort.by(Sort.Order.desc("date")));
				}
			}
			if (paymentSort.getAmount() != null) {
				if (paymentSort.getAmount().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("amount")));
				} else {
					query.with(Sort.by(Sort.Order.desc("amount")));
				}
			}
			if (paymentSort.getStatus() != null) {
				if (paymentSort.getStatus().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("status")));
				} else {
					query.with(Sort.by(Sort.Order.desc("status")));
				}
			}
			if (paymentSort.getCurrencyCode() != null) {
				if (paymentSort.getCurrencyCode().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("currencyCode")));
				} else {
					query.with(Sort.by(Sort.Order.desc("cuurencyCode")));
				}
			}
			if (paymentSort.getGroupId() != null) {
				if (paymentSort.getGroupId().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("groupId")));
				} else {
					query.with(Sort.by(Sort.Order.desc("groupId")));
				}
			}
			if (paymentSort.getDescription() != null) {
				if (paymentSort.getDescription().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("description")));
				} else {
					query.with(Sort.by(Sort.Order.desc("description")));
				}
			}
			if (paymentSort.getPayoutMethod() != null) {
				if (paymentSort.getPayoutMethod().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("payoutMethod")));
				} else {
					query.with(Sort.by(Sort.Order.desc("payoutMethoud")));
				}
			}
		}

		return mongoTemplate.find(query, Payment.class);
	}
	
	private void validate(Payment payment) {
		if (payment.getPayeeId() == null || payment.getPayeeName() == null) {
			throw new BusinessException("error_payeeDoesNotExist");
		}
		UsersFilter usersFilter = new UsersFilter();
		List<String> id = new ArrayList<>();
		id.add(payment.getId());
		usersFilter.setIds(id);

		// TODO: CHECK W/ OSAMA
		usersFilter.setEmail(payment.getPayeeName());

		List<UserRecord> user = userService.searchUsers(usersFilter);
		if (user == null || user.size() == 0) {
			throw new DataNotFoundException("error_userDoesNotExist");
		}

		if (payment.getPaymentId() == null || payment.getDate() == null || payment.getAmount() == null) {
			throw new BusinessException("error_DataNotComplete");
		}
		
		if (paymentRepository.existsByPaymentId(payment.getPaymentId())) {
			throw new BusinessException("error_PaymentIdAlreadyExists");
		}
	}

	@Override
	public Payment savePayment(Payment payment) {
		validate(payment);
		
		payment.setStatus(PaymentStatusEnum.PENDING.getCode());
		return paymentRepository.save(payment);
	}

	@Override
	public List<Payment> savePayments(List<Payment> payments) {
		List<Payment> result = new ArrayList<>();
		for (Payment payment : payments) {
			validate(payment);
			payment.setStatus(PaymentStatusEnum.PENDING.getCode());
			result.add(paymentRepository.save(payment));
		}
		return result;
	}

	@Override
	public Payment cancelPayment(String paymentId) {
		Payment payment = paymentRepository.findByPaymentId(paymentId);
		if (payment == null) {
			throw new DataNotFoundException("error_paymentNotFound");
		}
		if (payment.getStatus() != PaymentStatusEnum.PENDING.getCode()) {
			throw new BusinessException("error_paymentNotPending");
		}
		payment.setStatus(PaymentStatusEnum.CANCELLED.getCode());
		return paymentRepository.save(payment);
	}

	@Override
	public List<Payment> getPayments() {
		return paymentRepository.findAll();
	}

	@Override
	public Payment getPaymentByPaymentId(String paymentId) {
		return paymentRepository.findByPaymentId(paymentId);
	}

}
