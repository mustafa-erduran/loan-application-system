package com.definexpracticum.loanapplicationsystem.service;


import com.definexpracticum.loanapplicationsystem.dto.request.AuthenticationRequest;
import com.definexpracticum.loanapplicationsystem.dto.request.UserRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.AuthenticationResponse;
import com.definexpracticum.loanapplicationsystem.model.ERole;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public record AuthenticationService(UserRepository repository,
                                    PasswordEncoder passwordEncoder,
                                    JwtService jwtService,
                                    AuthenticationManager authenticationManager) {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

    public AuthenticationResponse register(@NotNull UserRequest request) {

        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .citizenId(request.getCitizenId())
                .birthDate(request.getBirthDate())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(ERole.ROLE_USER)
                .build();
        repository.save(user);
        logger.info("User has been created!");
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(@NotNull AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail())
                .orElseThrow();
        logger.info("Login successfully!");
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }


}

