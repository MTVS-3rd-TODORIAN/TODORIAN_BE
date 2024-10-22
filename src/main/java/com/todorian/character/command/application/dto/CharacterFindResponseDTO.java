package com.todorian.character.command.application.dto;

import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.domain.model.CharacterCategory;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    private String characterImgsrc;

    private CharacterCategory characterCategory;

    public CharacterFindResponseDTO() {}

    public CharacterFindResponseDTO(long characterId, String characterName, int characterPrice, String characterDescription, String characterImgsrc, CharacterCategory characterCategory) {
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

    public long getCharacterId() {
        return characterId;
    }

    public void setCharacterId(long characterId) {
        this.characterId = characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public int getCharacterPrice() {
        return characterPrice;
    }

    public void setCharacterPrice(int characterPrice) {
        this.characterPrice = characterPrice;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public void setCharacterDescription(String characterDescription) {
        this.characterDescription = characterDescription;
    }

    public String getCharacterImgsrc() {
        return characterImgsrc;
    }

    public void setCharacterImgsrc(String characterImgsrc) {
        this.characterImgsrc = characterImgsrc;
    }

    public CharacterCategory getCharacterCategory() {
        return characterCategory;
    }

    public void setCharacterCategory(CharacterCategory characterCategory) {
        this.characterCategory = characterCategory;
    }
}
