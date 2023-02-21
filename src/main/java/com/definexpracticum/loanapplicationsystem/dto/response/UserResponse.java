package com.definexpracticum.loanapplicationsystem.dto.response;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String citizenId;
    private String email;
}
