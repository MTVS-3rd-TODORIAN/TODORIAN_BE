package com.todorian.member.command.application.dto;

import lombok.Getter;

@Getter
public class MemberCreateRequestDTO {

    private String nickName;
    private String email;
    private String password;

    public MemberCreateRequestDTO(String nickName, String email, String password) {
        this.nickName = nickName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberCreateRequestDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
