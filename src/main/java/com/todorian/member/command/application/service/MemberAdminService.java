package com.todorian.member.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian._core.error.exception.Exception403;
import com.todorian._core.jwt.JWTTokenProvider;
import com.todorian.member.command.application.dto.MemberRequestDTO;
import com.todorian.member.command.application.dto.MemberResponseDTO;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.model.property.Authority;
import com.todorian.member.command.domain.model.property.Status;
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

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberAdminService {

    private final MemberRepository memberRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    /*
        관리자 로그인
     */
    public MemberResponseDTO.authTokenDTO login(MemberRequestDTO.authDTO requestDTO) {

        // 1. 이메일 확인
        Member member = getMemberByEmail(requestDTO);

        // 2. 비밀번호 확인
        checkValidPassword(requestDTO.password(), member.getPassword());

        // 3. 회원 권한 확인
        validateAdmin(member);

        return getAuthTokenDTO(requestDTO.email(), requestDTO.password());
    }

    // 토큰 발급
    protected MemberResponseDTO.authTokenDTO getAuthTokenDTO(String email, String password) {

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

    // 회원 확인 - 이메일
    private Member getMemberByEmail(MemberRequestDTO.authDTO requestDTO) {
        return memberRepository.findByEmail(requestDTO.email())
                .orElseThrow(() -> new Exception400("가입 되지 않은 이메일입니다."));
    }

    // 비밀번호 확인
    private void checkValidPassword(String rawPassword, String encodedPassword) {

        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new Exception400("비밀번호가 유효하지 않습니다.");
        }
    }

    // 계정 유효성 검사
    private void validateAdmin(Member member) {
        checkMemberStatus(member.getStatus());
        checkMemberAuthority(member.getAuthority());
    }

    // 계정 상태 확인
    private void checkMemberStatus(Status status) {
        if (status != Status.ACTIVE) {
            throw new Exception400("해당 계정을 사용하실 수 없습니다.");
        }
    }

    // 계정 권한 확인
    private void checkMemberAuthority(Authority authority) {
        if (authority != Authority.ADMIN) {
            throw new Exception403("해당 계정은 관리 권한이 없습니다.");
        }
    }
}
