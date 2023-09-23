package com.zfinance.orm.userdefinedtypes.transaction;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@UserDefinedType("sender_recipient_type")
public class SenderRecipient {

	@Column("full_name")
	private String fullName;

	@Column("email")
	private String email;

	@Column("phone_number")
	private String phoneNumber;
}
