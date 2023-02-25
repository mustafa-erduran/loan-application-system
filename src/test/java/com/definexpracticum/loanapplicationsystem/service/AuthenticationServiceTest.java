package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.request.AuthenticationRequest;
import com.definexpracticum.loanapplicationsystem.dto.request.RegisterRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.AuthenticationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.RegisterResponse;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import com.definexpracticum.loanapplicationsystem.service.implementation.AuthenticationServiceImpl;
import com.definexpracticum.loanapplicationsystem.service.implementation.JwtServiceImpl;
import com.definexpracticum.loanapplicationsystem.service.implementation.LoanScoreServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationServiceImpl authenticationServiceImpl;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtServiceImpl jwtServiceImpl;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private LoanScoreServiceImpl loanScoreServiceImpl;

    @Test
    void register() {
        RegisterRequest request = RegisterRequest.builder()
                .firstName("Test-firstName")
                .lastName("Test-lastName")
                .email("Test-email@gmail.com")
                .birthDate("test-birthDate")
                .citizenId("Test-citizenId")
                .password("Test-password")
                .build();
        RegisterResponse registerResponse = authenticationServiceImpl.register(request);

        ArgumentCaptor<User> registerRequestArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(repository).save(registerRequestArgumentCaptor.capture());
        User capturedUser = registerRequestArgumentCaptor.getValue();
        assertThat(capturedUser.getFirstName()).isEqualTo(request.getFirstName());
    }

    @Test
    void authenticate() {
        User user = User.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .citizenId("test")
                .email("Test-email@gmail.com")
                .birthDate("test")
                .password("Test-password")
                .loanScore(0)
                .build();

        AuthenticationRequest request = AuthenticationRequest.builder()
                .email("Test-email@gmail.com")
                .password("Test-password")
                .build();

        given(repository.findByEmail(request.getEmail())).willReturn(Optional.of(user));
        AuthenticationResponse authenticationResponse = authenticationServiceImpl.authenticate(request);
        assertThat(authenticationResponse).isNotNull();

    }
}