package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.request.AuthenticationRequest;
import com.definexpracticum.loanapplicationsystem.dto.request.RegisterRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.AuthenticationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.RegisterResponse;

public interface AuthenticationService {

    RegisterResponse register(RegisterRequest registerRequest);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
