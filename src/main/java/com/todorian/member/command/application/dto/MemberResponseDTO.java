package com.todorian.member.command.application.dto;

public class MemberResponseDTO {

    // 회원 프로필
    public record getMemberProfileDTO(
            String nickName
    ){
    }
}
