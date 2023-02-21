package com.definexpracticum.loanapplicationsystem.dto.request;


import lombok.*;

import javax.validation.constraints.Min;
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
