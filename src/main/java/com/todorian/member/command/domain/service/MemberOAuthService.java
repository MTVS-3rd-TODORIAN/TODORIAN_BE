package com.todorian.member.command.domain.service;


import com.todorian.member.command.application.dto.MemberAuthResponseDTO;

public interface MemberOAuthService {

    MemberAuthResponseDTO.authTokenDTO kakaoLogin(String code);
}
