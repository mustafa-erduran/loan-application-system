package com.definexpracticum.loanapplicationsystem.dto.request;

import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class LoanApplicationRequest {

    private Long userId;
    private Integer income;

    @Min(value = 0, message = "The value must be positive")
    private Integer loanGuarantee;
}
