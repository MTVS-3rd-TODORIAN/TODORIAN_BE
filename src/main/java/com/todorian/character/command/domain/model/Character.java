package com.todorian.character.command.domain.model;

import jakarta.persistence.*;

@Entity
@Table(name="tbl_character")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="character_id")
    // 캐릭터 고유 번호
    private long characterId;

    @Column(name="character_name")
    // 캐릭터 이름
    private String characterName;

    @Column(name="character_price")
    // 캐릭터 가격
    private int characterPrice;

    @Column(name="character_descriptrion")
    // 캐릭터 묘사
    private String characterDescription;

    @Column(name="character_imgsrc")
    // 캐릭터 이미지 주소
    private String characterImgsrc;

    @Column(name="character_category")
    private CharacterCategory characterCategory;

    public Character(){}

    public Character(String characterName, int characterPrice, String characterDescription, String characterImgsrc, CharacterCategory characterCategory) {
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
        return "Character{" +
                "characterId=" + characterId +
                ", characterName='" + characterName + '\'' +
                ", characterPrice=" + characterPrice +
                ", characterDescription='" + characterDescription + '\'' +
                ", characterImgsrc='" + characterImgsrc + '\'' +
                ", characterCategory=" + characterCategory +
                '}';
    }
}
