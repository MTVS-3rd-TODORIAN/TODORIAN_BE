package com.todorian.member.service;

import com.todorian.member.domain.Authority;
import com.todorian.member.domain.Member;
import com.todorian.member.domain.SocialType;
import com.todorian.member.dto.MemberCreateRequestDTO;
import com.todorian.member.dto.MemberRequestDTO;
import com.todorian.member.dto.MemberResponseDTO;
import com.todorian.member.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
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

    public void signUp(MemberRequestDTO.signUpDTO requestDTO) {

    }

    public MemberResponseDTO.authTokenDTO login(HttpServletRequest httpServletRequest, MemberRequestDTO.loginDTO requestDTO) {
        return null;
    }

    public MemberResponseDTO.authTokenDTO reissueToken(HttpServletRequest httpServletRequest) {
        return null;
    }

    public void logout(HttpServletRequest httpServletRequest) {

    }
}
