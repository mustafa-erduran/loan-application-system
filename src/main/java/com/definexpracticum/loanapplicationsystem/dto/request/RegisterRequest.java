package com.definexpracticum.loanapplicationsystem.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

@Getter
@Setter
@Builder
public class RegisterRequest {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String citizenId;

    @NotNull
    private String birthDate;

    @NotNull
    private String email;

    @Size(min = 6, max = 150)
    private String password;

}
