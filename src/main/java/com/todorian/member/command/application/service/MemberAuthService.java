package com.todorian.member.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian._core.jwt.JWTTokenProvider;
import com.todorian.member.command.domain.model.Authority;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.model.SocialType;
import com.todorian.member.command.domain.model.Status;
import com.todorian.member.command.application.dto.MemberCreateRequestDTO;
import com.todorian.member.command.application.dto.MemberRequestDTO;
import com.todorian.member.command.application.dto.MemberResponseDTO;
import com.todorian.member.command.domain.repository.MemberRepository;
import com.todorian.redis.domain.RefreshToken;
import com.todorian.redis.repository.RefreshTokenRedisRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberAuthService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public void save(MemberCreateRequestDTO memberCreateRequestDTO) {

        Member member = new Member(
                memberCreateRequestDTO.getNickName(),
                memberCreateRequestDTO.getEmail(),
                memberCreateRequestDTO.getPassword(),
                SocialType.NONE,
                Authority.USER,
                Status.ACTIVE
        );

        memberRepository.save(member);
    }

    public void delete(long id) {

        memberRepository.deleteById(id);
    }

    /*
        기본 회원 가입
     */
    @Transactional
    public void signUp(MemberRequestDTO.signUpDTO requestDTO) {

        // 이메일 중복 확인
        checkDuplicatedEmail(requestDTO.email());

        // 비밀번호 확인
        checkValidPassword(requestDTO.password(), passwordEncoder.encode(requestDTO.confirmPassword()));

        // 회원 생성
        Member member = newMember(requestDTO);

        // 회원 저장
        memberRepository.save(member);
    }

    /*
        기본 로그인
     */
    public MemberResponseDTO.authTokenDTO login(HttpServletRequest httpServletRequest, MemberRequestDTO.authDTO requestDTO) {

        // 1. 이메일 확인
        Member member = memberRepository.findByEmail(requestDTO.email())
                .orElseThrow(() -> new Exception400("가입 되지 않은 이메일입니다."));

        // 2. 비밀번호 확인
        checkValidPassword(requestDTO.password(), member.getPassword());

        return getAuthTokenDTO(requestDTO.email(), requestDTO.password(), httpServletRequest);
    }

    /*
        토큰 재발급
     */
    public MemberResponseDTO.authTokenDTO reissueToken(HttpServletRequest httpServletRequest) {

        // Request Header 에서 JWT Token 추출
        String token = jwtTokenProvider.resolveToken(httpServletRequest);

        // 토큰 유효성 검사
        if(token == null || !jwtTokenProvider.validateToken(token)) {
            throw new Exception400("유효하지 않은 Access Token 입니다.");
        }

        // type 확인
        if(!jwtTokenProvider.isRefreshToken(token)) {
            throw new Exception400("유효하지 않은 Refresh Token 입니다.");
        }

        // RefreshToken
        RefreshToken refreshToken = refreshTokenRedisRepository.findByRefreshToken(token);

        if(refreshToken == null) {
            throw new Exception400("Refresh Token 이 비어있습니다.");
        }

        // Redis 에 저장된 RefreshToken 정보를 기반으로 JWT Token 생성
        MemberResponseDTO.authTokenDTO authTokenDTO = jwtTokenProvider.generateToken(
                refreshToken.getId(), refreshToken.getAuthorities()
        );

        // Redis 에 RefreshToken Update
        refreshTokenRedisRepository.save(RefreshToken.builder()
                .id(refreshToken.getId())
                .authorities(refreshToken.getAuthorities())
                .refreshToken(authTokenDTO.refreshToken())
                .build());

        return authTokenDTO;
    }

    /*
        로그아웃
     */
    public void logout(HttpServletRequest httpServletRequest) {

        log.info("로그아웃 - Refresh Token 확인");

        String token = jwtTokenProvider.resolveToken(httpServletRequest);

        if(token == null || !jwtTokenProvider.validateToken(token)) {
            throw new Exception400("유효하지 않은 Refresh Token 입니다.");
        }

        RefreshToken refreshToken = refreshTokenRedisRepository.findByRefreshToken(token);
        refreshTokenRedisRepository.delete(refreshToken);
    }

    // 이메일 중복 확인
    private void checkDuplicatedEmail(String email) {

        Optional<Member> member = memberRepository.findByEmail(email);

        if(member.isPresent()) {
            throw new Exception400("이미 가입된 이메일입니다.");
        }
    }

    // 비밀번호 확인
    private void checkValidPassword(String rawPassword, String encodedPassword) {

        log.info("{} {}", rawPassword, encodedPassword);

        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new Exception400("비밀번호가 유효하지 않습니다.");
        }
    }

    // 회원 생성
    protected Member newMember(MemberRequestDTO.signUpDTO requestDTO) {
        return Member.builder()
                .nickName(requestDTO.nickName())
                .email(requestDTO.email())
                .password(passwordEncoder.encode(requestDTO.password()))
                .socialType(SocialType.NONE)
                .authority(Authority.USER)
                .status(Status.ACTIVE)
                .build();
    }

    // 토큰 발급
    protected MemberResponseDTO.authTokenDTO getAuthTokenDTO(String email, String password, HttpServletRequest httpServletRequest) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(email, password);
        AuthenticationManager manager = authenticationManagerBuilder.getObject();
        Authentication authentication = manager.authenticate(usernamePasswordAuthenticationToken);

        MemberResponseDTO.authTokenDTO authTokenDTO = jwtTokenProvider.generateToken(authentication);

        refreshTokenRedisRepository.save(RefreshToken.builder()
                .id(authentication.getName())
                .authorities(authentication.getAuthorities())
                .refreshToken(authTokenDTO.refreshToken())
                .build()
        );

        return authTokenDTO;
    }
}
