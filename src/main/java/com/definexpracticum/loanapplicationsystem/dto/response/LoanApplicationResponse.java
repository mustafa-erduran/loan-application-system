package com.definexpracticum.loanapplicationsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoanApplicationResponse {

    private Long applicationId;
    private String message;
    private String citizenId;

}
