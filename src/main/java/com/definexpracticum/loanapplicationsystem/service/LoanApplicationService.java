package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.request.LoanApplicationRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanApplicationResponse;
import com.definexpracticum.loanapplicationsystem.model.ELoanStatus;
import com.definexpracticum.loanapplicationsystem.model.Loan;
import com.definexpracticum.loanapplicationsystem.repository.LoanApplicationRepository;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class LoanApplicationService {

    private static final Integer MIN_SCORE = 500;
    private static final Integer MAX_SCORE = 1000;
    private static final Integer MIN_INCOME = 5000;
    private static final Integer MAX_INCOME = 10000;


    private static final Integer LOAN_LIMIT_COEFFICIENT = 4;

    @Autowired
    private Loan loan;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanApplicationRepository loanApplicationRepository;

    private Integer LoanCalculator(Integer income, Integer loanGuarantee, Integer loanScore){
        Integer loanLimit;
        if(loanScore < MIN_SCORE){
            loanLimit = 0;
        }
        else if(loanScore >= MIN_SCORE && loanScore < MAX_SCORE && income < MIN_INCOME ){
            loanLimit = 10000 + loanGuarantee/10;
        }
        else if(loanScore > MIN_SCORE && loanScore < MAX_SCORE && income >= MIN_INCOME && income <= MAX_INCOME){
            loanLimit = 20000 + loanGuarantee/5;
        }
        else if(loanScore > MIN_SCORE && loanScore < MAX_SCORE && income >MAX_INCOME){
            loanLimit = (income * LOAN_LIMIT_COEFFICIENT/2) + (loanGuarantee/4);
        }
        else if(loanScore >= MAX_SCORE){
            loanLimit = (income * LOAN_LIMIT_COEFFICIENT) + loanGuarantee/2;
        }
        else {
            loanLimit = 0;
        }
        return loanLimit;
    }

    public LoanApplicationResponse loanCollector(LoanApplicationRequest request){
        ELoanStatus status;
        var user = userRepository.findById(request.getUserId()).get();
        Integer loanLimit = LoanCalculator(request.getIncome(),request.getLoanGuarantee(),user.getLoanScore());
        status = (loanLimit == 0) ? ELoanStatus.RED : ELoanStatus.ONAY;
        Loan newLoanApplication = Loan.builder()
                .user(user)
                .limit(loanLimit)
                .status(status)
                .build();
        loanApplicationRepository.save(newLoanApplication);

        var applicationResponse = LoanApplicationResponse.builder()
                .applicationId(newLoanApplication.getId())
                .citizenId(user.getCitizenId())
                .message("Loan application has been received.We will inform you about loan status in future.")
                .build();

        return applicationResponse;
    }
}
