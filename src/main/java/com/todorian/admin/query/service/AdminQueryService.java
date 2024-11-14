package com.todorian.admin.query.service;

import com.todorian.admin.query.dto.AdminQueryResponseDTO;
import com.todorian.character.command.domain.model.Character;
import com.todorian.character.command.domain.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AdminQueryService {

    private final CharacterRepository characterRepository;

    /*
        Character List 조회
     */
    public AdminQueryResponseDTO.getCharacterListDTO getCharacterList() {

        List<Character> characterList = characterRepository.findAll();
        List<AdminQueryResponseDTO.CharacterDTO> characterDTOList = characterList.stream()
                .map(character -> new AdminQueryResponseDTO.CharacterDTO(
                        character.getCharacterId(),
                        character.getCharacterName(),
                        character.getCharacterImgsrc(),
                        character.getGrowthCriteria()
                ))
                .toList();

        return new AdminQueryResponseDTO.getCharacterListDTO(characterDTOList);
    }
}
