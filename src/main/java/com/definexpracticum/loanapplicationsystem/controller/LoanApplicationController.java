package com.definexpracticum.loanapplicationsystem.controller;

import com.definexpracticum.loanapplicationsystem.dto.request.LoanApplicationRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanApplicationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanResponse;
import com.definexpracticum.loanapplicationsystem.service.LoanApplicationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("api/v1/loan")
@RequiredArgsConstructor
public class LoanApplicationController {

    private final LoanApplicationService loanApplicationService;

    @PostMapping("/application")
    public ResponseEntity<LoanApplicationResponse> loanApplication(@Valid @RequestBody LoanApplicationRequest request) {
        return ResponseEntity.ok(loanApplicationService.loanCollector(request));
    }

    @GetMapping("/result/{citizenId}/{birthDate}")
    public ResponseEntity<List<LoanResponse>> getLoanResult(@PathVariable String citizenId, @PathVariable String birthDate) {
       return ResponseEntity.ok(loanApplicationService.getLoanResult(citizenId,birthDate));
    }

}
