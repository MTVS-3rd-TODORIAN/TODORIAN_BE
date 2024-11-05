package com.todorian.membercharacter.command.application.dto;

import com.todorian.membercharacter.command.domain.model.MemberCharacter;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberCharacterFindResponseDTO {

    private Long characterId;
    private Integer step;
    private Integer usedGrowthPoint;
    private Integer growthPoint;
    private Boolean completedStatus;
    private List<String> characterImgsrc;

    public MemberCharacterFindResponseDTO(Object[] result){

        this.characterId = (Long) result[0];
        this.step = (Integer) result[1];
        this.usedGrowthPoint = (Integer) result[2];
        this.growthPoint = (Integer) result[2];
        this.completedStatus = (Boolean) result[3];
        this.characterImgsrc = (List<String>) result[4];
    }

}
