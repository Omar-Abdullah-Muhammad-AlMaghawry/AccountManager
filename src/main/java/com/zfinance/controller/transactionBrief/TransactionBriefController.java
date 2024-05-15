package com.zfinance.controller.transactionBrief;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.response.transactionBrief.FundingDto;
import com.zfinance.dto.response.transactionBrief.PayoutDto;
import com.zfinance.dto.response.transactionBrief.RunningBalanceDto;
import com.zfinance.services.transactionBrief.TransactionBriefService;

@RestController
@RequestMapping("/transactionsBrief")
@CrossOrigin("*")
public class TransactionBriefController {

	@Autowired
	private TransactionBriefService transactionBriefService;
	
	@GetMapping("/fundings/{userId}")
	public List<FundingDto> getFundings(@PathVariable String userId) {
		return transactionBriefService.getFundings(userId);
	}
	
	@GetMapping("/payouts/{userId}")
	public List<PayoutDto> getPayouts(@PathVariable String userId) {
		return transactionBriefService.getPayouts(userId);
	}
	
	@GetMapping("/runningBalance/{userId}")
	public List<RunningBalanceDto> getRunningBalance(@PathVariable String userId) {
		return transactionBriefService.getRunningBalance(userId);
	}
}
