package com.zfinance.services.transactionBrief;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.ibm.icu.text.SimpleDateFormat;
import com.zfinance.config.filters.TokenAuthorizationFilter;
import com.zfinance.dto.request.extenrnal.UsersFilter;
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
import com.zfinance.services.external.UserService;

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

	@Autowired
	private UserService userService;

	public List<TransactionBrief> getAllTransactionBriefs() {
		List<TransactionBrief> res = transactionBriefRepository.findAll();
		return res;
	}

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

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			transactionBrief.setDate(formatter.parse(transaction.getCreatedAt()));
		} catch (ParseException e) {
			// Todo
		}

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
			UsersFilter usersFilter = new UsersFilter();
			List<String> id = new ArrayList<>();
			id.add(transactionBrief.getFromUserId());
			usersFilter.setIds(id);
			UserRecord user = userService.searchUsers(usersFilter).get(0);

			fundings.add(new FundingDto(user.getEmail(), transactionBrief.getAmount()));
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
			UsersFilter usersFilter = new UsersFilter();
			List<String> id = new ArrayList<>();
			id.add(transactionBrief.getFromUserId());
			usersFilter.setIds(id);
			UserRecord user = userService.searchUsers(usersFilter).get(0);

			payouts.add(new PayoutDto(user.getEmail(), transactionBrief.getAmount()));
		}

		return payouts;
	}

	@Override
	public List<RunningBalanceDto> getRunningBalance(String userId) {
		// Get the current date
		Date currentDate = new Date();

		// Create a Calendar instance and set it to the current date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		// Subtract 30 days from the calendar
		calendar.add(Calendar.DAY_OF_MONTH, -30);

		// Get the updated date
		Date oneMonthAgo = calendar.getTime();

		List<TransactionBrief> fundingTransactionBriefs = transactionBriefRepository.findAllByFromUserId(userId);
		List<TransactionBrief> payoutTransactionBriefs = transactionBriefRepository.findAllByToUserId(userId);

		List<RunningBalanceDto> runningBalance = new ArrayList<>();
		for (TransactionBrief transactionBrief : fundingTransactionBriefs) {
			Date date = transactionBrief.getDate();

			if (date.before(currentDate) && date.after(oneMonthAgo))
				runningBalance.add(new RunningBalanceDto(date, transactionBrief.getBalanceFrom()));

		}
		for (TransactionBrief transactionBrief : payoutTransactionBriefs) {
			Date date = transactionBrief.getDate();

			if (date != null && date.before(currentDate) && date.after(oneMonthAgo))
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
