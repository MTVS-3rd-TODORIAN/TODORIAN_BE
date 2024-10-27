package com.todorian.member.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian._core.error.exception.Exception403;
import com.todorian.member.command.application.dto.MemberRequestDTO;
import com.todorian.member.command.application.dto.MemberResponseDTO;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.model.property.Authority;
import com.todorian.member.command.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberAdminService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    /*
        관리자 로그인
     */
    public MemberResponseDTO.loginDTO login(MemberRequestDTO.authDTO requestDTO) {

        // 1. 이메일 확인
        Member member = getMemberByEmail(requestDTO);

        // 2. 비밀번호 확인
        checkValidPassword(requestDTO.password(), member.getPassword());

        // 3. 회원 권한 확인
        checkAdminAuthority(member);

        return null;
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

    // 회원 권한 확인
    private void checkAdminAuthority(Member member) {

        if(member.getAuthority() != Authority.ADMIN) {
            throw new Exception403("관리자 권한이 없는 계정입니다.");
        }
    }
}
