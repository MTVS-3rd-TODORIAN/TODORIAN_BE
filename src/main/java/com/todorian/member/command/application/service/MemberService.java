package com.todorian.member.command.application.service;

import com.todorian._core.error.exception.Exception401;
import com.todorian.member.command.application.dto.MemberResponseDTO;
import com.todorian.member.command.domain.model.Member;
import com.todorian.member.command.domain.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberResponseDTO.getMemberProfileDTO getMemberProfile(Long currentMemberId) {

        Member member = memberRepository.findById(currentMemberId)
                .orElseThrow(() -> new Exception401("해당 회원을 찾을 수 없습니다."));

        return new MemberResponseDTO.getMemberProfileDTO(member.getNickName());
    }
}
