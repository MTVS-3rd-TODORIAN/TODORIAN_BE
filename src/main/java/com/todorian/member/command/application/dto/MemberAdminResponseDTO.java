package com.todorian.member.command.application.dto;

public class MemberAdminResponseDTO {

    public record loginDTO(
            String grantType,
            String accessToken,
            Long accessTokenValidTime,
            String refreshToken,
            Long refreshTokenValidTime
    ) {
    }
}
