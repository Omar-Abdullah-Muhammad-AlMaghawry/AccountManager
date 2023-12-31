package com.zfinance.services.report;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfinance.dto.response.report.ReportsCoinDetails;
import com.zfinance.dto.response.report.ReportsCoinDetailsCoinEntry;
import com.zfinance.dto.response.report.ReportsCoinDetailsRecord;
import com.zfinance.orm.coin.Wallet;
import com.zfinance.services.coin.WalletService;

@Service
public class ReportServiceImpl implements ReportService {

	@Autowired
	private WalletService walletService;

	@Override
	public ReportsCoinDetailsRecord getCoinDetails(String currency) {
		ReportsCoinDetailsRecord reportsCoinDetailsRecord = new ReportsCoinDetailsRecord();
		Double totalAmount = 0.0;
		List<Wallet> wallets = walletService.getSignedInWalletsByCurrency(currency);
		List<ReportsCoinDetails> reportsCoinDetails = new ArrayList<ReportsCoinDetails>();
		for (Wallet wallet : wallets) {
			totalAmount += wallet.getAvailableAmount();
			reportsCoinDetailsRecord.setSymbol(wallet.getIssuer() != null ? wallet.getIssuer().getSymbol() : null);

		}
		for (Wallet wallet : wallets) {
			ReportsCoinDetails reportsCoinDetail = new ReportsCoinDetails();
			reportsCoinDetail.setAmount(wallet.getAvailableAmount());
			reportsCoinDetail.setCurrency(currency);
			reportsCoinDetail.setPercentFromTotal(wallet.getAvailableAmount() != null ? ((wallet.getAvailableAmount()
					/ totalAmount) * 100) : 0.0);
			reportsCoinDetail.setSymbol(reportsCoinDetailsRecord.getSymbol());
			ReportsCoinDetailsCoinEntry reportsCoinDetailsCoinEntry = new ReportsCoinDetailsCoinEntry();
			reportsCoinDetailsCoinEntry.setCurrency(currency);
			reportsCoinDetailsCoinEntry.setSymbol(reportsCoinDetailsRecord.getSymbol());
			reportsCoinDetailsCoinEntry.setIssuerId(wallet.getIssuer().getId());
			reportsCoinDetailsCoinEntry.setAmount(wallet.getAvailableAmount());
			reportsCoinDetail.setReportsCoinDetailsCoinEntry(reportsCoinDetailsCoinEntry);
			reportsCoinDetails.add(reportsCoinDetail);
		}

		reportsCoinDetailsRecord.setCoinsDetail(reportsCoinDetails);
		reportsCoinDetailsRecord.setTotalAmount(totalAmount);
		reportsCoinDetailsRecord.setRoundedTotalAmount(totalAmount);
		reportsCoinDetailsRecord.setCurrency(currency);
		return reportsCoinDetailsRecord;
	}

}
