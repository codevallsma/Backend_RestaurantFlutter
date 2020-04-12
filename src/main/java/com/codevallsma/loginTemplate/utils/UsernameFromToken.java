package com.codevallsma.loginTemplate.utils;

import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;


public class UsernameFromToken {

    public static String usernameFromToken(HttpServletRequest httpServletRequest){
        String authorizationHeader = httpServletRequest.getHeader(Constants.HEADER_AUTHORIZATION_KEY);
        final String token = authorizationHeader.replace(Constants.TOKEN_BEARER_PREFIX + " ", "");
        try {
           return  TokenProvider.getUserName(token);
        }catch (SignatureException | UsernameNotFoundException e){
        }
        return "";
    }
}
