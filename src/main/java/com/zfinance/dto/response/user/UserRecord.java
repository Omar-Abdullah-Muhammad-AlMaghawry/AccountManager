package com.zfinance.dto.response.user;

import java.util.List;

import com.zfinance.orm.userdefinedtypes.user.UserContact;
import com.zfinance.orm.userdefinedtypes.user.UserMemberRecord;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class UserRecord {

	private String id;
	private String name;
	private String email;
	private String createdAt;
	private Boolean active;
	private Boolean banned;
	private String banExpiryDate;
	private UserContact contact;
	private List<UserMemberRecord> members;
}
