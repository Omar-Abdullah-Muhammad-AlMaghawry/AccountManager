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
import com.zfinance.orm.userdefinedtypes.exchangerates.CoinIssuer;

@Service
public class IssuerServiceImpl implements IssuerService {

	@Value("${services.url}")
	private String SERVICES_URL;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private TokenAuthorizationFilter tokenAuthorizationFilter;

	@Override
	public CoinIssuer getIssuerById(String id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "TOKEN " + tokenAuthorizationFilter.getToken());
		HttpEntity<Void> entity = new HttpEntity<>(null, headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SERVICES_URL + "/issuers/getIssuerById")
				.queryParam("id", id);

		ResponseEntity<CoinIssuer> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				CoinIssuer.class);
		return response.getBody();
	}

	@Override
	public CoinIssuer getIssuerByCurrencyCode(String code) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Authorization", "TOKEN " + tokenAuthorizationFilter.getToken());
		HttpEntity<Void> entity = new HttpEntity<>(null, headers);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SERVICES_URL
				+ "/issuers/getIssuerByCurrencyCode").queryParam("code", code);

		ResponseEntity<CoinIssuer> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,
				CoinIssuer.class);
		return response.getBody();
	}

}
