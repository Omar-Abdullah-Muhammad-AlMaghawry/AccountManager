package com.zfinance.orm.account;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.zfinance.orm.userdefinedtypes.account.BankAccountDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_bank_account")
public class BankAccount {

	@Id
	@Field("id")
	private String id;

	@Field("created_at")
	private String createdAt;

	@Field("details")
	private BankAccountDetails details;

	@Field("status")
	private String status;

	@Field("updated_at")
	private String updatedAt;

	@Field("user_id")
	private String userId;

	@Transient
	public static final String SEQUENCE_NAME = "bank_account_sequence";
}
