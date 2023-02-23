package com.definexpracticum.loanapplicationsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RegisterResponse {

    private String firstName;
    private String lastName;
    private String citizenId;
    private String birthDate;
    private String email;

}
