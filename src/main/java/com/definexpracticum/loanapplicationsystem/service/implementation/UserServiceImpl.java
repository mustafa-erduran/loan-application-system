package com.definexpracticum.loanapplicationsystem.service.implementation;

import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.exception.ResourceNotFoundRuntimeException;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import com.definexpracticum.loanapplicationsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userList = new ArrayList<>();

        for (var user : userRepository.findAll()) {
            userList.add(convertUserToUserResponse(user));
        }
        return userList;
    }

    @Override
    public UserResponse findUserByUserId(Long userId) {
        return convertUserToUserResponse(userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundRuntimeException("user not found!")));
    }

    @Override
    public UserResponse findUserByCitizenId(String citizenId) {
        return convertUserToUserResponse(userRepository.findByCitizenId(citizenId)
                .orElseThrow(() -> new ResourceNotFoundRuntimeException("user not found!")));
    }

    @Override
    public Boolean deleteUserByUserId(Long userId) {
        Boolean isExist = userRepository.existsById(userId);
        if (isExist) {
            userRepository.deleteById(userId);
            logger.info("User has been deleted! User id: {} ", userId);
        }
        return isExist;
    }

    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
        logger.info("All users has been deleted!");
    }

    private UserResponse convertUserToUserResponse(User savedUser) {
        return UserResponse.builder()
                .id(savedUser.getId())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .birthDate(savedUser.getBirthDate())
                .citizenId(savedUser.getCitizenId())
                .email(savedUser.getEmail())
                .build();
    }

}
