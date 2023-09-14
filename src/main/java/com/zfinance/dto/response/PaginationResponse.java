package com.zfinance.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaginationResponse<R> {

	private String status;
	private String message;
	private int pageNumber;
	private int pageSize;
	private int totalRecords;
	private int totalPages;
	private List<R> records;
}
