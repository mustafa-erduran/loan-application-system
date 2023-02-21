package com.definexpracticum.loanapplicationsystem.controller;

import com.definexpracticum.loanapplicationsystem.dto.request.AuthenticationRequest;
import com.definexpracticum.loanapplicationsystem.dto.request.UserRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.AuthenticationResponse;
import com.definexpracticum.loanapplicationsystem.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody UserRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }


}
