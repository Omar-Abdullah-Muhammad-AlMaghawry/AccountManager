package com.zfinance.dto.request;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PaginationRequestOptions<F, S> {

	private String pageSize;
	private String pageNumber;
	private Boolean all;
	private F filter;
	private List<Map<String, Object>> records;
	private S sort;

}
