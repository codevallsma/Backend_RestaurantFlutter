package com.codevallsma.loginTemplate.web.presentation;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class UserToken implements Serializable {
    @JsonProperty("id_token")
    private String idToken;

    public UserToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    @Override
    public String toString() {
        return "{\n" +
                '"'+"id_token"+'"'+':' + '"'+idToken + '"'+ "\n" +
                '}';
    }
}