package com.todorian.member.command.domain.service;


import com.todorian.member.command.application.dto.MemberResponseDTO;

public interface MemberOAuthService {

    MemberResponseDTO.authTokenDTO kakaoLogin(String code);
}
