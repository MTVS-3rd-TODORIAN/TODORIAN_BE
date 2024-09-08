package com.todorian.member.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian.member.command.application.dto.MemberRequestDTO;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.model.Status;
import com.todorian.member.command.domain.repository.MemberRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberStatusService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void deleteMember(Long memberId) {

        // 회원 확인
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new Exception400("가입되지 않은 회원입니다."));

        // 이미 탈퇴된 회원인지 확인
        if (member.getStatus() == Status.DEACTIVATED) {
            throw new Exception400("이미 탈퇴된 회원입니다.");
        }

        // Soft Delete
        member.deactivate();

        log.info("회원 ID: {}가 탈퇴 처리되었습니다.", memberId);
    }

    @Transactional
    public void restoreMember(MemberRequestDTO.authDTO requestDTO) {

        // 1. 이메일 확인
        Member member = memberRepository.findByEmail(requestDTO.email())
                .orElseThrow(() -> new Exception400("가입 되지 않은 이메일입니다."));

        // 2. 비밀번호 확인
        checkValidPassword(requestDTO.password(), member.getPassword());

        // 3. 회원 상태 확인
        if(member.getStatus() == Status.ACTIVE) {
            throw new Exception400("이미 활성화된 회원입니다.");
        }

        if(member.getStatus() == Status.SUSPENDED) {
            throw new Exception400("정지된 회원은 계정 복구를 할 수 없습니다.");
        }

        // 4. 회원 상태 변경
        member.activate();

        log.info("회원 ID: {}가 복구 처리되었습니다.", member.getId());
    }

    // 비밀번호 확인
    private void checkValidPassword(String rawPassword, String encodedPassword) {

        log.info("{} {}", rawPassword, encodedPassword);

        if(!passwordEncoder.matches(rawPassword, encodedPassword)) {
            throw new Exception400("비밀번호가 유효하지 않습니다.");
        }
    }
}
