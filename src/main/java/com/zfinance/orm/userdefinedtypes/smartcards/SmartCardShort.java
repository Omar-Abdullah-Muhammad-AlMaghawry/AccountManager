package com.zfinance.orm.userdefinedtypes.smartcards;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import com.zfinance.enums.ExpirationStatusEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@UserDefinedType("smart_card_short_type")
public class SmartCardShort {

	private String id;
	private String name;
	private Boolean active;
	private String cardNumber;
	private String expirationDate;
	private ExpirationStatusEnum expirationStatus;

}
