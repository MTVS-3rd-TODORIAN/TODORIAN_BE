package com.todorian.character.command.application.dto;

import com.todorian.character.command.domain.model.CharacterCategory;

public class CharacterCreateRequestDTO {

    private long characterId;
    private String characterName;
    private int characterPrice;
    private String characterDescription;
    private String characterImgsrc;
    private CharacterCategory characterCategory;

    public CharacterCreateRequestDTO() {}

    public CharacterCreateRequestDTO(String characterName, int characterPrice, String characterDescription, String characterImgsrc, CharacterCategory characterCategory) {
        this.characterName = characterName;
        this.characterPrice = characterPrice;
        this.characterDescription = characterDescription;
        this.characterImgsrc = characterImgsrc;
        this.characterCategory = characterCategory;
    }

    public long getCharacterId() {
        return characterId;
    }

    public String getCharacterName() {
        return characterName;
    }

    public int getCharacterPrice() {
        return characterPrice;
    }

    public String getCharacterDescription() {
        return characterDescription;
    }

    public String getCharacterImgsrc() {
        return characterImgsrc;
    }

    public CharacterCategory getCharacterCategory() {
        return characterCategory;
    }

    @Override
    public String toString() {
        return "CharacterCreateRequestDTO{" +
                ", characterName='" + characterName + '\'' +
                ", characterPrice=" + characterPrice +
                ", characterDescription='" + characterDescription + '\'' +
                ", characterImgsrc='" + characterImgsrc + '\'' +
                ", characterCategory=" + characterCategory +
                '}';
    }
}
