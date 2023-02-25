package com.definexpracticum.loanapplicationsystem.controller;

import com.definexpracticum.loanapplicationsystem.config.JwtAuthenticationFilter;
import com.definexpracticum.loanapplicationsystem.dto.response.UserResponse;
import com.definexpracticum.loanapplicationsystem.service.implementation.JwtServiceImpl;
import com.definexpracticum.loanapplicationsystem.service.implementation.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;


import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = UserController.class)
class UserControllerTest {

    @MockBean
    private UserServiceImpl userServiceImpl;

    @Mock
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtServiceImpl jwtServiceImpl;

    @Mock
    private AuthenticationManager authenticationManager;

    private UserResponse userResponse;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Autowired
    private WebApplicationContext context;


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        userResponse = UserResponse.builder()
                .id(1L)
                .firstName("test")
                .lastName("test")
                .citizenId("test")
                .email("test")
                .birthDate("test")
                .build();
    }

    @Test
    @WithMockUser(username = "test@gmail.com", password = "test123")
    void getAllUsers() throws Exception {
        when(userServiceImpl.getAllUsers()).thenReturn(Arrays.asList(userResponse));

        MvcResult mvcResult = mockMvc.perform(get("/users").accept("application/json")).andReturn();

        String response = mvcResult.getResponse().getContentAsString();
        verify(userServiceImpl, times(1)).getAllUsers();
        assertThat(objectMapper.writeValueAsString((Arrays.asList(userResponse)))).isEqualTo(response);

    }

    @Test
    void getUserById() {
    }

    @Test
    void deleteAllUsers() {
    }

    @Test
    void deleteUserById() {
    }
}