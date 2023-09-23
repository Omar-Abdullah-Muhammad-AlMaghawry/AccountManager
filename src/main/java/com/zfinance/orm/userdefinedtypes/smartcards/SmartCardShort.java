package com.zfinance.orm.userdefinedtypes.smartcards;

import org.springframework.data.cassandra.core.mapping.Column;
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

	@Column("id")
	private String id;

	@Column("name")
	private String name;

	@Column("active")
	private Boolean active;

	@Column("card_number")
	private String cardNumber;

	@Column("expiration_date")
	private String expirationDate;

	@Column("expiration_status")
	private ExpirationStatusEnum expirationStatus;

}
