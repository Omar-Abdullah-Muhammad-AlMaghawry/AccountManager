package com.zfinance.orm.userdefinedtypes.coin;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: maybe table also

@Setter
@Getter
@NoArgsConstructor
@UserDefinedType("contract_info_type")
public class ContractInfo {

	@Column("id")
	private String id;

	@Column("person_type")
	private String personType;

	@Column("name")
	private String name;

}
