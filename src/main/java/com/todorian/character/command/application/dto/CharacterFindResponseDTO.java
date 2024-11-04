package com.todorian.character.command.application.dto;

import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.domain.model.CharacterCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CharacterFindResponseDTO {


    // 캐릭터 고유 번호
    private long characterId;

    // 캐릭터 이름
    private String characterName;

    // 캐릭터 가격
    private int characterPrice;

    // 캐릭터 묘사
    private String characterDescription;

    // 캐릭터 이미지 주소
    private List<String> characterImgsrc;

    private CharacterCategory characterCategory;

    public CharacterFindResponseDTO(long characterId, String characterName, int characterPrice, String characterDescription, List<String> characterImgsrc, CharacterCategory characterCategory) {
        this.characterId = characterId;
        this.characterName = characterName;
        this.characterPrice = characterPrice;
        this.characterDescription = characterDescription;
        this.characterImgsrc = characterImgsrc;
        this.characterCategory = characterCategory;
    }

    public CharacterFindResponseDTO(Character character) {
        this.characterId = character.getCharacterId();
        this.characterName = character.getCharacterName();
        this.characterPrice = character.getCharacterPrice();
        this.characterDescription = character.getCharacterDescription();
        this.characterImgsrc = character.getCharacterImgsrc();
        this.characterCategory = character.getCharacterCategory();
    }
}
