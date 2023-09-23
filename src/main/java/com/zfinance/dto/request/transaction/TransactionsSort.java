package com.zfinance.dto.request.transaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransactionsSort {
	private String createdAt;
	private String status;
	private String type;
}
