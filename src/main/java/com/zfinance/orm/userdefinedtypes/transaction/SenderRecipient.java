package com.zfinance.orm.userdefinedtypes.transaction;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SenderRecipient {

	@Field("full_name")
	private String fullName;

	@Field("email")
	private String email;

	@Field("phone_number")
	private String phoneNumber;
}
