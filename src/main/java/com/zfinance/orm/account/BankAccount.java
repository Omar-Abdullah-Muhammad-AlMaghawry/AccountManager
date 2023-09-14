package com.zfinance.orm.account;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.zfinance.orm.userdefinedtypes.account.BankAccountDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table("zfin_bank_account")
public class BankAccount {

	@PrimaryKey
	@Column("id")
	private String id;

	@Column("created_at")
	private String createdAt;

	@Column("details")
	private BankAccountDetails details;

	@Column("status")
	private String status;

	@Column("updated_at")
	private String updatedAt;

	@Column("user_id")
	private String userId;
}
