package com.definexpracticum.loanapplicationsystem.dto.request;

import lombok.*;

@Data
@Builder
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String citizenId;
    private String birthDate;
    private String email;
    private String password;

}
