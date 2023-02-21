package com.definexpracticum.loanapplicationsystem.dto.response;

import com.definexpracticum.loanapplicationsystem.model.ELoanStatus;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class LoanResponse {

    private Integer loanLimit;
    private ELoanStatus status;
    private Date applicationDate;

}
