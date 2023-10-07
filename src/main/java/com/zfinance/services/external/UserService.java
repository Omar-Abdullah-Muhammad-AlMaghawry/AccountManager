package com.zfinance.services.external;

import java.util.List;

import com.zfinance.dto.request.extenrnal.UsersFilter;
import com.zfinance.dto.response.user.UserRecord;

public interface UserService {

	public List<UserRecord> searchUsers(UsersFilter userRecord);

	public UserRecord saveUser(UserRecord userRecord);

}
