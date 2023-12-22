package com.zfinance.dto.request.transfer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransfersSort {
	private String createdAt;
	private String status;
	private String type;
}
