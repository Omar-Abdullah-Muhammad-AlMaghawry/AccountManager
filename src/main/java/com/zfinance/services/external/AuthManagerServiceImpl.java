package com.zfinance.services.external;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.zfinance.config.filters.TokenAuthorizationFilter;
import com.zfinance.dto.response.user.UserRecord;

@Service
public class AuthManagerServiceImpl implements AuthManagerService {

	@Value("${authmanager.url}")
	private String AUTH_MANAGER_URL;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TokenAuthorizationFilter tokenAuthorizationFilter;

	@Override
	public UserRecord getUserIdFromToken(String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", tokenAuthorizationFilter.getToken());
		HttpEntity<Void> entity = new HttpEntity<>(null, headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(AUTH_MANAGER_URL + "/auth/getUserIdFromToken")
				.queryParam("token", token);

		ResponseEntity<UserRecord> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				UserRecord.class);
		return response.getBody();

	}

}
