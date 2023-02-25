package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.request.LoanApplicationRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanApplicationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanResponse;

import java.util.List;

public interface LoanApplicationService {

    public LoanApplicationResponse loanCollector(LoanApplicationRequest request);

    public List<LoanResponse> getLoanResult(Long userId);
}
