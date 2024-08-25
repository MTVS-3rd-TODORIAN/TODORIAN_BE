package com.todorian.member.service;


import com.todorian.member.domain.Member;
import com.todorian.member.dto.MemberResponseDTO;
import com.todorian.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberOAuthService {

    private final MemberRepository memberRepository;

    /*
        카카오 로그인
     */
    // 카카오로부터 받은 최신 사용자 정보로 데이터베이스 내의 사용자 정보를 갱신할 필요가 있을까?
    @Transactional
    public MemberResponseDTO.authTokenDTO kakaoLogin(String code) {

        // 토큰 발급
        String accessToken = generateAccessToken(code);

        // 사용자 정보
        MemberResponseDTO.KakaoInfoDTO profile = getKakaoProfile(accessToken);

        // 회원 확인
        Member member = findMemberByEmail(profile.kakaoAccount().email())
                .orElseGet(() -> kakaoSignUp(profile));

        return getOAuthTokenDTO(member);
    }

    // 이메일 확인
    protected Optional<Member> findMemberByEmail(String email) {
        log.info("회원 확인 : {}", email);

        return memberRepository.findByEmail(email);
    }
}
