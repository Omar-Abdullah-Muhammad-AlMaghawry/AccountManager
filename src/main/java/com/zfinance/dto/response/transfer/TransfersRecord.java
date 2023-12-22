package com.zfinance.dto.response.transfer;

import java.util.List;

import com.zfinance.orm.userdefinedtypes.transfer.TransfersTransaction;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class TransfersRecord {
	private String id;
	private String createdAt;
	private String updatedAt;
	private String description;
	private Integer requestIdentifier;
	private String requestStatus;
	private String status;
	private List<TransfersTransaction> transactions;
	private List<Object> children;
	private String type;
}
