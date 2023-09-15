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
	private Integer pageNumber;
	private Integer pageSize;
	private Integer totalRecords;
	private Integer totalPages;
	private List<R> records;
}
