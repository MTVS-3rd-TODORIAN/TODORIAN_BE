package com.todorian.member.service;

import com.todorian.member.command.application.dto.MemberAuthRequestDTO;
import com.todorian.member.command.application.dto.MemberAuthResponseDTO;
import com.todorian.member.command.application.service.MemberAuthService;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.repository.MemberRepository;
import com.todorian.redis.domain.RefreshToken;
import com.todorian.redis.repository.RefreshTokenRedisRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MemberAuthServiceTest {

    @InjectMocks
    private MemberAuthService memberAuthService;

    @Mock
    private MemberRepository memberRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Mock
    private HttpServletRequest httpServletRequest;

    private static Stream<Arguments> createMember() {
        return Stream.of(
                Arguments.of("testNickName1", "test1@test.com", "test1234!", "test1234!")
        );
    }

    @DisplayName("signUp 테스트")
    @ParameterizedTest
    @MethodSource("createMember")
    void signUp(String userNickName, String userEmail, String userPassword, String userConfirmPassword) {

        // given
        MemberAuthRequestDTO.signUpDTO requestDTO = new MemberAuthRequestDTO.signUpDTO(
                userNickName,
                userEmail,
                userPassword,
                userConfirmPassword
        );

        when(memberRepository.findByEmail(userEmail)).thenReturn(Optional.empty());

        String encodedPassword = passwordEncoder.encode(userConfirmPassword);
        when(passwordEncoder.encode(userConfirmPassword)).thenReturn(encodedPassword);
        when(passwordEncoder.matches(userPassword, encodedPassword)).thenReturn(true);

        // when
        memberAuthService.signUp(requestDTO);

        // then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @DisplayName("login 테스트")
    @Test
    void login() {

        MemberAuthRequestDTO.authDTO requestDTO = new MemberAuthRequestDTO.authDTO(
                "test1@test.com",
                "test1234"
        );

        // when
        MemberAuthResponseDTO.authTokenDTO authTokenDTO = memberAuthService.login(httpServletRequest, requestDTO);

        // then
        System.out.println("authTokenDTO = " + authTokenDTO);

        assertNotNull(authTokenDTO);
        assertNotNull(authTokenDTO.accessToken());
        assertNotNull(authTokenDTO.refreshToken());
    }

    @DisplayName(("토큰 재발급 테스트"))
    @Test
    void reissueToken() {

        // given
        MemberAuthRequestDTO.authDTO authDTO = new MemberAuthRequestDTO.authDTO(
                "test1@test.com",
                "test1234"
        );

        MemberAuthResponseDTO.authTokenDTO authTokenDTO = memberAuthService.login(httpServletRequest, authDTO);

        // 실제 HTTP 요청에서 토큰 추출
        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer " + authTokenDTO.refreshToken());

        // when
        MemberAuthResponseDTO.authTokenDTO newAuthTokenDTO = memberAuthService.reissueToken(httpServletRequest);

        // then
        assertNotNull(newAuthTokenDTO);
        System.out.println("newAuthTokenDTO = " + newAuthTokenDTO);
    }

    @DisplayName("logout 테스트")
    @Test
    void logout() {

        // given
        MemberAuthRequestDTO.authDTO authDTO = new MemberAuthRequestDTO.authDTO(
                "test1@test.com",
                "test1234"
        );

        MemberAuthResponseDTO.authTokenDTO authTokenDTO = memberAuthService.login(httpServletRequest, authDTO);

        // 실제 HTTP 요청에서 토큰 추출
        when(httpServletRequest.getHeader("Authorization")).thenReturn("Bearer " + authTokenDTO.refreshToken());

        // when
        memberAuthService.logout(httpServletRequest);

        // then
        RefreshToken refreshToken = refreshTokenRedisRepository.findByRefreshToken(authTokenDTO.refreshToken());
        assertNull(refreshToken);
    }
}
