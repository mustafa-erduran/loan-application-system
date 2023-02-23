package com.definexpracticum.loanapplicationsystem.controller;

import com.definexpracticum.loanapplicationsystem.dto.request.LoanApplicationRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanApplicationResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.LoanResponse;
import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.service.LoanApplicationService;
import com.definexpracticum.loanapplicationsystem.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("api/v1/loan")
public class LoanApplicationController {

    @Autowired
    private LoanApplicationService loanApplicationService;

    @Autowired
    private UserService userService;


    @PostMapping("/application")
    public ResponseEntity<LoanApplicationResponse> loanApplication(@RequestBody LoanApplicationRequest request){
        return ResponseEntity.ok(loanApplicationService.loanCollector(request));
    }

    @GetMapping("/result/{citizenId}/{birthDate}")
    public ResponseEntity<List<LoanResponse>> getLoanResult(@PathVariable String citizenId, @PathVariable String birthDate){
        UserResponse userResponse = userService.findUserByCitizenId(citizenId);
        if(userResponse.getBirthDate().equals(birthDate)){
            return ResponseEntity.ok(loanApplicationService.getLoanResult(userResponse.getId()));
        }
        else
            return (ResponseEntity<List<LoanResponse>>) ResponseEntity.badRequest();
    }

}
