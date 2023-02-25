package com.definexpracticum.loanapplicationsystem.service.implementation;

import com.definexpracticum.loanapplicationsystem.dto.request.LoanApplicationRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanApplicationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanResponse;
import com.definexpracticum.loanapplicationsystem.exception.ResourceNotFoundRuntimeException;
import com.definexpracticum.loanapplicationsystem.model.ELoanStatus;
import com.definexpracticum.loanapplicationsystem.model.Loan;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.LoanRepository;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import com.definexpracticum.loanapplicationsystem.service.LoanApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class LoanApplicationServiceImpl implements LoanApplicationService {

    private static final Integer MIN_SCORE = 500;
    private static final Integer MAX_SCORE = 1000;
    private static final Integer MIN_INCOME = 5000;
    private static final Integer MAX_INCOME = 10000;
    private static final Integer LOAN_LIMIT_COEFFICIENT = 4;
    private static final Integer LOAN_LIMIT_10K = 10000;
    private static final Integer LOAN_LIMIT_20K = 20000;

    private final Logger logger = LoggerFactory.getLogger(LoanApplicationServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanApplicationResponse loanCollector(LoanApplicationRequest request) {
        ELoanStatus status;
        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new ResourceNotFoundRuntimeException("user not found!"));
        Integer loanLimit = loanCalculator(request.getIncome(), request.getLoanGuarantee(), user.getLoanScore());
        status = (loanLimit == 0) ? ELoanStatus.RED : ELoanStatus.ONAY;
        Loan newLoanApplication = Loan.builder()
                .user(user)
                .loanLimit(loanLimit)
                .status(status)
                .build();
        loanRepository.save(newLoanApplication);
        logger.info("Loan application saved!");
        return LoanApplicationResponse.builder()
                .applicationId(newLoanApplication.getId())
                .citizenId(user.getCitizenId())
                .message("Loan application has been received.We will inform you about loan status in future.")
                .build();
    }

    @Override
    public List<LoanResponse> getLoanResult(Long userId) {
        List<LoanResponse> loanResponses = new ArrayList<>();
        List<Loan> loans = loanRepository.findByUserId(userId).orElseThrow(() -> new ResourceNotFoundRuntimeException("loan not found!"));
        for (Loan loan : loans) {
            loanResponses.add(LoanResponse.builder()
                    .loanLimit(loan.getLoanLimit())
                    .status(loan.getStatus())
                    .applicationDate(loan.getApplicationDate())
                    .build());
        }
        return loanResponses;

    }

    private Integer loanCalculator(Integer income, Integer loanGuarantee, Integer loanScore) {
        Integer loanLimit;
        if (loanScore < MIN_SCORE) {
            loanLimit = 0;
        } else if (loanScore >= MIN_SCORE && loanScore < MAX_SCORE && income < MIN_INCOME) {
            loanLimit = LOAN_LIMIT_10K + loanGuarantee / 10;
        } else if (loanScore > MIN_SCORE && loanScore < MAX_SCORE && income >= MIN_INCOME && income <= MAX_INCOME) {
            loanLimit = LOAN_LIMIT_20K + loanGuarantee / 5;
        } else if (loanScore > MIN_SCORE && loanScore < MAX_SCORE && income > MAX_INCOME) {
            loanLimit = (income * LOAN_LIMIT_COEFFICIENT / 2) + (loanGuarantee / 4);
        } else if (loanScore >= MAX_SCORE) {
            loanLimit = (income * LOAN_LIMIT_COEFFICIENT) + loanGuarantee / 2;
        } else {
            loanLimit = 0;
        }
        return loanLimit;
    }
}
