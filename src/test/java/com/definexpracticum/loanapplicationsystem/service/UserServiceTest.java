package com.definexpracticum.loanapplicationsystem.service;

import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.model.User;
import com.definexpracticum.loanapplicationsystem.repository.UserRepository;
import com.definexpracticum.loanapplicationsystem.service.implementation.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = User.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .citizenId("test")
                .email("test")
                .birthDate("test")
                .password("test")
                .loanScore(0)
                .build();
    }

    @Test
    void getAllUsers() {
        userService.getAllUsers();
        verify(userRepository).findAll();
    }

    @Test
    void findUserByUserId() {
        given(userRepository.findById(1L)).willReturn(Optional.ofNullable(user));
        UserResponse userResponse = userService.findUserByUserId(1L);
        assertThat(userResponse).isNotNull();
    }

    @Test
    void deleteUserByUserId() {
        when(userRepository.existsById(user.getId())).thenReturn(true);
        userService.deleteUserByUserId(1L);
        verify(userRepository).deleteById(1L);
    }

    @Test
    void deleteAllUsers() {
        userService.deleteAllUsers();
        verify(userRepository).deleteAll();
    }
}