package com.definexpracticum.loanapplicationsystem.converter;

import com.definexpracticum.loanapplicationsystem.dto.request.UserRequest;
import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public User convertRequestToUser(UserRequest request){
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .birthDate(request.getBirthDate())
                .citizenId(request.getCitizenId())
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    public UserResponse convertUserToUserResponse(User savedUser){
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
