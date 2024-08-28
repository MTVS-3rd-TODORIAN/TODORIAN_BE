package com.todorian.member.service;

import com.todorian.member.command.application.dto.MemberRequestDTO;
import com.todorian.member.command.application.dto.MemberResponseDTO;
import com.todorian.member.command.application.service.MemberAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class MemberAuthServiceTest {

    @Autowired
    private MemberAuthService memberAuthService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    void setUp() {
        MemberRequestDTO.signUpDTO requestDTO = new MemberRequestDTO.signUpDTO(
                "userNickName",
                "user@test.com",
                "test1234",
                "test1234"
        );

        memberAuthService.signUp(requestDTO);
    }

    private static Stream<Arguments> createMember() {
        return Stream.of(
                Arguments.of("userNickName1", "user1@test.com", "test1234!", "test1234!")
        );
    }

    @DisplayName("signUp 테스트")
    @ParameterizedTest
    @MethodSource("createMember")
    void signUp(String userNickName, String userEmail, String userPassword, String userConfirmPassword) {

        MemberRequestDTO.signUpDTO requestDTO = new MemberRequestDTO.signUpDTO(
                userNickName,
                userEmail,
                userPassword,
                userConfirmPassword
        );

        assertDoesNotThrow(
                () -> memberAuthService.signUp(requestDTO)
        );
    }

    @DisplayName("login 테스트")
    @Test
    void login() {

        MemberRequestDTO.loginDTO requestDTO = new MemberRequestDTO.loginDTO(
                "user@test.com",
                "test1234"
        );

        // when
        MemberResponseDTO.authTokenDTO  authTokenDTO = memberAuthService.login(httpServletRequest, requestDTO);

        // then
        System.out.println("authTokenDTO = " + authTokenDTO);

        assertNotNull(authTokenDTO);
        assertNotNull(authTokenDTO.accessToken());
        assertNotNull(authTokenDTO.refreshToken());
    }
}
