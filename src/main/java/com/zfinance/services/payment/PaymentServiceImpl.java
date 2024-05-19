package com.zfinance.services.payment;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.dto.request.PaginationRequestOptions;
import com.zfinance.dto.request.extenrnal.UsersFilter;
import com.zfinance.dto.request.payment.PaymentFilter;
import com.zfinance.dto.request.payment.PaymentSort;
import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.enums.PaymentStatusEnum;
import com.zfinance.exceptions.BusinessException;
import com.zfinance.exceptions.DataNotFoundException;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.payment.Payment;
import com.zfinance.repositories.payment.PaymentRepository;
import com.zfinance.services.coin.WalletService;
import com.zfinance.services.database.sequence.SequenceGeneratorService;
import com.zfinance.services.external.CurrencyService;
import com.zfinance.services.external.UserService;
import com.zfinance.services.transaction.TransactionService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;

	@Autowired
	private UserService userService;

	@Autowired
	private CurrencyService currencyService;
	
	@Autowired
	private WalletService walletService;
	
	@Autowired
	private TransactionService transactionService;

	@Override
	public List<Payment> searchPayments(PaginationRequestOptions<PaymentFilter, PaymentSort> options) {
		PaymentFilter paymentFilter = options.getFilter();
		PaymentSort paymentSort = options.getSort();
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
			if (paymentFilter.getDateFrom() != null && paymentFilter.getDateTo() != null) {
				criteria.and("date").gte(paymentFilter.getDateFrom()).lte(paymentFilter.getDateTo());
	        } else if (paymentFilter.getDateFrom() != null) {
	            criteria = criteria.and("date").gte(paymentFilter.getDateFrom());
	        } else if (paymentFilter.getDateTo() != null) {
	            criteria = criteria.and("date").lte(paymentFilter.getDateTo());
	        }
			if (paymentFilter.getAmountFrom() != null || paymentFilter.getAmountTo() != null) {
				criteria.and("amount").gte((paymentFilter.getAmountFrom() == null ? Double.MIN_VALUE : paymentFilter.getAmountFrom())).lte(paymentFilter.getAmountTo() == null ? Double.MAX_VALUE : paymentFilter.getAmountTo());
			}
			if (paymentFilter.getStatus() != null) {
				criteria.and("status").in(paymentFilter.getStatus());
			}
			if (paymentFilter.getCurrencyCodes() != null) {
				criteria.and("currencyCode").in(paymentFilter.getCurrencyCodes());
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
			if (paymentFilter.getPartnerId() != null) {
				criteria.and("partnerId").in(paymentFilter.getPartnerId());
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
			if (paymentSort.getPartnerId() != null) {
				if (paymentSort.getPartnerId().equalsIgnoreCase("asc")) {
					query.with(Sort.by(Sort.Order.asc("partnerId")));
				} else {
					query.with(Sort.by(Sort.Order.desc("partnerId")));
				}
			}
		}

		
		int page = (null != options.getPageNumber()) ? Integer.parseInt(options.getPageNumber()) : 0;
        int size = (null != options.getPageSize()) ? Integer.parseInt(options.getPageSize()) : 0;

        if (page != 0 && size != 0) {
            Pageable pageable = Pageable.ofSize(size).withPage(page);
            return mongoTemplate.find(query.with(pageable), Payment.class);
        }
        return mongoTemplate.find(query, Payment.class);
	}
	
	private void validate(Payment payment) {
		
		

	// TODO: PAYMENT_ID IS UNIQUE .. to be generative
	//TODO: Download an example of excel sheet to fill it

		if (payment.getPayeeId() == null) {
			throw new BusinessException("error_payeeDoesNotExist");
		}
		if (payment.getPayeeId().equals(payment.getMerchantId())) {
			throw new BusinessException("error_senderAndReciverAreTheSame");
		}
		UsersFilter usersFilter = new UsersFilter();
		List<String> id = new ArrayList<>();
		id.add(payment.getPayeeId());
		usersFilter.setIds(id);


		// TODO: CHECK W/ OSAMA use email until now .. after a while we will use username at regestration to be unique
		usersFilter.setEmail(payment.getPayeeName());

		List<UserRecord> user = userService.searchUsers(usersFilter);
		if (user == null || user.size() == 0) {
			throw new DataNotFoundException("error_userDoesNotExist");
		}
		
		List<Wallet> payeeWallet = walletService.searchWalletsByUserAndCurrency(payment.getPayeeId(), payment.getCurrencyCode());
		if (payeeWallet == null || payeeWallet.size() == 0) {
			throw new BusinessException("error_payeeWalletDoesNotExist");
		}
		List<Wallet> payerWallet = walletService.searchWalletsByUserAndCurrency(payment.getMerchantId(), payment.getCurrencyCode());
		if (payerWallet == null || payerWallet.size() == 0) {
			throw new BusinessException("error_payerWalletDoesNotExist");
		}
		
		if (payerWallet.get(0).getAmount() < payment.getAmount()) {
			throw new BusinessException("error_notEnoughBalance");
		}

		if (payment.getPaymentId() == null || payment.getAmount() == null || payment.getDate() == null) {
			throw new BusinessException("error_DataNotComplete");
		}
		
		if (paymentRepository.existsByPaymentId(payment.getPaymentId())) {
			throw new BusinessException("error_PaymentIdAlreadyExists");
		}
	}
	
	private void applyPayment(Payment payment) {
		List<Wallet> payeeWallet = walletService.searchWalletsByUserAndCurrency(payment.getPayeeId(), payment.getCurrencyCode());
		List<Wallet> payerWallet = walletService.searchWalletsByUserAndCurrency(payment.getMerchantId(), payment.getCurrencyCode());

		double payerBalance = payerWallet.get(0).getAmount();
		double payeeBalance = payeeWallet.get(0).getAmount();
		payerWallet.get(0).setAmount(payerBalance - payment.getAmount());
		payeeWallet.get(0).setAmount(payeeBalance + payment.getAmount());
	}

	@Override
	public Payment savePayment(Payment payment) {
		validate(payment);
		applyPayment(payment);
		
		payment.setStatus(PaymentStatusEnum.PENDING.getCode());
		
		transactionService.createTransaction(payment);
		
		if (payment.getId() == null) {
			payment.setId(sequenceGeneratorService.generateSequence(Payment.SEQUENCE_NAME));
		}
		return paymentRepository.save(payment);
	}

	@Override
	public List<Payment> savePayments(List<Payment> payments) {
		List<Payment> result = new ArrayList<>();
		for (Payment payment : payments) {
			validate(payment);
			applyPayment(payment);
			payment.setStatus(PaymentStatusEnum.PENDING.getCode());
			
			transactionService.createTransaction(payment);
			
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
		if (!payment.getStatus().equals(PaymentStatusEnum.PENDING.getCode())) {
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
