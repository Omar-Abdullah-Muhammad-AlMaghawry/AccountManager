package com.zfinance.orm.userdefinedtypes.user;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserContractInfo {

	@Field("id")
	private String id;

	@Field("person_type")
	private String personType;

	@Field("name")
	private String name;
}
