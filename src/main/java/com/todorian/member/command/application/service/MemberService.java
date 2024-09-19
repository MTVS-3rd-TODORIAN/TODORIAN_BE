package com.todorian.member.command.application.service;


import com.todorian.member.command.application.dto.MemberResponseDTO;
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
        return null;
    }
}
