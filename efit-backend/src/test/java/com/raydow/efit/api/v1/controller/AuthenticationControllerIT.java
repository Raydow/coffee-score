package com.raydow.efit.api.v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raydow.efit.api.v1.dto.AuthenticationRequestDTO;
import com.raydow.efit.api.v1.dto.user.UserCreateDTO;
import com.raydow.efit.repository.TokenRepository;
import com.raydow.efit.repository.UserRepository;
import com.raydow.efit.service.vo.AuthenticationResponseVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Disabled
class AuthenticationControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenRepository tokenRepository;

    private static final String BASE_PATH = "/api/v1/auth";

    @BeforeEach
    void cleanDatabase() {
        tokenRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    @DisplayName("Should register a new user and return access and refresh tokens")
    void register_shouldCreateUserAndReturnTokens() throws Exception {
        UserCreateDTO dto = createUserDto("newuser");

        String response = mockMvc.perform(post(BASE_PATH + "/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        AuthenticationResponseVO responseVO = objectMapper.readValue(response, AuthenticationResponseVO.class);

        assertThat(responseVO).isNotNull();
        assertThat(responseVO.getAccessToken()).isNotBlank();
        assertThat(responseVO.getRefreshToken()).isNotBlank();
    }

//    @Test
//    @DisplayName("Should register and authenticate a user returning valid tokens")
//    void authenticate_shouldReturnTokens() throws Exception {
//        UserCreateDTO dto = createUserDto("authuser");
//
//        mockMvc.perform(post(BASE_PATH + "/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isOk());
//
//        AuthenticationRequestDTO authDto = new AuthenticationRequestDTO();
//        authDto.setEmail(dto.getEmail());
//        authDto.setPassword(dto.getPassword());
//
//        String response = mockMvc.perform(post(BASE_PATH + "/authenticate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(authDto)))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        AuthenticationResponseVO authResponse = objectMapper.readValue(response, AuthenticationResponseVO.class);
//
//        assertThat(authResponse.getAccessToken()).isNotBlank();
//        assertThat(authResponse.getRefreshToken()).isNotBlank();
//    }
//
//    @Test
//    @DisplayName("Should refresh access token using a valid refresh token")
//    void refreshToken_shouldReturnNewAccessToken() throws Exception {
//        UserCreateDTO dto = createUserDto("refreshuser");
//
//        mockMvc.perform(post(BASE_PATH + "/register")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//                .andExpect(status().isOk());
//
//        AuthenticationRequestDTO authDto = new AuthenticationRequestDTO();
//        authDto.setEmail(dto.getEmail());
//        authDto.setPassword(dto.getPassword());
//
//        String authResponse = mockMvc.perform(post(BASE_PATH + "/authenticate")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(authDto)))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse()
//                .getContentAsString();
//
//        AuthenticationResponseVO tokens = objectMapper.readValue(authResponse, AuthenticationResponseVO.class);
//
//        MockHttpServletResponse response = mockMvc.perform(post(BASE_PATH + "/refresh-token")
//                        .header("Authorization", "Bearer " + tokens.getRefreshToken()))
//                .andExpect(status().isOk())
//                .andReturn()
//                .getResponse();
//
//        String newAccessToken = response.getContentAsString();
//
//        assertThat(newAccessToken).isNotBlank();
//    }

    private UserCreateDTO createUserDto(String prefix) {
        UserCreateDTO dto = new UserCreateDTO();
        dto.setName("User " + prefix);
        dto.setEmail(prefix + UUID.randomUUID() + "@example.com");
        dto.setPassword("Password123");
        dto.setBirthDate(LocalDate.of(1990, 1, 1));
        dto.setHeight(1.75);
        dto.setWeight(70.0);
        dto.setGender("MALE");
        dto.setUserRole("USER");
        return dto;
    }
}
