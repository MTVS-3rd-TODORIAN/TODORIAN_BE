package com.todorian.member.service;

import com.todorian.member.domain.Authority;
import com.todorian.member.domain.Member;
import com.todorian.member.domain.SocialType;
import com.todorian.member.dto.MemberCreateRequestDTO;
import com.todorian.member.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(MemberCreateRequestDTO memberCreateRequestDTO) {

        Member member = new Member(
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
}
