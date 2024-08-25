package com.todorian.member.service;


import com.todorian._core.error.exception.Exception400;
import com.todorian._core.error.exception.Exception500;
import com.todorian._core.jwt.JWTTokenProvider;
import com.todorian.member.domain.Authority;
import com.todorian.member.domain.Member;
import com.todorian.member.domain.SocialType;
import com.todorian.member.dto.MemberResponseDTO;
import com.todorian.member.property.KakaoProperties;
import com.todorian.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberOAuthService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final JWTTokenProvider jwtTokenProvider;

    private final KakaoProperties kakaoProperties;
    private final RestTemplate restTemplate = new RestTemplate();

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

    // kakao code 기반 AccessToken 발급
    protected String generateAccessToken(String code) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", kakaoProperties.getGrantType());
        params.add("client_id", kakaoProperties.getClientId());
        params.add("redirect_uri", kakaoProperties.getRedirectUri());
        params.add("code", code);

        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(params, headers);
        ResponseEntity<MemberResponseDTO.KakaoTokenDTO> response = restTemplate.postForEntity(
                kakaoProperties.getTokenUri(),
                httpEntity,
                MemberResponseDTO.KakaoTokenDTO.class
        );

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception500("Access Token 을 가져오는데 실패했습니다.");
        }

        return Objects.requireNonNull(response.getBody()).accessToken();
    }

    // kakao 유저 프로필 확인
    protected MemberResponseDTO.KakaoInfoDTO getKakaoProfile(String accessToken) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(accessToken);

        ResponseEntity<MemberResponseDTO.KakaoInfoDTO> response = restTemplate.postForEntity(
                kakaoProperties.getUserInfoUri(),
                new HttpEntity<>(headers),
                MemberResponseDTO.KakaoInfoDTO.class
        );

        if(!response.getStatusCode().is2xxSuccessful()) {
            throw new Exception500("Kakao 유저 프로필을 가져오는데 실패했습니다.");
        }

        return response.getBody();
    }

    // 카카오 회원 생성
    protected Member kakaoSignUp(MemberResponseDTO.KakaoInfoDTO profile) {
        log.info("카카오 회원 생성 : " + profile.kakaoAccount().email());

        Member member = Member.builder()
                .nickName(profile.properties().nickname())
                .email(profile.kakaoAccount().email())
                .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                .socialType(SocialType.KAKAO)
                .authority(Authority.USER)
                .build();

        memberRepository.save(member);

        return member;
    }

    // 이메일 확인
    protected Optional<Member> findMemberByEmail(String email) {
        log.info("회원 확인 : {}", email);

        return memberRepository.findByEmail(email);
    }

    // OAuth Token 발급
    protected MemberResponseDTO.authTokenDTO getOAuthTokenDTO(Member member) {
        UserDetails userDetails = new User(member.getEmail(), "",
                Collections.singletonList(new SimpleGrantedAuthority(member.getAuthority().toString())));
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        return jwtTokenProvider.generateToken(authentication);
    }
}
