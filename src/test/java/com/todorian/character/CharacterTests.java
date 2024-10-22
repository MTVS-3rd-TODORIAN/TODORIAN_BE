package com.todorian.character;

import com.todorian.character.command.domain.model.CharacterCategory;
import com.todorian.character.command.application.dto.CharacterCreateRequestDTO;
import com.todorian.character.command.application.service.CharacterFindService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

@SpringBootTest
public class CharacterTests {

    @Autowired
    private CharacterFindService characterFindService;

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

}
