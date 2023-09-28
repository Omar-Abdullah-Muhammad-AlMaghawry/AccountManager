package com.zfinance.orm.userdefinedtypes.coin;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//TODO: maybe table also

@Setter
@Getter
@NoArgsConstructor
public class ContractInfo {

	@Field("id")
	private String id;

	@Field("person_type")
	private String personType;

	@Field("name")
	private String name;

}
