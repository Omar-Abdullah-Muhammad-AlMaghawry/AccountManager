package com.zfinance.controller.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zfinance.dto.response.report.ReportsCoinDetailsRecord;
import com.zfinance.services.report.ReportService;

@RestController
@RequestMapping("/reporting/coins")
@CrossOrigin("*")
public class ReportController {

	@Autowired
	private ReportService reportService;

	@GetMapping
	public ReportsCoinDetailsRecord getCoinDetails(@RequestParam String currency) {
		return reportService.getCoinDetails(currency);

	}

}
