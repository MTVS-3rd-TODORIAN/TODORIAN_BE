package com.todorian.member.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian.member.domain.Authority;
import com.todorian.member.domain.Member;
import com.todorian.member.domain.SocialType;
import com.todorian.member.dto.MemberCreateRequestDTO;
import com.todorian.member.dto.MemberRequestDTO;
import com.todorian.member.dto.MemberResponseDTO;
import com.todorian.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final PasswordEncoder passwordEncoder;

    public void save(MemberCreateRequestDTO memberCreateRequestDTO) {

        Member member = new Member(
                memberCreateRequestDTO.getNickName(),
                memberCreateRequestDTO.getEmail(),
                memberCreateRequestDTO.getPassword(),
                SocialType.NONE,
                Authority.USER
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

    public MemberResponseDTO.authTokenDTO login(HttpServletRequest httpServletRequest, MemberRequestDTO.loginDTO requestDTO) {
        return null;
    }

    public MemberResponseDTO.authTokenDTO reissueToken(HttpServletRequest httpServletRequest) {
        return null;
    }

    public void logout(HttpServletRequest httpServletRequest) {

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
                .build();
    }
}
