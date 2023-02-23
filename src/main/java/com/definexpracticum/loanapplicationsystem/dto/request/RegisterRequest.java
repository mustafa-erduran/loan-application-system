package com.definexpracticum.loanapplicationsystem.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String citizenId;
    private String birthDate;
    private String email;
    private String password;

}
