package com.todorian.character.command.domain.service;

import com.todorian.character.command.application.dto.CharacterFindResponseDTO;
import com.todorian.character.command.application.service.CharacterDeleteService;
import com.todorian.character.command.domain.model.CharacterCategory;
import com.todorian.character.command.application.dto.CharacterCreateRequestDTO;
import com.todorian.character.command.application.service.CharacterFindService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

@SpringBootTest
public class CharacterTests {

    @Autowired
    private CharacterFindService characterFindService;
    @Autowired
    private CharacterDeleteService characterDeleteService;

    private static Stream<Arguments> createCharacter(){
        return Stream.of(
                Arguments.of(""),
                Arguments.of("")
        );
    }

    @DisplayName("캐릭터 추가 테스트")
    @ParameterizedTest
    @MethodSource("createCharacter")
    void createCharacter(String characterName, int characterPrice,
                         String characterDescription, String characterImgsrc,
                         CharacterCategory characterCategory) {
        CharacterCreateRequestDTO characterCreateRequestDTO
                = new CharacterCreateRequestDTO(characterName, characterPrice,
                characterDescription, characterImgsrc, characterCategory);

        Assertions.assertDoesNotThrow(
                () -> characterFindService.save(characterCreateRequestDTO)
        );
    }

    @DisplayName("캐릭터 전체 조회 테스트")
    @Test
    void testFindAllCharacters(){
        Assertions.assertDoesNotThrow(
                () -> {
                    List<CharacterFindResponseDTO> characters
                            = characterFindService.findAllCharacters();

                    characters.forEach(System.out::println);
                }
        );
    }

    @DisplayName("캐릭터 번호로 개별 회원캐릭터 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3})
    void testFindCharacterById(long id) {
        Assertions.assertDoesNotThrow(
                () -> {
                    CharacterFindResponseDTO character
                            = characterFindService.findCharacterById(id);
                    System.out.println("character = " + character);
                }
        );
    }

    @DisplayName("캐릭터 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1, 2, 3})
    void testRemoveCharacterById(long characterId) {
        Assertions.assertDoesNotThrow(
                () -> characterDeleteService.deleteCharacterById(characterId)
        );

        characterFindService.findAllCharacters().forEach(System.out::println);
    }
}
