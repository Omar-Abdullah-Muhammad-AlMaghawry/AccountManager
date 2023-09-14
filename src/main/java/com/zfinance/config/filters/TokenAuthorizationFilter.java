package com.zfinance.config.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.zfinance.dto.response.user.UserRecord;
import com.zfinance.services.security.SecurityService;

@Component
public class TokenAuthorizationFilter extends OncePerRequestFilter {

	private final String AUTHORIZATION_HEADER = "Authorization";
	private final String TOKEN_PREFIX = "Bearer ";
	private String token;

	@Autowired
	private SecurityService securityService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			token = parseToken(request);
			if (token != null) {
				UserRecord userDto = securityService.validateToken(token);
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDto, null, null);
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			} else {
				SecurityContextHolder.clearContext();
			}
		} catch (Exception e) {
			logger.error(e);
			SecurityContextHolder.clearContext();
		}
		filterChain.doFilter(request, response);
	}

	private String parseToken(HttpServletRequest request) {
		String authenticationHeader = request.getHeader(AUTHORIZATION_HEADER);
		if (authenticationHeader == null || !authenticationHeader.startsWith(TOKEN_PREFIX)) {
			return null;
		}

		return request.getHeader(AUTHORIZATION_HEADER).replace(TOKEN_PREFIX, "");
	}

	public String getToken() {
		return token;
	}

}
