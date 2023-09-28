package com.zfinance.orm.userdefinedtypes.smartcards;

import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.enums.ExpirationStatusEnum;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SmartCardShort {

	@Field("id")
	private String id;

	@Field("name")
	private String name;

	@Field("active")
	private Boolean active;

	@Field("card_number")
	private String cardNumber;

	@Field("expiration_date")
	private String expirationDate;

	@Field("expiration_status")
	private ExpirationStatusEnum expirationStatus;

}
