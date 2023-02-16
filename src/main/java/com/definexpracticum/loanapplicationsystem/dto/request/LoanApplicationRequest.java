package com.definexpracticum.loanapplicationsystem.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequest {

    private Long userId;
    private Integer income;

    @Min(value = 0, message = "The value must be positive")
    private Integer loanGuarantee;
}
