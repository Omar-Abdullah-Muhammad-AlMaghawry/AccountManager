package com.zfinance.services.transactionBrief;

import java.util.List;

import com.zfinance.dto.response.transactionBrief.FundingDto;
import com.zfinance.dto.response.transactionBrief.PayoutDto;
import com.zfinance.dto.response.transactionBrief.RunningBalanceDto;
import com.zfinance.orm.transaction.Transaction;
import com.zfinance.orm.transactionBrief.TransactionBrief;

public interface TransactionBriefService {
	
	public TransactionBrief createTransactionBrief(Transaction transaction);
	
	public List<TransactionBrief> getTransactionBriefsInLast30Days(String userId);
	
	public List<FundingDto> getFundings(String userId);
	
	public List<PayoutDto> getPayouts(String userId);

	public List<RunningBalanceDto> getRunningBalance(String userId);

}
