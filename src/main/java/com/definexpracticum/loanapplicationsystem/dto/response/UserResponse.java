package com.definexpracticum.loanapplicationsystem.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;


@Data
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String citizenId;
    private String email;
}
