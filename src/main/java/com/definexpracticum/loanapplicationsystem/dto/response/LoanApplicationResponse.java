package com.definexpracticum.loanapplicationsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationResponse {

    private Long applicationId;
    private String message;
    private String citizenId;

}
