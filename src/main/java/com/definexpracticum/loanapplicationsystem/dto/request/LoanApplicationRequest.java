package com.definexpracticum.loanapplicationsystem.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoanApplicationRequest {

    @NotNull
    private Long userId;

    @NotNull
    @Min(value = 0, message = "Income value should not be negative!")
    private Integer income;

    @NotNull
    @Min(value = 0, message = "Loan guarantee value should not be negative!")
    private Integer loanGuarantee;
}
