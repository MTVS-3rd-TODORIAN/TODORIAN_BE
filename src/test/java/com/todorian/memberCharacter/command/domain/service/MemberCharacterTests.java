package com.todorian.memberCharacter.command.domain.service;

import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import com.todorian.membercharacter.command.domain.model.MemberCharacterCreateRequestDTO;
import com.todorian.membercharacter.command.domain.repository.MemberCharacterRepository;
import com.todorian.membercharacter.command.domain.service.MemberCharacterService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
public class MemberCharacterTests {

    @Autowired
    private MemberCharacterRepository memberCharacterRepository;
    @Autowired
    private MemberCharacterService memberCharacterService;

    private static Stream<Arguments> createMemberCharacter(){

        return Stream.of(
                Arguments.of(1, 1, new Date()),
                Arguments.of(2, 2, new Date())
        );
    }

    @DisplayName("회원캐릭터 추가 테스트")
    @ParameterizedTest
    @MethodSource("createMemberCharacter")
    void testCreateMemberCharacter(long memberId, long characterId, Date acquisitionDate) {
        MemberCharacterCreateRequestDTO memberCharacterCreateRequestDTO
                = new MemberCharacterCreateRequestDTO(memberId, characterId, acquisitionDate);

        Assertions.assertDoesNotThrow(
                () -> memberCharacterService.createMemberCharacter(memberCharacterCreateRequestDTO)
        );
    }

    @DisplayName("회원캐릭터 전체 조회 테스트")
    @Test
    void testFindAllMemberCharacters(){
        Assertions.assertDoesNotThrow(
                () -> {
                    List<MemberCharacter> memberCharacters
                            = memberCharacterService.findAllMemberCharacters();

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
                    MemberCharacter memberCharacter
                            = memberCharacterService.findMemberCharacterById(id);
                    System.out.println("memberCharacter = " + memberCharacter);
                }
        );
    }

    @DisplayName("회원캐릭터 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3})
    void testRemoveMemberCharacter(long memberCharacterId){

        Assertions.assertDoesNotThrow(
                () -> memberCharacterService.removeMemberCharacter(memberCharacterId)
        );

        memberCharacterService.findAllMemberCharacters().forEach(System.out::println);
    }

}
