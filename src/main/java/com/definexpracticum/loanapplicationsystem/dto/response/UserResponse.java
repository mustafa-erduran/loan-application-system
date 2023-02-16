package com.definexpracticum.loanapplicationsystem.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.Date;


@Data
@Builder
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthDate;
    private String citizenId;
    private String email;
}
