package com.zfinance.services.external;

import com.zfinance.dto.response.user.UserRecord;

public interface AuthManagerService {

	public UserRecord getUserIdFromToken(String token);

}
