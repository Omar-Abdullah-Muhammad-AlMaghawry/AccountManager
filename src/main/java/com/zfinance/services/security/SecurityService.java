package com.zfinance.services.security;

import com.zfinance.dto.response.user.UserRecord;

public interface SecurityService {

	public UserRecord validateToken(String token);

}
