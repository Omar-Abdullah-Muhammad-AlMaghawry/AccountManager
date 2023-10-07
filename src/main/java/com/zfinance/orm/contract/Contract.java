package com.zfinance.orm.contract;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document("zfin_contract")
public class Contract {

	@Id
	@Field("id")
	private String id;

	@Field("organization_type")
	private String organizationType;

	@Field("person_type")
	private String personType;

	@Field("name")
	private String name;

	@Field("description")
	private String description;

	@Field("active")
	private Boolean active;

	@Field("global")
	private Boolean global;

	@Transient
	public static final String SEQUENCE_NAME = "contract_sequence";

}
