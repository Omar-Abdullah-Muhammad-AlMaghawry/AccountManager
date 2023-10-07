package com.zfinance.services.external;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.zfinance.config.filters.TokenAuthorizationFilter;
import com.zfinance.dto.request.extenrnal.UsersFilter;
import com.zfinance.dto.response.user.UserRecord;

@Service
public class UserServiceImpl implements UserService {

	@Value("${user.service.url}")
	private String USER_SERVICE_URL;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TokenAuthorizationFilter tokenAuthorizationFilter;

	@Override
	public List<UserRecord> searchUsers(UsersFilter usersFilter) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "TOKEN " + tokenAuthorizationFilter.getToken());

		// Create the request body and set the statusId
		HttpEntity<UsersFilter> requestEntity = new HttpEntity<>(usersFilter, headers);

		ParameterizedTypeReference<List<UserRecord>> responseType = new ParameterizedTypeReference<List<UserRecord>>() {
		};

		ResponseEntity<List<UserRecord>> response = restTemplate.exchange(USER_SERVICE_URL + "/users/searchUsers",
				HttpMethod.POST, requestEntity, responseType);
		return response.getBody();
	}

	@Override
	public UserRecord saveUser(UserRecord userRecord) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "TOKEN " + tokenAuthorizationFilter.getToken());

		// Create the request body and set the statusId
		HttpEntity<UserRecord> requestEntity = new HttpEntity<>(userRecord, headers);

		ResponseEntity<UserRecord> response = restTemplate.exchange(USER_SERVICE_URL + "/users/saveUser",
				HttpMethod.POST, requestEntity, UserRecord.class);
		return response.getBody();
	}

}
