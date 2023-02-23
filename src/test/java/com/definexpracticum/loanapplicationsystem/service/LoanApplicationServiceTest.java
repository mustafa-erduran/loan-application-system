package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.repository.LoanRepository;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceTest {

    @InjectMocks
    private LoanApplicationService loanApplicationService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanRepository loanRepository;


    @Test
    void loanCollector() {

    }

    @Test
    void getLoanResult() {
    }
}