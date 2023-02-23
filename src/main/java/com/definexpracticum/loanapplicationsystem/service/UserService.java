package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.model.User;
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

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    private UserResponse convertUserToUserResponse(User savedUser){
        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .birthDate(savedUser.getBirthDate())
                .citizenId(savedUser.getCitizenId())
                .email(savedUser.getEmail())
                .build();
    }

    public List<UserResponse> getAllUsers() {
        List<UserResponse> userList = new ArrayList<>();

        for (var user : userRepository.findAll()) {
            userList.add(convertUserToUserResponse(user));
        }
        return userList;
    }

    public UserResponse findUserByUserId(Long userId){
        return convertUserToUserResponse(userRepository.findById(userId).orElseThrow(()-> new RuntimeException("user not found!")));
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
