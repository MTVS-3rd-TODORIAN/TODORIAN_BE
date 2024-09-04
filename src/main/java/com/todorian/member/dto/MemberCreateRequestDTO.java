package com.todorian.member.dto;

public class MemberCreateRequestDTO {

    private String email;
    private String password;

    public MemberCreateRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "MemberCreateRequestDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
