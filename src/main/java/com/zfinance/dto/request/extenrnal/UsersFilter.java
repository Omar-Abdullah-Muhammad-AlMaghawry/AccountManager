package com.zfinance.dto.request.extenrnal;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UsersFilter {
	
	private List<String> ids;
	private String email;
	private Boolean emailVerified;
	private String phone;
	private Boolean phoneVerified;
	private String text;
	private Boolean banned;
	private Boolean active;
	private List<String> roles;
	private String identificationStatus;
	private List<String> organizationIds;
	private String iban;
	private String tid;
	private String virtualAccountNumber;
	private String personalId;
}
