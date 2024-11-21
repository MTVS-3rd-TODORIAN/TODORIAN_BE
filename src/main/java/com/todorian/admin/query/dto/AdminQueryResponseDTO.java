package com.todorian.admin.query.dto;

import java.util.List;

public class AdminQueryResponseDTO {

    public record getCharacterListDTO(
            List<CharacterDTO> charaterDTOList
    ) {
    }

    public record CharacterDTO(
            Long characterId,
            String characterName,
            List<String> characterImgsrc,
            List<Integer> growthCriteria
    ) {
    }

    public record getPointListDTO(
            List<pointDTO> pointDTOList
    ) {
    }

    public record pointDTO(
            Long todoPointId,
            String todoPointType,
            Integer currentRatio
    ) {
    }
}
