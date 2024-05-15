package com.zfinance.services.transactionBrief;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.zfinance.config.filters.TokenAuthorizationFilter;
import com.zfinance.dto.response.transactionBrief.FundingDto;
import com.zfinance.dto.response.transactionBrief.PayoutDto;
import com.zfinance.dto.response.transactionBrief.RunningBalanceDto;
import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.orm.transaction.Transaction;
import com.zfinance.orm.transactionBrief.TransactionBrief;
import com.zfinance.repositories.transactionBrief.TransactionBriefRepository;
import com.zfinance.services.coin.WalletService;
import com.zfinance.services.external.AuthManagerService;

@Service
public class TransactionBriefServiceImpl implements TransactionBriefService {

	@Autowired
	private WalletService walletService;

	@Autowired
	private TransactionBriefRepository transactionBriefRepository;

	@Autowired
	private AuthManagerService authManagerService;

	@Autowired
	private TokenAuthorizationFilter tokenAuthorizationFilter;

	@Autowired
	private MongoTemplate mongoTemplate;

	public TransactionBrief createTransactionBrief(Transaction transaction) {
		TransactionBrief transactionBrief = new TransactionBrief();

		Wallet payee = walletService.getWalletBySerial(transaction.getFrom().getSerial()).get(0);
		Wallet payer = walletService.getWalletBySerial(transaction.getTo().getSerial()).get(0);

		// ToDo: check if more details needed
		transactionBrief.setFromUserId(payer.getUserId());
		transactionBrief.setToUserId(payee.getUserId());
		transactionBrief.setBalanceFrom(payer.getAmount());
		transactionBrief.setBalanceTo(payee.getAmount());

		transactionBrief.setTransactionId(transaction.getId());
		transactionBrief.setAmount(transaction.getAmount());
		transactionBrief.setType(transaction.getType());

		return transactionBriefRepository.save(transactionBrief);
	}

	public List<TransactionBrief> getTransactionBriefsInLast30Days(String userId) {
		Query query = new Query();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		query.addCriteria(Criteria.where("date").gte(cal.getTime()).and(userId));

		return mongoTemplate.find(query, TransactionBrief.class);
	}

	@Override
	public List<FundingDto> getFundings(String userId) {
		Query query = new Query();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		query.addCriteria(Criteria.where("date").gte(cal.getTime()).and("from_user_id").is(userId));
		List<TransactionBrief> fundingTransactionBriefs = mongoTemplate.find(query, TransactionBrief.class);

		List<FundingDto> fundings = new ArrayList<>();
		for (TransactionBrief transactionBrief : fundingTransactionBriefs) {
			fundings.add(new FundingDto(transactionBrief.getFromUserId(), transactionBrief.getAmount()));
		}

		return fundings;
	}

	@Override
	public List<PayoutDto> getPayouts(String userId) {
		Query query = new Query();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		query.addCriteria(Criteria.where("date").gte(cal.getTime()).and("to_user_id").is(userId));
		List<TransactionBrief> payoutTransactionBriefs = mongoTemplate.find(query, TransactionBrief.class);

		List<PayoutDto> payouts = new ArrayList<>();
		for (TransactionBrief transactionBrief : payoutTransactionBriefs) {
			payouts.add(new PayoutDto(transactionBrief.getToUserId(), transactionBrief.getAmount()));
		}

		return payouts;
	}

	@Override
	public List<RunningBalanceDto> getRunningBalance(String userId) {
		Query query1 = new Query();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -30);
		query1.addCriteria(Criteria.where("date").gte(cal.getTime()).and("from_user_id").is(userId));
		List<TransactionBrief> fundingTransactionBriefs = mongoTemplate.find(query1, TransactionBrief.class);

		Query query2 = new Query();
		query2.addCriteria(Criteria.where("date").gte(cal.getTime()).and("to_user_id").is(userId));
		List<TransactionBrief> payoutTransactionBriefs = mongoTemplate.find(query2, TransactionBrief.class);

		List<RunningBalanceDto> runningBalance = new ArrayList<>();
		for (TransactionBrief transactionBrief : fundingTransactionBriefs) {
			runningBalance.add(new RunningBalanceDto(transactionBrief.getDate(), transactionBrief.getBalanceFrom()));
		}
		for (TransactionBrief transactionBrief : payoutTransactionBriefs) {
			runningBalance.add(new RunningBalanceDto(transactionBrief.getDate(), transactionBrief.getBalanceTo()));
		}

		return runningBalance;
	}

	@Override
	public List<RunningBalanceDto> getSignedInRunningBalance() {
		String token = tokenAuthorizationFilter.getToken();
		UserRecord user = authManagerService.getUserFromToken(token);
		return getRunningBalance(user.getId());
	}

}
