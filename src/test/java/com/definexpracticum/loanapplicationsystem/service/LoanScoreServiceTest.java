package com.definexpracticum.loanapplicationsystem.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
class LoanScoreServiceTest {

    @InjectMocks
    private LoanScoreService service;

    @Test
    void getLoanScore() {
        Integer loanScore = service.getLoanScore();
        assertThat(loanScore).isBetween(99,3001);
    }
}