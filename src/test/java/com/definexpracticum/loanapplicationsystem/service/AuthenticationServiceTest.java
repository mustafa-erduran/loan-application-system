package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.request.AuthenticationRequest;
import com.definexpracticum.loanapplicationsystem.dto.request.RegisterRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.AuthenticationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.RegisterResponse;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.junit.jupiter.api.Assertions;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService authenticationService;

    @Mock
    private UserRepository repository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private LoanScoreService loanScoreService;

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
        RegisterResponse registerResponse = authenticationService.register(request);

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
        AuthenticationResponse authenticationResponse = authenticationService.authenticate(request);
        assertThat(authenticationResponse).isNotNull();

    }
}