package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.Optional;



@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;


    @Test
    void getAllUsers() {
        userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void findUserByUserId() {
        User user = User.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .citizenId("test")
                .email("test")
                .birthDate("test")
                .password("test")
                .loanScore(0)
                .build();
        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));
        UserResponse userResponse = userService.findUserByUserId(1L);
        assertThat(userResponse).isNotNull();
    }

    @Test
    void deleteUserByUserId() {
        userService.deleteUserByUserId(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteAllUsers() {
        userService.deleteAllUsers();
        verify(userRepository).deleteAll();
    }
}