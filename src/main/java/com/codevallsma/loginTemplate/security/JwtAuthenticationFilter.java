package com.codevallsma.loginTemplate.security;

import com.codevallsma.loginTemplate.utils.TokenProvider;
import com.codevallsma.loginTemplate.web.presentation.AuthorizationRequest;
import com.codevallsma.loginTemplate.web.presentation.UserToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static com.codevallsma.loginTemplate.utils.Constants.*;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
		super.setAuthenticationFailureHandler(new JwtAuthenticationFailureHandler());
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			AuthorizationRequest userCredentials = new ObjectMapper().readValue(request.getInputStream(), AuthorizationRequest.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					userCredentials.getUserName(), userCredentials.getPassword()));
		} catch (IOException e) {
			response.setStatus(HttpStatus.BAD_REQUEST.value());
			try {
				response.getWriter().append(jsonResponseBadRequest());
			} catch (IOException e1) {
				return null;
			}
		}
		return null;
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

		String token = TokenProvider.generateToken(authResult);
		UserToken userToken = new UserToken(token);
		response.setStatus(HttpStatus.OK.value());
		response.addHeader(HEADER_AUTHORIZATION_KEY, TOKEN_BEARER_PREFIX + " " + token);
		response.addHeader(CONTENT_TYPE, "application/json");
		response.getWriter().append(userToken.toString());
	}
	private String jsonResponseBadRequest() {
		long date = new Date().getTime();
		return "{\"timestamp\": " + date + ", "
				+ "\"status\": " + HttpStatus.BAD_REQUEST.value() + ", "
				+ "\"error\": \"Bad Request\", "
				+ "\"message\": \"Authentication failed: Wrong JSON style \", "
				+ "\"path\": \"/login\"}";
	}
}
