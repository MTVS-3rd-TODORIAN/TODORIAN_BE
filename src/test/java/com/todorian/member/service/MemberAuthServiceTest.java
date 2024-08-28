package com.todorian.member.service;

import com.todorian.member.command.application.dto.MemberRequestDTO;
import com.todorian.member.command.application.service.MemberAuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
public class MemberAuthServiceTest {

    @Autowired
    private MemberAuthService memberAuthService;

    @BeforeEach
    void setUp() {
        MemberRequestDTO.signUpDTO requestDTO = new MemberRequestDTO.signUpDTO(
                "userNickName",
                "user@test.com",
                "test1234",
                "test1234"
        );

        memberAuthService.signUp(requestDTO);
    }
}
