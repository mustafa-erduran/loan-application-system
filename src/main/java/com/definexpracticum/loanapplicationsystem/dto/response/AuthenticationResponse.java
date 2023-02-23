package com.definexpracticum.loanapplicationsystem.dto.response;

import lombok.Getter;

public class AuthenticationResponse {

    public AuthenticationResponse(String token){
        this.token = token;
    }
    @Getter
    private String token;
}