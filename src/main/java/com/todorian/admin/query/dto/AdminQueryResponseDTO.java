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
}
