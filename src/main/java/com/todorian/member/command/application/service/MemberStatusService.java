package com.todorian.member.command.application.service;

import com.todorian._core.error.exception.Exception400;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.model.Status;
import com.todorian.member.command.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberStatusService {

    private final MemberRepository memberRepository;

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
}
