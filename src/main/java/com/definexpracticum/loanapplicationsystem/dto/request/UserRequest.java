package com.definexpracticum.loanapplicationsystem.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String firstName;
    private String lastName;

    @NotBlank
    //@Size(min = 11,max = 11)
    private String citizenId;

    @NotBlank
    private Date birthDate;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

}
