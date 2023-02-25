package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    public List<UserResponse> getAllUsers();

    public UserResponse findUserByUserId(Long userId);

    public UserResponse findUserByCitizenId(String citizenId);

    public Boolean deleteUserByUserId(Long userId);

    public void deleteAllUsers();
}
