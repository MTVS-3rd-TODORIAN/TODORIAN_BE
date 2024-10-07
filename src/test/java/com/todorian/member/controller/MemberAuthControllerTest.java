package com.todorian.member.controller;

import com.todorian._core.utils.ApiUtils;
import com.todorian.member.command.application.controller.MemberAuthController;
import com.todorian.member.command.application.dto.MemberAuthRequestDTO;
import com.todorian.member.command.application.service.MemberAuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberAuthControllerTest {

    @InjectMocks
    private MemberAuthController memberAuthController;

    @Mock
    private MemberAuthService memberAuthService;
    @Mock
    private HttpServletRequest httpServletRequest;

    private static Stream<Arguments> createMember() {
        return Stream.of(
                Arguments.of("testNickName1", "test1@test.com", "test1234!", "test1234!")
        );
    }

    @DisplayName("회원 가입")
    @MethodSource("createMember")
    @ParameterizedTest
    void signUp(String nickName, String email, String password, String confirmPassword) {

        // given
        MemberAuthRequestDTO.signUpDTO requestDTO = new MemberAuthRequestDTO.signUpDTO(
                nickName, email, password, confirmPassword
        );

        //when
        ResponseEntity<?> response = memberAuthController.signUp(requestDTO);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(memberAuthService, times(1)).signUp(any(MemberAuthRequestDTO.signUpDTO.class));
    }

    private static Stream<Arguments> givenMember() {
        return Stream.of(
                Arguments.of("test1@test.com", "test1234!")
        );
    }

    @DisplayName("로그인")
    @MethodSource("givenMember")
    @ParameterizedTest
    void login(String email, String password) {

        // given
        MemberAuthRequestDTO.authDTO requestDTO = new MemberAuthRequestDTO.authDTO(
                email, password
        );


    }
}
