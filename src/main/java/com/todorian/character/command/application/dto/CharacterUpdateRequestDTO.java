package com.todorian.character.command.application.dto;

import com.todorian.character.command.application.service.CharacterUpdateService;
import com.todorian.character.command.domain.model.CharacterCategory;

public class CharacterUpdateRequestDTO {

    private long characterId;
    private String characterName;
    private int characterPrice;
    private String characterDescription;
    private String characterImgsrc;
    private CharacterCategory characterCategory;

    public CharacterUpdateRequestDTO(){}

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
        return "CharacterUpdateRequestDTO{" +
                "characterId=" + characterId +
                ", characterName='" + characterName + '\'' +
                ", characterPrice=" + characterPrice +
                ", characterDescription='" + characterDescription + '\'' +
                ", characterImgsrc='" + characterImgsrc + '\'' +
                ", characterCategory=" + characterCategory +
                '}';
    }
}
