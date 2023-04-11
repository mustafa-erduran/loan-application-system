package com.definexpracticum.loanapplicationsystem.controller;

import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public @NotNull ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/users/{id}")
    public @NotNull ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUserByUserId(id));
    }

    @DeleteMapping(value = "/users")
    public @NotNull ResponseEntity<HttpStatus> deleteAllUsers() {
        userService.deleteAllUsers();
        return (ResponseEntity<HttpStatus>) ResponseEntity.accepted();
    }

    @DeleteMapping(value = "/users/{id}")
    public @NotNull ResponseEntity<Map<String, Boolean>> deleteUserById(@PathVariable Long id) {
        Map<String, Boolean> response = new HashMap<>();
        Boolean result = userService.deleteUserByUserId(id);
        if (result) {
            response.put("Deleted", Boolean.TRUE);
        } else
            response.put("user not found with id: "+ id, Boolean.FALSE);
        return ResponseEntity.ok(response);
    }
}
