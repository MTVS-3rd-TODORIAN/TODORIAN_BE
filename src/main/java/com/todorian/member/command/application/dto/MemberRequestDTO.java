package com.todorian.member.command.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class MemberRequestDTO {

    public record authDTO(
            @Email(message = "올바른 이메일 주소를 입력해 주세요.")
            @NotBlank(message = "이메일을 입력해 주세요.")
            String email,
            String password
    ) {
    }
}
