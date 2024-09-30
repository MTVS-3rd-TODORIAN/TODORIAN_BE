package com.todorian.member.service;

import com.todorian._core.jwt.JWTTokenProvider;
import com.todorian.member.command.application.dto.MemberAuthRequestDTO;
import com.todorian.member.command.application.dto.MemberAuthResponseDTO;
import com.todorian.member.command.application.service.MemberAuthService;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.model.property.Authority;
import com.todorian.member.command.domain.model.property.SocialType;
import com.todorian.member.command.domain.model.property.Status;
import com.todorian.member.command.domain.repository.MemberRepository;
import com.todorian.redis.domain.RefreshToken;
import com.todorian.redis.repository.RefreshTokenRedisRepository;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
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
    private AuthenticationManagerBuilder authenticationManagerBuilder;
    @Mock
    private JWTTokenProvider jwtTokenProvider;
    @Mock
    private Authentication authentication;
    @Mock
    private RefreshTokenRedisRepository refreshTokenRedisRepository;

    @Mock
    private HttpServletRequest httpServletRequest;

    // Access token의 시간 : 15분
    private static final long ACCESS_TOKEN_LIFETIME = 15 * 60 * 1000L;
    // Refresh token의 시간 : 3일
    private static final long REFRESH_TOKEN_LIFETIME = 3 * 24 * 60 * 60 * 1000L;


    private static Stream<Arguments> createMember() {
        return Stream.of(
                Arguments.of("testNickName1", "test1@test.com", "test1234!", "test1234!")
        );
    }

    @DisplayName("signUp 테스트")
    @MethodSource("createMember")
    @ParameterizedTest
    void signUp(String userNickName, String userEmail, String userPassword, String userConfirmPassword) {

        // given
        MemberAuthRequestDTO.signUpDTO requestDTO = new MemberAuthRequestDTO.signUpDTO(
                userNickName,
                userEmail,
                userPassword,
                userConfirmPassword
        );

        when(memberRepository.findByEmail(userEmail)).thenReturn(Optional.empty());

        when(passwordEncoder.encode(userConfirmPassword)).thenReturn(userConfirmPassword);
        when(passwordEncoder.matches(userPassword, userConfirmPassword)).thenReturn(true);

        // when
        memberAuthService.signUp(requestDTO);

        // then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    private static Stream<Arguments> givenMember() {
        return Stream.of(
                Arguments.of("test1@test.com", "test1234!")
        );
    }

    @DisplayName("login 테스트")
    @MethodSource("givenMember")
    @ParameterizedTest
    void login(String userEmail, String userPassword) {

        // given
        MemberAuthRequestDTO.authDTO requestDTO = new MemberAuthRequestDTO.authDTO(
                userEmail,
                userPassword
        );

        Member member = Member.builder()
                .nickName("testNickName1")
                .email(userEmail)
                .password(userPassword)
                .socialType(SocialType.NONE)
                .authority(Authority.USER)
                .status(Status.ACTIVE)
                .build();

        when(memberRepository.findByEmail(userEmail)).thenReturn(Optional.of(member));
        when(passwordEncoder.matches(userPassword, userPassword)).thenReturn(true);

        AuthenticationManager authenticationManager = mock(AuthenticationManager.class);
        when(authenticationManagerBuilder.getObject()).thenReturn(authenticationManager);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userEmail,
                userPassword
        );
        when(authenticationManager.authenticate(authenticationToken)).thenReturn(authentication);

        MemberAuthResponseDTO.authTokenDTO authTokenDTO = new MemberAuthResponseDTO.authTokenDTO(
                "Bearer",
                "accessToken",
                ACCESS_TOKEN_LIFETIME,
                "refreshToken",
                REFRESH_TOKEN_LIFETIME
        );
        when(jwtTokenProvider.generateToken(authentication)).thenReturn(authTokenDTO);

        // when
        MemberAuthResponseDTO.authTokenDTO responseDTO = memberAuthService.login(httpServletRequest, requestDTO);

        // then
        System.out.println("responseDTO = " + responseDTO);

        assertNotNull(responseDTO);
        assertNotNull(responseDTO.accessToken());
        assertNotNull(responseDTO.refreshToken());
    }

    @DisplayName(("토큰 재발급 테스트"))
    @Test
    void reissueToken() {

        // given
        String token = "RefreshToken";
        String userId = "userId";

        // 모의 HTTP 요청에서 토큰 추출
        when(jwtTokenProvider.resolveToken(httpServletRequest)).thenReturn(token);

        // 유효성 검사 통과
        when(jwtTokenProvider.validateToken(token)).thenReturn(true);

        // RefreshToken 타입 확인
        when(jwtTokenProvider.isRefreshToken(token)).thenReturn(true);

        // Redis 에서 저장된 RefreshToken 가져오기
        RefreshToken refreshToken = RefreshToken.builder()
                .id(userId)
                .authorities(List.of(new SimpleGrantedAuthority(Authority.USER.name())))
                .refreshToken(token)
                .build();
        when(refreshTokenRedisRepository.findByRefreshToken(token)).thenReturn(refreshToken);

        // 새로운 AccessToken 및 RefreshToken 생성
        MemberAuthResponseDTO.authTokenDTO authTokenDTO = new MemberAuthResponseDTO.authTokenDTO(
                "Bearer",
                "newAccessToken",
                ACCESS_TOKEN_LIFETIME,
                "newRefreshToken",
                REFRESH_TOKEN_LIFETIME
        );

        // when
        MemberAuthResponseDTO.authTokenDTO responseDTO = memberAuthService.reissueToken(httpServletRequest);

        // then
        assertNotNull(responseDTO);
        assertEquals("newAccessToken", responseDTO.accessToken());
        assertEquals("newRefreshToken", responseDTO.refreshToken());

        // Redis에 RefreshToken 업데이트 확인
        verify(refreshTokenRedisRepository, times(1)).save(any(RefreshToken.class));

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
