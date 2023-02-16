package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.converter.UserConverter;
import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public List<UserResponse> getAllUsers() {
        List<UserResponse> userList = new ArrayList<>();

        for (var user : userRepository.findAll()) {
            userList.add(userConverter.convertUserToUserResponse(user));
        }
        return userList;
    }

    public UserResponse findUserByUserId(Long userId){
        return userConverter.convertUserToUserResponse(userRepository.findById(userId).get());
    }

    public void deleteUserByUserId(Long userId){
        userRepository.deleteById(userId);
        logger.info("User has been deleted! User id: {} " , userId);
    }

    public void deleteAllUsers(){
        userRepository.deleteAll();
        logger.info("All users has been deleted!");
    }

}
