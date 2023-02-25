package com.definexpracticum.loanapplicationsystem.dto.request;


import jakarta.validation.constraints.Size;
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

    @Size(min = 6, max = 150)
    private String password;

}
