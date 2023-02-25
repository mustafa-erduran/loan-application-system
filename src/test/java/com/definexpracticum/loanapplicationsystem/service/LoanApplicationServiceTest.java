package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.request.LoanApplicationRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanApplicationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanResponse;
import com.definexpracticum.loanapplicationsystem.model.ELoanStatus;
import com.definexpracticum.loanapplicationsystem.model.Loan;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.LoanRepository;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import com.definexpracticum.loanapplicationsystem.service.implementation.LoanApplicationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@ExtendWith(MockitoExtension.class)
class LoanApplicationServiceTest {

    @InjectMocks
    private LoanApplicationServiceImpl loanApplicationServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LoanRepository loanRepository;

    private User user;

    @BeforeEach
    void setUp(){
        user = User.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .citizenId("testCitizenId")
                .email("test")
                .birthDate("testBirthDate")
                .password("test")
                .loanScore(800)
                .build();

    }

    @Test
    void loanCollector() {
        LoanApplicationRequest request = LoanApplicationRequest.builder()
                .userId(1L)
                .income(15000)
                .loanGuarantee(0)
                .build();

        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));
        LoanApplicationResponse loanApplicationResponse = loanApplicationServiceImpl.loanCollector(request);

        assertThat(loanApplicationResponse.getCitizenId()).isEqualTo("testCitizenId");
        assertThat(loanApplicationResponse.getMessage()).isEqualTo("Loan application has been received.We will inform you about loan status in future.");

    }

    @Test
    void getLoanResult() {
        Loan loan = Loan.builder()
                .id(1L)
                .status(ELoanStatus.ONAY)
                .loanLimit(30000)
                .user(user)
                .build();

        List<Loan> loans = Arrays.asList(loan);
        given(loanRepository.findByUserId(user.getId())).willReturn(Optional.of(loans));
        List<LoanResponse> loanResponses = loanApplicationServiceImpl.getLoanResult(user.getId());

        assertThat(loanResponses.size()).isNotZero();

    }

}