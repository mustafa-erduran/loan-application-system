package com.definexpracticum.loanapplicationsystem.controller;


import com.definexpracticum.loanapplicationsystem.dto.request.UserRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.service.UserService;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public record UserController(UserService userService) {

    @Contract(" -> new")
    @GetMapping("/users")
    public @NotNull ResponseEntity<List<UserResponse>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Contract("_ -> new")
    @GetMapping("/users/{id}")
    public @NotNull ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
        return new ResponseEntity<>(userService.findUserByUserId(id),HttpStatus.OK);
    }


    @Contract(" -> new")
    @DeleteMapping(value = "/users")
    public @NotNull ResponseEntity<HttpStatus> deleteAllUsers(){
        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Contract("_ -> new")
    @DeleteMapping(value = "/users/{id}")
    public @NotNull ResponseEntity<HttpStatus> deleteUserById(@PathVariable Long id){
        userService.deleteUserByUserId(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
