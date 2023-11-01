package com.zfinance.dto.request.systemOperation;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class SystemOperationReqDTO implements Serializable {

	private String method;
	private String cashDeskId;
	private String serial;
	private Double amount;
	private String fullName;
}
