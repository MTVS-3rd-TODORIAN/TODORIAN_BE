package com.todorian.member;

import com.todorian.member.dto.MemberCreateRequestDTO;
import com.todorian.member.service.MemberService;
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
@Transactional
public class MemberTests {

    @Autowired
    private MemberService memberService;

    @BeforeEach
    void setUp() {
        MemberCreateRequestDTO memberCreateRequestDTO = new MemberCreateRequestDTO(
                "user@test.com", "test1234!"
        );

        memberService.save(memberCreateRequestDTO);
    }

    private static Stream<Arguments> createMember() {
        return Stream.of(
                Arguments.of("user1@test.com", "test1234!")
        );
    }

    @DisplayName("회원 추가 테스트")
    @ParameterizedTest
    @MethodSource("createMember")
    void createMember(String email, String password) {

        MemberCreateRequestDTO memberCreateRequestDTO  = new MemberCreateRequestDTO(email, password);

        Assertions.assertDoesNotThrow(
                () -> memberService.save(memberCreateRequestDTO)
        );
    }
}
