package com.definexpracticum.loanapplicationsystem.service.implementation;

import com.definexpracticum.loanapplicationsystem.dto.request.AuthenticationRequest;
import com.definexpracticum.loanapplicationsystem.dto.request.RegisterRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.AuthenticationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.RegisterResponse;
import com.definexpracticum.loanapplicationsystem.model.ERole;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import com.definexpracticum.loanapplicationsystem.service.AuthenticationService;
import com.definexpracticum.loanapplicationsystem.service.JwtService;
import com.definexpracticum.loanapplicationsystem.service.LoanScoreService;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private LoanScoreService loanScoreService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .citizenId(registerRequest.getCitizenId())
                .birthDate(registerRequest.getBirthDate())
                .loanScore(loanScoreService.getLoanScore())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(ERole.ROLE_USER)
                .build();
        repository.save(user);
        logger.info("User has been created!");
        return convertRegisterRequestToRegisterResponse(registerRequest);
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = repository.findByEmail(request.getEmail()).get();
        logger.info("Login successfully!");
        String jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponse(jwtToken);
    }

    private RegisterResponse convertRegisterRequestToRegisterResponse(RegisterRequest registerRequest) {
        return RegisterResponse.builder()
                .firstName(registerRequest.getFirstName())
                .lastName((registerRequest.getLastName()))
                .email(registerRequest.getEmail())
                .citizenId(registerRequest.getCitizenId())
                .birthDate(registerRequest.getBirthDate())
                .build();
    }

}

