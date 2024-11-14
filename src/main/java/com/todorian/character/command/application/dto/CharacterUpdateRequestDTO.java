package com.todorian.character.command.application.dto;

import com.todorian.character.command.domain.model.CharacterCategory;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CharacterUpdateRequestDTO {

    private long characterId;
    private String characterName;
    private int characterPrice;
    private String characterDescription;
    private List<String> characterImgsrc;
    private CharacterCategory characterCategory;

}
