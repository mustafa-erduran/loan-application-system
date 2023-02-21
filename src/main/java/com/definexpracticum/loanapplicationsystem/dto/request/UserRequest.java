package com.definexpracticum.loanapplicationsystem.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "User DTO", description = "Test")
public class UserRequest {

    private String firstName;
    private String lastName;

    @ApiModelProperty(required = true)
    private String citizenId;

    @ApiModelProperty(required = true)
    private String birthDate;

    @ApiModelProperty(required = true)
    private String email;

    @ApiModelProperty(required = true)
    private String password;

}
