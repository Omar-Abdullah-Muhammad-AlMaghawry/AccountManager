package com.zfinance.services.report;

import com.zfinance.dto.response.report.ReportsCoinDetailsRecord;

public interface ReportService {

	public ReportsCoinDetailsRecord getCoinDetails(String currency);
}
