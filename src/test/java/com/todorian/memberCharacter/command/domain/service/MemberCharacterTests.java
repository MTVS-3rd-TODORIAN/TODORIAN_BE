package com.todorian.memberCharacter.command.domain.service;

import com.todorian.membercharacter.command.application.dto.MemberCharacterFindResponseDTO;
import com.todorian.membercharacter.command.application.service.MemberCharacterCreateService;
import com.todorian.membercharacter.command.application.service.MemberCharacterDeleteService;
import com.todorian.membercharacter.command.application.service.MemberCharacterFindService;
import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.application.dto.MemberCharacterCreateRequestDTO;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class MemberCharacterTests {

    @Autowired
    private MemberCharacterRepository memberCharacterRepository;
    @Autowired
    private MemberCharacterFindService memberCharacterFindService;
    private MemberCharacterCreateService memberCharacterCreateService;
    private MemberCharacterDeleteService memberCharacterDeleteService;

    private static Stream<Arguments> createMemberCharacter(){

        return Stream.of(
                Arguments.of(1, 1, 10),
                Arguments.of(2, 2, 10)
        );
    }

    @DisplayName("회원캐릭터 추가 테스트")
    @ParameterizedTest
    @MethodSource("createMemberCharacter")
    void testCreateMemberCharacter(long memberId, long characterId, int growthPoint) {
        MemberCharacterCreateRequestDTO memberCharacterCreateRequestDTO
                = new MemberCharacterCreateRequestDTO(memberId, characterId, growthPoint);

        Assertions.assertDoesNotThrow(
                () -> memberCharacterCreateService.createMemberCharacter(memberCharacterCreateRequestDTO)
        );
    }

    @DisplayName("회원캐릭터 전체 조회 테스트")
    @Test
    void testFindAllMemberCharacters(){
        Assertions.assertDoesNotThrow(
                () -> {
                    List<MemberCharacterFindResponseDTO> memberCharacters
                            = memberCharacterFindService.findAllMemberCharacters();

                    memberCharacters.forEach(System.out::println);
                }
        );
    }

    @DisplayName("회원캐릭터 번호로 개별 회원캐릭터 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3})
    void testFindMemberCharacterById(long id){
        Assertions.assertDoesNotThrow(
                () -> {
                    MemberCharacterFindResponseDTO memberCharacter
                            = memberCharacterFindService.findMemberCharacterById(id);
                    System.out.println("memberCharacter = " + memberCharacter);
                }
        );
    }

    @DisplayName("회원캐릭터 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3})
    void testRemoveMemberCharacter(long memberCharacterId){

        Assertions.assertDoesNotThrow(
                () -> memberCharacterDeleteService.deleteMemberCharacterById(memberCharacterId)
        );

        memberCharacterFindService.findAllMemberCharacters().forEach(System.out::println);
    }

}
