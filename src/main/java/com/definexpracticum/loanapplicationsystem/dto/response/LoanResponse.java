package com.definexpracticum.loanapplicationsystem.dto.response;

import com.definexpracticum.loanapplicationsystem.model.ELoanStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Builder
public class LoanResponse {

    private Integer loanLimit;
    private ELoanStatus status;
    private Date applicationDate;

}
