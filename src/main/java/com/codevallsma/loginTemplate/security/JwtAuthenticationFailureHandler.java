package com.codevallsma.loginTemplate.security;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {

	private final HttpStatus statusErrorResponse;

	public JwtAuthenticationFailureHandler(HttpStatus statusErrorResponse) {
		this.statusErrorResponse = statusErrorResponse;
	}

	public JwtAuthenticationFailureHandler() {
		this.statusErrorResponse = HttpStatus.UNAUTHORIZED;
	}

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
		response.setStatus(statusErrorResponse.value());
		response.setContentType("application/json");
		response.getWriter().append(jsonResponse());
	}

	private String jsonResponse() {
		long date = new Date().getTime();
		return "{\"timestamp\": " + date + ", "
				+ "\"status\": " + statusErrorResponse.value() + ", "
				+ "\"error\": \"Unauthorized\", "
				+ "\"message\": \"Authentication failed: bad credentials\", "
				+ "\"path\": \"/login\"}";
	}
	public static void badToken(HttpServletResponse response, String message, int statusCode) throws IOException {
		response.setStatus(statusCode);
		response.setContentType("application/json");
		response.getWriter().append(jsonResponseBadToken(message, statusCode));
	}
	private static String jsonResponseBadToken(String message, int statusCode) {
		long date = new Date().getTime();
		return "{\"timestamp\": " + date + ", "
				+ "\"status\": " + statusCode + ", "
				+ "\"error\": \"Unauthorized\", "
				+ "\"message\": "+message+" ,"
				+ "}";
	}
}
