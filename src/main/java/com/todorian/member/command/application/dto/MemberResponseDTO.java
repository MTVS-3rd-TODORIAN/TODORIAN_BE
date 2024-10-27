package com.todorian.member.command.application.dto;

public class MemberResponseDTO {

    // 토큰 발급
    public record authTokenDTO(
            String grantType,
            String accessToken,
            Long accessTokenValidTime,
            String refreshToken,
            Long refreshTokenValidTime
    ) {
    }

    // 회원 프로필
    public record getMemberProfileDTO(
            String nickName
    ){
    }
}
