package com.definexpracticum.loanapplicationsystem.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoanApplicationRequest {

    private Long userId;
    private Integer income;
    private Integer loanGuarantee;
}
