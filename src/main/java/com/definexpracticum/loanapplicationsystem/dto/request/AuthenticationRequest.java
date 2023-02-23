package com.definexpracticum.loanapplicationsystem.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {

    private String email;
    private String password;
}
