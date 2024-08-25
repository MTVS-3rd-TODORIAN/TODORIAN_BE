package com.todorian.member;

import com.todorian.member.command.application.dto.MemberCreateRequestDTO;
import com.todorian.member.command.application.service.MemberAuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class MemberTests {

    @Autowired
    private MemberAuthService memberAuthService;

    @BeforeEach
    void setUp() {
        MemberCreateRequestDTO memberCreateRequestDTO = new MemberCreateRequestDTO(
                "userNickName", "user@test.com", "test1234!"
        );

        memberAuthService.save(memberCreateRequestDTO);
    }

    private static Stream<Arguments> createMember() {
        return Stream.of(
                Arguments.of("userNickName1", "user1@test.com", "test1234!")
        );
    }

    @DisplayName("회원 추가 테스트")
    @ParameterizedTest
    @MethodSource("createMember")
    void createMember(String nickName, String email, String password) {

        MemberCreateRequestDTO memberCreateRequestDTO  = new MemberCreateRequestDTO(nickName, email, password);

        Assertions.assertDoesNotThrow(
                () -> memberAuthService.save(memberCreateRequestDTO)
        );
    }

    @DisplayName("회원 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1})
    void deleteMember(long id) {

        Assertions.assertDoesNotThrow(
                () -> memberAuthService.delete(id)
        );
    }
}
