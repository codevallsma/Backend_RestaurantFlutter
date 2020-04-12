package com.codevallsma.loginTemplate.security;

import com.codevallsma.loginTemplate.interfaces.UserService;
import com.codevallsma.loginTemplate.utils.Constants;
import com.codevallsma.loginTemplate.utils.TokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.SignatureException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
		String authorizationHeader = httpServletRequest.getHeader(Constants.HEADER_AUTHORIZATION_KEY);

		if (StringUtils.isEmpty(authorizationHeader) || !authorizationHeader
				.startsWith(Constants.TOKEN_BEARER_PREFIX)) {
			filterChain.doFilter(httpServletRequest, httpServletResponse);
			return;
		}
		final String token = authorizationHeader.replace(Constants.TOKEN_BEARER_PREFIX + " ", "");
		try {
	try {

		String userName = TokenProvider.getUserName(token);
		UserDetails user = userService.loadUserByUsername(userName);
		UsernamePasswordAuthenticationToken authenticationToken = TokenProvider.getAuthentication(token, user);
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}catch (ExpiredJwtException exp){
		JwtAuthenticationFailureHandler.badToken(httpServletResponse, "\"The token you provided has expired \"", HttpStatus.UNAUTHORIZED.value());
	}
		}catch (SignatureException| UsernameNotFoundException e){
			JwtAuthenticationFailureHandler.badToken(httpServletResponse, "\"Authentication failed: could not authenticate\"", HttpStatus.FORBIDDEN.value());
		}

	}
}
