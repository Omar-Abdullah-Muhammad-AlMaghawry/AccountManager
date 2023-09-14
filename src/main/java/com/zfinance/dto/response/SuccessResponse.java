package com.zfinance.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SuccessResponse<R> {

	private R response;
	private Object error;
	private Object originalResponse;

}
