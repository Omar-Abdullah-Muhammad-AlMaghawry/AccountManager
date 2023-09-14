package com.zfinance.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.exceptions.BusinessException;

@Service
public class SecurityServiceImpl implements SecurityService {

	@Value("${security.usermenuaction.module}")
	private Long module;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${integration.rst.auth.validateToken}")
	private String validateTokenUrl;

	@Override
	public UserRecord validateToken(String token) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", token);
			HttpEntity<Void> entity = new HttpEntity<>(null, headers);

			ResponseEntity<UserRecord> responseEntity = restTemplate.exchange(validateTokenUrl, HttpMethod.POST, entity,
					UserRecord.class);
			return responseEntity.getBody();
		} catch (HttpStatusCodeException ex) {
			if (ex.getStatusCode().equals(HttpStatus.UNAUTHORIZED)) {
				throw ex;
			} else {
				ex.printStackTrace();
				throw new BusinessException("error_authServiceConnectFailed");
			}
		}
	}

}
