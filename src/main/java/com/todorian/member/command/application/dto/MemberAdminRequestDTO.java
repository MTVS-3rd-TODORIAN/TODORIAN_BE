package com.todorian.member.command.application.dto;

public class MemberAdminRequestDTO {

    public record loginDTO(
            String email,
            String password
    ) {
    }
}
