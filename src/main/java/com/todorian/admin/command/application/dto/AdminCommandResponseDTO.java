package com.todorian.admin.command.application.dto;

public class AdminCommandResponseDTO {

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
