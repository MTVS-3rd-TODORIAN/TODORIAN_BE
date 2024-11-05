package com.todorian.character.command.domain.model;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.todorian.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="tbl_character")
@ToString
public class Character extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="character_id")
    // 캐릭터 고유 번호
    private Long characterId;

    @Column(name="character_name")
    // 캐릭터 이름
    private String characterName;

    @Column(name="character_price")
    // 캐릭터 가격
    private Integer characterPrice;

    @Column(name="character_descriptrion")
    // 캐릭터 묘사
    private String characterDescription;

    @Column(name="character_imgsrc")
    @ElementCollection
    // 캐릭터 이미지 주소 리스트
    private List<String> characterImgsrc;

    @Column(name="character_category")
    private CharacterCategory characterCategory;

    @Column(name="growth_criteira")
    @ElementCollection
    private List<Integer> growthCriteria;

    public Character(){}

    public Character(
            String characterName,
            int characterPrice,
            String characterDescription,
            String characterImgsrc,
            CharacterCategory characterCategory
    ) {
        this.characterName = characterName;
        this.characterPrice = characterPrice;
        this.characterDescription = characterDescription;
        this.characterImgsrc.add(characterImgsrc);
        this.characterCategory = characterCategory;
    }
}
